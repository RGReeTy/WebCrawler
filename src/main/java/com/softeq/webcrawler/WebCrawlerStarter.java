package com.softeq.webcrawler;

import com.softeq.webcrawler.bean.ConfigParam;

public class WebCrawlerStarter {

    public static void main(String[] args) {

        String url = "https://www.tesla.com/elon-musk";
        String wordsToFind = "Tesla, Musk, Gigafactory, Elon Mask";
        int maxDepthOfCrawling = 1;
        int maxPagesToFind = 100;

        ConfigParam configParam = ConfigParam.newBuilder()
                .setUrl(url)
                .setWordsToFind(wordsToFind)
                .setMaxDepthOfCrawling(maxDepthOfCrawling)
                .setMaxPagesToFind(maxPagesToFind)
                .build();

        Controller controller = new Controller();
        controller.startWebScrapping(configParam);

        //String WIKI_ELON_MUSK = "https://en.wikipedia.org/wiki/Elon_Musk";

    }
}
