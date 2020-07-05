package com.softeq.webcrawler;

import com.softeq.webcrawler.bean.ConfigParam;
import com.softeq.webcrawler.util.SearchForMatches;
import com.softeq.webcrawler.util.SearchForMatchesRegexImpl;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

import static com.softeq.webcrawler.util.JsoupParser.addLinksToQueue;
import static com.softeq.webcrawler.util.JsoupParser.getHTMLDocumentByURL;

public class Controller {

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

                System.out.println(lineOfCounters);
                System.out.println(reference.size());
                System.out.println(maxPagesToFind);
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

        for (String word : inputWords) {
            sb.append(" ").append(searchForMatches.countMatchesForText(word, bodyOfHtml));
        }
        return sb;
    }
}
