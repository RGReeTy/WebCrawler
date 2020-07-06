package com.softeq.webcrawler.controller;

import com.softeq.webcrawler.bean.ConfigParam;
import com.softeq.webcrawler.bean.Record;
import com.softeq.webcrawler.dal.CSVHelper;
import com.softeq.webcrawler.dal.CSVHelperImpl;
import com.softeq.webcrawler.service.util.SearchForMatches;
import com.softeq.webcrawler.service.util.SearchForMatchesRegexImpl;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.softeq.webcrawler.service.RecordWorker.getRecordAsStringBuilder;
import static com.softeq.webcrawler.service.RecordWorker.sortByTotalHits;
import static com.softeq.webcrawler.service.util.JsoupParser.addLinksToQueue;
import static com.softeq.webcrawler.service.util.JsoupParser.getHTMLDocumentByURL;

/**
 * The type Controller.
 */
public class ControllerImpl implements Controller {

    private static final Logger logger = Logger.getLogger(ControllerImpl.class);

    private List<Record> records = new ArrayList<>();
    private StringBuilder header = new StringBuilder();
    private StringBuilder body = new StringBuilder();
    private CSVHelper csvHelper = new CSVHelperImpl();

    private final String FULL_STAT_FILE_NAME = "CsvStatOfHits.csv";
    private final String TOP_TEN_STAT_FILE_NAME = "CsvStatOfTopTenHits.csv";

    @Override
    public void startWebScrapping(ConfigParam configParam) {

        List<String> inputWords = new ArrayList<>(Arrays.asList(configParam.getWordsToFind().split(",")));
        //TODO concurrent queue
        Deque<String> reference = new ArrayDeque<>();
        header = new StringBuilder(configParam.getUrl()).append(" ,").append(configParam.getWordsToFind()).append(" , TOTAL");

        int maxPagesToFind = configParam.getMaxPagesToFind();
        int maxDepthOfCrawling = configParam.getMaxDepthOfCrawling();

        reference.addFirst(configParam.getUrl());

        try {
            while (!reference.isEmpty() & maxPagesToFind > 0) {
                Document document = getHTMLDocumentByURL(reference.getFirst());

                Record record = getLineOfCounters(reference.getFirst(), document, inputWords);
                records.add(record);

                if (maxDepthOfCrawling > 0) {
                    addLinksToQueue(document, reference);
                }

                reference.removeFirst();
                maxPagesToFind--;
                maxDepthOfCrawling--;
            }

            getStringBuilderFromListOfEntity(records, body);

        } catch (IOException e) {
            logger.error("Can't get document from url! " + e);
        }


        try {
            csvHelper.writeDataToCSVFile(header, body, FULL_STAT_FILE_NAME);

            getTopTenTotalHitsDescOrder();
        } catch (IOException e) {
            logger.error("Writing data to file throw an error! " + e);
        }
    }


    private void getTopTenTotalHitsDescOrder() throws IOException {
        List<Record> topTenRecords = records.stream()
                .sorted(sortByTotalHits)
                .limit(10)
                .collect(Collectors.toList());

        StringBuilder topTen = new StringBuilder();

        getStringBuilderFromListOfEntity(topTenRecords, topTen);

        csvHelper.writeDataToCSVFile(header, topTen, TOP_TEN_STAT_FILE_NAME);
    }


    private Record getLineOfCounters(String url, Document document, List<String> inputWords) {
        SearchForMatches searchForMatches = new SearchForMatchesRegexImpl();
        String bodyOfHtml = document.body().text();

        long[] hits = new long[inputWords.size() + 1];
        int index = 0;
        long totalCount = 0;

        for (String word : inputWords) {

            long temp = searchForMatches.countMatchesForText(word, bodyOfHtml);
            hits[index] = temp;
            totalCount += temp;
            index++;
        }
        hits[hits.length - 1] = totalCount;

        return new Record(url, hits);
    }


    private void getStringBuilderFromListOfEntity(List<Record> records, StringBuilder text) {
        if (!records.isEmpty()) {
            for (Record record : records) {
                text.append(getRecordAsStringBuilder(record)).append("\n");
            }
        }
    }
}
