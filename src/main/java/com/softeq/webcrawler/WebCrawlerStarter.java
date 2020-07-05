package com.softeq.webcrawler;

import com.softeq.webcrawler.util.SearchForMatches;
import com.softeq.webcrawler.util.SearchForMatchesRegexImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebCrawlerStarter {

    public static void main(String[] args) {

        final String TESLACOM = "https://www.tesla.com/elon-musk";
        final String WIKI_ELON_MUSK = "https://en.wikipedia.org/wiki/Elon_Musk";

        //part 1
        String inputString = "Tesla, Musk, Gigafactory, Elon Mask";
        List<String> inputWords = new ArrayList<>(Arrays.asList(inputString.split(",")));

        SearchForMatches searchForMatches = new SearchForMatchesRegexImpl();

        try {
            Document teslacom = Jsoup.connect(TESLACOM).get();
            Document wiki_elon_musk = Jsoup.connect(WIKI_ELON_MUSK).get();

            String teslacomStr = teslacom.getElementsByTag("body").text();


            for (String word : inputWords) {
                System.out.println("Word: " + word + " = " + searchForMatches.countMatchesForText(word, teslacomStr));

            }


        } catch (IOException e) {
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
