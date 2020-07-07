package service;

import by.softeq.webcrawler.bean.Record;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static by.softeq.webcrawler.service.RecordWorker.transformRecordToStringBuilder;

public class RecordWorkerTest {

    Record record;

    @Before
    public void setUp() throws Exception {
        String url = "aaa.bbb.ccc";
        long[] hits = {1, 2, 3, 6};
        record = new Record(url, hits);
    }

    @Test
    public void transformRecordToStringBuilder_Should_Return_StringBuilder() {
        StringBuilder sb = new StringBuilder("aaa.bbb.ccc,1,2,3,6");
        Assert.assertEquals(sb.toString(), transformRecordToStringBuilder(record).toString());
    }

    @Test(expected = NullPointerException.class)
    public void transformRecordToStringBuilder_Should_Throw_NPE() {
        StringBuilder sb = new StringBuilder("aaa.bbb.ccc,1,2,3,6");
        record.setHits(null);
        transformRecordToStringBuilder(record);

    }
}