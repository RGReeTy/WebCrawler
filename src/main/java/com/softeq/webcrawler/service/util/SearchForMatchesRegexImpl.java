package com.softeq.webcrawler.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchForMatchesRegexImpl implements SearchForMatches {

    @Override
    public long countMatchesForText(String word, String text) {
        long count = 0;

        Pattern pattern = Pattern.compile(word.toLowerCase());
        Matcher matcher = pattern.matcher(text.toLowerCase());
        count = matcher.results().count();

        return count;
    }
}
