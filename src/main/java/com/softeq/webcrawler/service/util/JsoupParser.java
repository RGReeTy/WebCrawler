package com.softeq.webcrawler.service.util;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Deque;

public class JsoupParser {

    private static final Logger logger = Logger.getLogger(JsoupParser.class);

    public static Document getHTMLDocumentByURL(String url) throws IOException {

        return Jsoup.connect(url).get();

    }

    public static void addLinksToQueue(Document document, Deque<String> reference) {

        if (document != null) {
            // Elements extends ArrayList<Element>
            Elements refs = document.getElementsByTag("a");

            for (Element element : refs) {
                reference.addLast(element.attr("abs:href"));
            }
        } else {
            logger.info("Document for parsing is came == null!");
        }

    }


}
