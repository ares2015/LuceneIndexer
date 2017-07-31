package com.luceneIndexer.indexer.tokens;

import java.util.List;
import java.util.Set;

/**
 * Created by Oliver on 7/31/2017.
 */
public interface Tokenizer {

    List<String> getTokens(String sentence);

    List<String> splitStringIntoList(String sentence);

    String removeCommaAndDot(final String token);

    Set<Integer> getCommaIndexes(List<String> tokens);

    String decapitalize(String token);

}
