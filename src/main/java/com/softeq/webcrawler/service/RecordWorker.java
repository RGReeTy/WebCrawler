package com.softeq.webcrawler.service;

import com.softeq.webcrawler.bean.Record;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RecordWorker {

    public static StringBuilder getRecordAsStringBuilder(Record record) {
        StringBuilder recordToSB = new StringBuilder(record.getUrl()).append(",");
        recordToSB.append(Arrays.stream(record.getHits())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
        return recordToSB;
    }

}
