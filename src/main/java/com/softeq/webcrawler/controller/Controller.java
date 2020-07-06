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

import static com.softeq.webcrawler.service.RecordWorker.getRecordAsStringBuilder;
import static com.softeq.webcrawler.service.util.JsoupParser.addLinksToQueue;
import static com.softeq.webcrawler.service.util.JsoupParser.getHTMLDocumentByURL;

/**
 * The type Controller.
 */
public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class);

    /**
     * Start web scrapping.
     *
     * @param configParam the config param
     */
    public void startWebScrapping(ConfigParam configParam) {

        List<String> inputWords = new ArrayList<>(Arrays.asList(configParam.getWordsToFind().split(",")));
        List<Record> records = new ArrayList<>();
        //TODO concurrent queue
        Deque<String> reference = new ArrayDeque<String>();
        StringBuilder header = new StringBuilder(configParam.getUrl()).append(" ,").append(configParam.getWordsToFind()).append(" , TOTAL");
        StringBuilder body = new StringBuilder();
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

            if (!records.isEmpty()) {
                for (Record record : records) {
                    body.append(getRecordAsStringBuilder(record)).append("\n");
                }
            }


        } catch (IOException e) {
            logger.error(e);
        }

        CSVHelper csvHelper = new CSVHelperImpl();
        csvHelper.writeDataToCSVFile(header, body);

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
}
