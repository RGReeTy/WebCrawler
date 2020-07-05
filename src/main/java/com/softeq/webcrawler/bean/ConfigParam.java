package com.softeq.webcrawler.bean;

import java.io.Serializable;

public class ConfigParam implements Serializable {

    //TODO sv uid

    private String url;
    private String wordsToFind;
    private int maxDepthOfCrawling;
    private int maxPagesToFind;

    private ConfigParam() {
    }

    public String getUrl() {
        return url;
    }

    public String getWordsToFind() {
        return wordsToFind;
    }

    public int getMaxDepthOfCrawling() {
        return maxDepthOfCrawling;
    }

    public int getMaxPagesToFind() {
        return maxPagesToFind;
    }

    public static Builder newBuilder() {
        return new ConfigParam().new Builder();
    }

    public class Builder {
        public Builder setUrl(String url) {
            ConfigParam.this.url = url;
            return this;
        }

        public Builder setWordsToFind(String wordsToFind) {
            ConfigParam.this.wordsToFind = wordsToFind;
            return this;
        }

        public Builder setMaxDepthOfCrawling(int maxDepthOfCrawling) {
            ConfigParam.this.maxDepthOfCrawling = maxDepthOfCrawling;
            return this;
        }

        public Builder setMaxPagesToFind(int maxPagesToFind) {
            ConfigParam.this.maxPagesToFind = maxDepthOfCrawling;
            return this;
        }

        public ConfigParam build() {
            return ConfigParam.this;
        }
    }
}