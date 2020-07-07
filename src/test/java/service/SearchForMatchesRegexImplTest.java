package service;

import by.softeq.webcrawler.service.util.SearchForMatchesRegexImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearchForMatchesRegexImplTest {

    private SearchForMatchesRegexImpl searchForMatches;

    @Before
    public void setUp() {
        searchForMatches = new SearchForMatchesRegexImpl();
    }

    @Test
    public void countMatchesForText_Should_Return_Match_At_Long_Value() {
        String text1 = "TExt text TUXU tex esgsdgsg extestettextTEXT";
        String text2 = "text";
        String find = "text";

        Assert.assertSame(searchForMatches.countMatchesForText(find, text1), 4L);
        Assert.assertSame(searchForMatches.countMatchesForText(find, text2), 1L);
    }

    @Test
    public void countMatchesForText_Should_Return_Zero() {
        String text1 = "lololololololol";
        String find = "softeq";

        Assert.assertSame(searchForMatches.countMatchesForText(find, text1), 0L);
    }
}