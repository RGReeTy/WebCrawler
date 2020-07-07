package bean;

import by.softeq.webcrawler.bean.Record;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RecordTest {

    String url;
    long[] hits;
    Record record;

    @Before
    public void setUp() throws Exception {
        url = "www.123.com";
        hits = new long[]{1, 2, 3, 6};
        record = new Record(url, hits);
    }

    @Test
    public void getUrl() {
        assertEquals("www.123.com", record.getUrl());
    }

    @Test
    public void setUrl() {
        record.setUrl("www.abracadabra.net");
        assertEquals("www.abracadabra.net", record.getUrl());

    }

    @Test
    public void getHits() {
        long[] anotherArr = new long[]{1, 2, 3, 6};
        assertEquals(Arrays.toString(anotherArr), Arrays.toString(record.getHits()));
    }

    @Test
    public void setHits() {
        long[] newArray = {9, 9, 9, 9};
        record.setHits(newArray);
        assertEquals(newArray, record.getHits());
    }

    @Test
    public void checkCreatingNewRecord() {
        String url = "www.123.com";
        long[] hits = {1, 2, 3, 6};
        Record record = new Record(url, hits);

        Assert.assertEquals(url, record.getUrl());
        Assert.assertEquals(hits, record.getHits());
    }
}