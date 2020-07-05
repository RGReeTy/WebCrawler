package com.softeq.webcrawler;

import com.softeq.webcrawler.bean.ConfigParam;
import com.softeq.webcrawler.util.SearchForMatches;
import com.softeq.webcrawler.util.SearchForMatchesRegexImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.softeq.webcrawler.util.JsoupParser.parseHTMLBody;

public class Controller {

    public void startWebScrapping(ConfigParam configParam) {

        List<String> inputWords = new ArrayList<>(Arrays.asList(configParam.getWordsToFind().split(",")));
        SearchForMatches searchForMatches = new SearchForMatchesRegexImpl();

        try {
            String bodyOfHtml = parseHTMLBody(configParam.getUrl());
            StringBuilder lineOfCounters = new StringBuilder(configParam.getUrl());

            for (String word : inputWords) {
                lineOfCounters.append(" ").append(searchForMatches.countMatchesForText(word, bodyOfHtml));

            }
            System.out.println(lineOfCounters);

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
