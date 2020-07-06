package com.softeq.webcrawler.controller;

import com.softeq.webcrawler.bean.ConfigParam;
import com.softeq.webcrawler.util.SearchForMatches;
import com.softeq.webcrawler.util.SearchForMatchesRegexImpl;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

import static com.softeq.webcrawler.util.JsoupParser.addLinksToQueue;
import static com.softeq.webcrawler.util.JsoupParser.getHTMLDocumentByURL;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class);

    public void startWebScrapping(ConfigParam configParam) {


        List<String> inputWords = new ArrayList<>(Arrays.asList(configParam.getWordsToFind().split(",")));
        //TODO concurrent queue
        Deque<String> reference = new ArrayDeque<String>();
        int maxPagesToFind = configParam.getMaxPagesToFind();
        int maxDepthOfCrawling = configParam.getMaxDepthOfCrawling();


        reference.addFirst(configParam.getUrl());

        try {
            while (!reference.isEmpty() & maxPagesToFind > 0) {
                Document document = getHTMLDocumentByURL(reference.getFirst());
                StringBuilder lineOfCounters = new StringBuilder(configParam.getUrl());
                lineOfCounters.append(getLineOfCounters(document, inputWords));

                if (maxDepthOfCrawling > 0) {
                    addLinksToQueue(document, reference);
                }

                logger.info(lineOfCounters);
                logger.info(reference.size());
                logger.info(maxPagesToFind);

                reference.removeFirst();
                maxPagesToFind--;
                maxDepthOfCrawling--;
            }


        } catch (IOException e) {
            //TODO add logger
            e.printStackTrace();
        }


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
