package com.softeq.webcrawler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupParser {

    public static String parseHTMLBody(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        return document.body().text();
    }


}
