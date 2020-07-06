package com.softeq.webcrawler.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Config param.
 */
public class ConfigParam implements Serializable {
    private static final long serialVersionUID = -9116286022027073155L;

    private String url;
    private String wordsToFind;
    private int maxDepthOfCrawling;
    private int maxPagesToFind;

    private ConfigParam() {
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets words to find.
     *
     * @return the words to find
     */
    public String getWordsToFind() {
        return wordsToFind;
    }

    /**
     * Gets max depth of crawling.
     *
     * @return the max depth of crawling
     */
    public int getMaxDepthOfCrawling() {
        return maxDepthOfCrawling;
    }

    /**
     * Gets max pages to find.
     *
     * @return the max pages to find
     */
    public int getMaxPagesToFind() {
        return maxPagesToFind;
    }

    /**
     * New builder builder.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new ConfigParam().new Builder();
    }

    /**
     * The type Builder.
     */
    public class Builder {
        /**
         * Sets url.
         *
         * @param url the url
         * @return the url
         */
        public Builder setUrl(String url) {
            ConfigParam.this.url = url;
            return this;
        }

        /**
         * Sets words to find.
         *
         * @param wordsToFind the words to find
         * @return the words to find
         */
        public Builder setWordsToFind(String wordsToFind) {
            ConfigParam.this.wordsToFind = wordsToFind;
            return this;
        }

        /**
         * Sets max depth of crawling.
         *
         * @param maxDepthOfCrawling the max depth of crawling
         * @return the max depth of crawling
         */
        public Builder setMaxDepthOfCrawling(int maxDepthOfCrawling) {
            ConfigParam.this.maxDepthOfCrawling = maxDepthOfCrawling;
            return this;
        }

        /**
         * Sets max pages to find.
         *
         * @param maxPagesToFind the max pages to find
         * @return the max pages to find
         */
        public Builder setMaxPagesToFind(int maxPagesToFind) {
            ConfigParam.this.maxPagesToFind = maxPagesToFind;
            return this;
        }

        /**
         * Build config param.
         *
         * @return the config param
         */
        public ConfigParam build() {
            return ConfigParam.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigParam that = (ConfigParam) o;
        return maxDepthOfCrawling == that.maxDepthOfCrawling &&
                maxPagesToFind == that.maxPagesToFind &&
                Objects.equals(url, that.url) &&
                Objects.equals(wordsToFind, that.wordsToFind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, wordsToFind, maxDepthOfCrawling, maxPagesToFind);
    }

    @Override
    public String toString() {
        return "ConfigParam{" +
                "url='" + url + '\'' +
                ", wordsToFind='" + wordsToFind + '\'' +
                ", maxDepthOfCrawling=" + maxDepthOfCrawling +
                ", maxPagesToFind=" + maxPagesToFind +
                '}';
    }
}