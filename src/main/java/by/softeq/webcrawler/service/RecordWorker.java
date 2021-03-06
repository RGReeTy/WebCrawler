package by.softeq.webcrawler.service;

import by.softeq.webcrawler.bean.Record;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The type Record worker.
 */
public class RecordWorker {


    /**
     * Transform record to stringBuilder.
     *
     * @param record the record
     * @return the string builder
     */
    public static StringBuilder transformRecordToStringBuilder(Record record) {
        StringBuilder recordToSB = new StringBuilder(record.getUrl()).append(",");
        recordToSB.append(Arrays.stream(record.getHits())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
        return recordToSB;
    }

    /**
     * The Comparator sortByTotalHits ar desc order.
     */
    public static Comparator<Record> sortByTotalHits = (o1, o2) -> {
        long lastElementO1 = o1.getHits()[o1.getHits().length - 1];
        long lastElementO2 = o2.getHits()[o2.getHits().length - 1];

        return (int) (lastElementO2 - lastElementO1);
    };


}


