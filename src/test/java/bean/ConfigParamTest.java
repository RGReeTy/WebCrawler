package bean;

import by.softeq.webcrawler.bean.ConfigParam;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigParamTest {
    String url = "https://softeq.by";
    String wordsToFind = "technology, future, possibility";
    int maxDepthOfCrawling = 5;
    int maxPagesToFind = 500;
    ConfigParam configParam;

    @Before
    public void setUp() throws Exception {
        configParam = ConfigParam.newBuilder()
                .setUrl(url)
                .setWordsToFind(wordsToFind)
                .setMaxDepthOfCrawling(maxDepthOfCrawling)
                .setMaxPagesToFind(maxPagesToFind)
                .build();
    }

    @Test
    public void getUrl() {
        assertEquals("https://softeq.by", configParam.getUrl());
    }

    @Test
    public void getWordsToFind() {
        assertEquals("technology"+", "+"future"+", "+"possibility", configParam.getWordsToFind());
    }

    @Test
    public void getMaxDepthOfCrawling() {
        assertEquals(5, configParam.getMaxDepthOfCrawling());
    }

    @Test
    public void getMaxPagesToFind() {
        assertEquals(500, configParam.getMaxPagesToFind());
    }

    @Test
    public void newBuilder() {
         url = "https://tut.by";
         wordsToFind = "news";
         maxDepthOfCrawling = 10;
         maxPagesToFind = 1000;
        ConfigParam testerParam = ConfigParam.newBuilder()
                .setUrl(url)
                .setWordsToFind(wordsToFind)
                .setMaxDepthOfCrawling(maxDepthOfCrawling)
                .setMaxPagesToFind(maxPagesToFind)
                .build();

        assertEquals(url, testerParam.getUrl());
        assertEquals(wordsToFind, testerParam.getWordsToFind());
        assertEquals(maxDepthOfCrawling, testerParam.getMaxDepthOfCrawling());
        assertEquals(maxPagesToFind, testerParam.getMaxPagesToFind());

    }
}