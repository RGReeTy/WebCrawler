package com.softeq.webcrawler.controller;

import com.softeq.webcrawler.bean.ConfigParam;

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
