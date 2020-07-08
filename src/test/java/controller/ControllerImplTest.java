package controller;

import by.softeq.webcrawler.bean.ConfigParam;
import by.softeq.webcrawler.controller.ControllerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllerImplTest {

    String url = "http://seasonvar.ru";
    String wordsToFind = "2020, новинки, аниме";
    int maxDepthOfCrawling = 1;
    int maxPagesToFind = 100;
    ConfigParam configParam;
    ControllerImpl controller = new ControllerImpl();

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
    public void integrationTest_Should_Get_2_Files_At_Dir() throws IOException {
        controller.startWebScrapping(configParam);
        Path PATH = Paths.get(System.getProperty("user.dir"));
        long countFilesCSV = Files.find(PATH, 1, (p, a) -> a.isRegularFile() && p.getFileName().toString().endsWith(".csv")).count();
        Assert.assertEquals(2, countFilesCSV);
    }


}