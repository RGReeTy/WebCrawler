package com.softeq.webcrawler.controller;

import com.softeq.webcrawler.bean.ConfigParam;
import com.softeq.webcrawler.dal.CSVHelper;
import com.softeq.webcrawler.dal.CSVHelperImpl;
import com.softeq.webcrawler.service.util.SearchForMatches;
import com.softeq.webcrawler.service.util.SearchForMatchesRegexImpl;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

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
        //TODO concurrent queue
        Deque<String> reference = new ArrayDeque<String>();
        StringBuilder header = new StringBuilder(configParam.getUrl()).append(" ,").append(configParam.getWordsToFind()).append(" , TOTAL");
        StringBuilder records = new StringBuilder();
        int maxPagesToFind = configParam.getMaxPagesToFind();
        int maxDepthOfCrawling = configParam.getMaxDepthOfCrawling();


        reference.addFirst(configParam.getUrl());

        try {
            while (!reference.isEmpty() & maxPagesToFind > 0) {
                Document document = getHTMLDocumentByURL(reference.getFirst());
                StringBuilder lineOfCounters = new StringBuilder(reference.getFirst());
                lineOfCounters.append(getLineOfCounters(document, inputWords));

                if (maxDepthOfCrawling > 0) {
                    addLinksToQueue(document, reference);
                }

                records.append(lineOfCounters).append("\n");
                logger.info(lineOfCounters + " - " + reference.size() + " - " + maxPagesToFind);

                reference.removeFirst();
                maxPagesToFind--;
                maxDepthOfCrawling--;
            }


        } catch (IOException e) {
            logger.error(e);
        }

        CSVHelper csvHelper = new CSVHelperImpl();
        csvHelper.writeDataToCSVFile(header, records);


    }

    private StringBuilder getLineOfCounters(Document document, List<String> inputWords) {
        SearchForMatches searchForMatches = new SearchForMatchesRegexImpl();
        String bodyOfHtml = document.body().text();
        StringBuilder sb = new StringBuilder();
        long totalCount = 0;

        for (String word : inputWords) {
            long temp = searchForMatches.countMatchesForText(word, bodyOfHtml);

            sb.append(" ").append(temp);

            totalCount += temp;
        }

        return sb.append(" ").append(totalCount);
    }
}
