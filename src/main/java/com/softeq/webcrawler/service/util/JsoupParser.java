package com.softeq.webcrawler.service.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Deque;

public class JsoupParser {

    public static Document getHTMLDocumentByURL(String url) throws IOException {

        return Jsoup.connect(url).get();

    }

    public static void addLinksToQueue(Document document, Deque<String> reference) {
        // Elements extends ArrayList<Element>
        Elements refs = document.getElementsByTag("a");

        for (Element element : refs) {
            reference.addLast(element.attr("abs:href"));
        }

    }


}
