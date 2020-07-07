package service;

import by.softeq.webcrawler.service.util.JsoupParser;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Deque;

import static org.junit.Assert.assertNull;

public class JsoupParserTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void getHTMLDocumentByURL_Should_Throw_Illegal_Document_Exception() throws IOException {
        String url = "aaa.bbb.ccc";
        Document doc = JsoupParser.getHTMLDocumentByURL(url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getHTMLDocumentByURL_Should_Throw_Illegal_Document_Exception_v2() throws IOException {
        String url = null;
        Document doc = JsoupParser.getHTMLDocumentByURL(url);
    }

    @Test
    public void addLinksToQueue_Check_On_Null() {

        Document doc = null;
        Deque<String> refs = null;
        JsoupParser.addLinksToQueue(doc, refs);
        assertNull(refs);
    }
}