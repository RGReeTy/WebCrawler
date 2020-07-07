package by.softeq.webcrawler.dal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The interface Csv helper.
 */
public interface CSVHelper {


    /**
     * Write data to csv file.
     *
     * @param header   the StringBuilder
     * @param body     the StringBuilder
     * @param fileName the String
     * @throws IOException the io exception
     */
    void writeDataToCSVFile(StringBuilder header, StringBuilder body, String fileName) throws IOException;


    /**
     * Write to file.
     *
     * @param fileContent the file content
     * @param fileName    the file name
     * @throws IOException the io exception
     */
    default void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator + fileName;
        File file = new File(tempFile);
        // if file does exists, then delete and create a new file
        if (file.exists()) {

            File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
            file.renameTo(newFileName);
            file.createNewFile();

        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(fileContent);

        bw.close();
        fw.close();
    }
}
