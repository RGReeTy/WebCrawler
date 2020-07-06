package com.softeq.webcrawler.dal;

import java.io.IOException;


public class CSVHelperImpl implements CSVHelper {

    @Override
    public void writeDataToCSVFile(StringBuilder header, StringBuilder body) {
        header.append("\n");
        String dataToCSV = body.toString().replaceAll(" ", ",");

        try {
            WriteToFile(header + dataToCSV, "CsvStatOfHits.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
