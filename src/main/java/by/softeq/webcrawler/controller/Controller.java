package by.softeq.webcrawler.controller;

import by.softeq.webcrawler.bean.ConfigParam;

/**
 * The interface Controller.
 */
public interface Controller {

    /**
     * Start web scrapping.
     *
     * @param configParam the config param
     */
    void startWebScrapping(ConfigParam configParam);
}
