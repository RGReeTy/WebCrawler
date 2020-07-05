package com.softeq.webcrawler;

import com.softeq.webcrawler.bean.ConfigParam;
import com.softeq.webcrawler.util.SearchForMatches;
import com.softeq.webcrawler.util.SearchForMatchesRegexImpl;

import java.io.IOException;
import java.util.*;

import static com.softeq.webcrawler.util.JsoupParser.parseHTMLBody;

public class Controller {

    public void startWebScrapping(ConfigParam configParam) {

        SearchForMatches searchForMatches = new SearchForMatchesRegexImpl();

        List<String> inputWords = new ArrayList<>(Arrays.asList(configParam.getWordsToFind().split(",")));
        //TODO concurrent queue
        Deque<String> reference = new ArrayDeque<String>();
        reference.addFirst(configParam.getUrl());


        try {
            while (!reference.isEmpty()) {
                String bodyOfHtml = parseHTMLBody(reference.getFirst());
                StringBuilder lineOfCounters = new StringBuilder(configParam.getUrl());

                for (String word : inputWords) {
                    lineOfCounters.append(" ").append(searchForMatches.countMatchesForText(word, bodyOfHtml));
                }
                System.out.println(lineOfCounters);
                reference.removeFirst();
            }


        } catch (IOException e) {
            //TODO add logger
            e.printStackTrace();
        }

//        try {
//            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Elon_Musk").get();
//
//            // Elements extends ArrayList<Element>.
//            Elements aElements = doc.getElementsByTag("a");
//
//            for (Element aElement : aElements) {
//                String href = aElement.attr("href");
//                String text = aElement.text();
//                System.out.println(text);
//                System.out.println("\t" + href);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
