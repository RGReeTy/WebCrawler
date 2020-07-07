package by.softeq.webcrawler.service.util;

import by.softeq.webcrawler.service.validator.InputDataValidator;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Deque;

/**
 * The type Jsoup parser.
 */
public class JsoupParser {

    private static final Logger logger = Logger.getLogger(JsoupParser.class);

    /**
     * Gets html document by url.
     *
     * @param url the url
     * @return the html document by url
     * @throws IOException the io exception
     */
    public static Document getHTMLDocumentByURL(String url) throws IOException {
        InputDataValidator.notEmpty(url);
        return Jsoup.connect(url).timeout(10000).get();

    }

    /**
     * Add links to queue.
     *
     * @param document  the document
     * @param reference the reference
     */
    public static void addLinksToQueue(Document document, Deque<String> reference) {

        if (document != null) {
            // Elements extends ArrayList<Element> - getting all ref
            Elements refs = document.getElementsByTag("a");

            //getting only absolute paths
            for (Element element : refs) {
                reference.addLast(element.attr("abs:href"));
            }
        } else {
            logger.info("Document for parsing == null!");
        }

    }


}
