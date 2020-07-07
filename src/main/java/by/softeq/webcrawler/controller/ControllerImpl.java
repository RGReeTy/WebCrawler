package by.softeq.webcrawler.controller;

import by.softeq.webcrawler.bean.ConfigParam;
import by.softeq.webcrawler.bean.Record;
import by.softeq.webcrawler.dal.CSVHelper;
import by.softeq.webcrawler.dal.CSVHelperImpl;
import by.softeq.webcrawler.service.util.SearchForMatches;
import by.softeq.webcrawler.service.util.SearchForMatchesRegexImpl;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static by.softeq.webcrawler.service.RecordWorker.sortByTotalHits;
import static by.softeq.webcrawler.service.RecordWorker.transformRecordToStringBuilder;
import static by.softeq.webcrawler.service.util.JsoupParser.addLinksToQueue;
import static by.softeq.webcrawler.service.util.JsoupParser.getHTMLDocumentByURL;

/**
 * The type Controller.
 */
public class ControllerImpl implements Controller {

    private static final Logger logger = Logger.getLogger(ControllerImpl.class);

    //Collection of records
    private List<Record> records = new ArrayList<>();
    //This class help to work with csv files
    private CSVHelper csvHelper = new CSVHelperImpl();

    private final String FULL_STAT_FILE_NAME = "CsvStatOfHits.csv";
    private final String TOP_TEN_STAT_FILE_NAME = "CsvStatOfTopTenHits.csv";

    @Override
    public void startWebScrapping(ConfigParam configParam) {
        //Get header of future csv-table from input params
        StringBuilder header = new StringBuilder(configParam.getUrl()).append(" ,").append(configParam.getWordsToFind()).append(" , TOTAL");
        StringBuilder body = new StringBuilder();

        //Parse line of words to search by splitting comma
        List<String> inputWords = new ArrayList<>(Arrays.asList(configParam.getWordsToFind().split(",")));

        //Collection of references. Links found later - are added to the end of the queue
        Deque<String> reference = new ArrayDeque<>();

        int maxPagesToFind = configParam.getMaxPagesToFind();
        int maxDepthOfCrawling = configParam.getMaxDepthOfCrawling();

        //Search start from input url
        reference.addFirst(configParam.getUrl());

//        try {
        do {
            Document document = null;
            try {
                document = getHTMLDocumentByURL(reference.getFirst());
                Record record = getLineOfCounters(reference.getFirst(), document, inputWords);
                records.add(record);

                //Geometric progression interesting thing =)
                if (maxDepthOfCrawling > 0) {
                    addLinksToQueue(document, reference);
                }
            } catch (IOException e) {
                logger.error("Can't get document from url! " + e);
            }

            //Delete used reference from collection
            reference.removeFirst();
            maxPagesToFind--;
            maxDepthOfCrawling--;
        }
        while (!reference.isEmpty() & maxPagesToFind > 0);

        //Creating body of future csv file
        getStringBuilderFromListOfEntity(records, body);

        try {
            //save data to csv file
            csvHelper.writeDataToCSVFile(header, body, FULL_STAT_FILE_NAME);

            //When list of references are empty or we get max pages to finding - start to find top ten hits
            getTopTenTotalHitsDescOrder(header);
        } catch (IOException e) {
            logger.error("Writing data to file throw an error! " + e);
        }
    }


    private void getTopTenTotalHitsDescOrder(StringBuilder header) throws IOException {
        List<Record> topTenRecords = records.stream()
                .sorted(sortByTotalHits)
                .limit(10)
                .collect(Collectors.toList());

        StringBuilder topTen = new StringBuilder();

        getStringBuilderFromListOfEntity(topTenRecords, topTen);

        csvHelper.writeDataToCSVFile(header, topTen, TOP_TEN_STAT_FILE_NAME);

        System.out.println("Top ten hits are: \n" + header + topTen);
    }


    //Parsing html to match for every word
    private Record getLineOfCounters(String url, Document document, List<String> inputWords) {
        SearchForMatches searchForMatches = new SearchForMatchesRegexImpl();
        String bodyOfHtml = document.body().text();

        //Size if hits = count of words +1 for total sum
        long[] hits = new long[inputWords.size() + 1];
        int index = 0;
        long totalCount = 0;

        for (String word : inputWords) {
            long temp = searchForMatches.countMatchesForText(word, bodyOfHtml);
            hits[index] = temp;
            totalCount += temp;
            index++;
        }
        //last element of array - total count
        hits[hits.length - 1] = totalCount;

        return new Record(url, hits);
    }


    private void getStringBuilderFromListOfEntity(List<Record> records, StringBuilder text) {
        if (!records.isEmpty()) {
            for (Record record : records) {
                text.append(transformRecordToStringBuilder(record)).append("\n");
            }
        }
    }
}
