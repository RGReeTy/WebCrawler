package com.softeq.webcrawler.service.util;

/**
 * The interface Search for matches.
 */
public interface SearchForMatches {

    /**
     * Count matches for text, result is long.
     *
     * @param word the String - searching word
     * @param text the String - text for searching
     * @return the long
     */
    long countMatchesForText(String word, String text);
}
