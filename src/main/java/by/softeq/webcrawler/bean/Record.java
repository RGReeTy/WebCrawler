package by.softeq.webcrawler.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Record. Every record represents: [url []hits]
 */
public class Record implements Serializable {

    private static final long serialVersionUID = -488948408549178971L;
    private String url;
    private long[] hits;

    /**
     * Instantiates a new Record.
     *
     * @param url  the url
     * @param hits the hits
     */
    public Record(String url, long[] hits) {
        this.url = url;
        this.hits = hits;
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
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get hits long [ ].
     *
     * @return the long [ ]
     */
    public long[] getHits() {
        return hits;
    }

    /**
     * Sets hits.
     *
     * @param hits the hits
     */
    public void setHits(long[] hits) {
        this.hits = hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(url, record.url) &&
                Arrays.equals(hits, record.hits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(url);
        result = 31 * result + Arrays.hashCode(hits);
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "url='" + url + '\'' +
                ", hits=" + Arrays.toString(hits) +
                '}';
    }
}
