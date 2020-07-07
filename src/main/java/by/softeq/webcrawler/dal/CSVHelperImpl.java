package by.softeq.webcrawler.dal;

import java.io.IOException;

/**
 * The type Csv helper.
 */
public class CSVHelperImpl implements CSVHelper {

    @Override
    public synchronized void writeDataToCSVFile(StringBuilder header, StringBuilder body, String fileName) throws IOException {
        header.append("\n");

        String dataToCSV = body.toString().replaceAll(" ", ",");

        WriteToFile(header + dataToCSV, fileName);


    }

}
