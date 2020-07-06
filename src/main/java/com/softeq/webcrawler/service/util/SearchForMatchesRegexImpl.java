package com.softeq.webcrawler.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Search for matches regex.
 */
public class SearchForMatchesRegexImpl implements SearchForMatches {

    //get all matches by RegEx
    @Override
    public long countMatchesForText(String word, String text) {
        long count = 0;

        Pattern pattern = Pattern.compile(word.toLowerCase());
        Matcher matcher = pattern.matcher(text.toLowerCase());
        count = matcher.results().count();

        return count;
    }
}
