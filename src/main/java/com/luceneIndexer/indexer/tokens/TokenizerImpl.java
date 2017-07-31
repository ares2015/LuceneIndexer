package com.luceneIndexer.indexer.tokens;

import java.util.*;

/**
 * Created by Oliver on 7/31/2017.
 */
public class TokenizerImpl implements Tokenizer {

    @Override
    public List<String> getTokens(String sentence) {
        return Arrays.asList(sentence.split("\\ "));
    }

    @Override
    public List<String> splitStringIntoList(String sentence) {
        String[] tokTmp;
        tokTmp = sentence.split("\\ ");
        final List<String> tokens = removeEmptyStringInSentence(tokTmp);
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            token = removeEmptyCharsFromToken(token);
            filteredTokens.add(token);
        }
        return filteredTokens;
    }

    /**
     * If token ends with "," then it is converted to the char array
     * and comma is removed by copying the char array into new array
     * ignoring the last comma element.
     *
     * @param token ending with comma.
     * @return String token without comma.
     */
    @Override
    public String removeCommaAndDot(final String token) {
        char[] charTmp;
        charTmp = token.toCharArray();
        final char[] charToken = new char[charTmp.length - 1];
        for (int i = 0; i < charTmp.length - 1; i++) {
            charToken[i] = charTmp[i];
        }
        final String tokenWithoutComma = new String(charToken);
        return tokenWithoutComma;
    }

    public Set<Integer> getCommaIndexes(List<String> tokens) {
        Set<Integer> indexes = new HashSet<Integer>();
        int index = 0;
        for (String token : tokens) {
            if (token.endsWith(",")) {
                indexes.add(index);
            }
            index++;
        }
        return indexes;
    }

    public String decapitalize(String token) {
        char c[] = token.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }

    /**
     * Removes empty String that is located in sentences with index > 0
     * that is created by empty space behind each sentence (space between
     * end of the sentence and the start of the new sentence). It would be more
     * efficient to remove only first token (empty string) but this loop guarantees
     * that all accidental empty strings are removed.
     *
     * @param tokens Array of tokens with empty strings.
     * @return List of tokens without empty strings.
     */
    private List<String> removeEmptyStringInSentence(final String[] tokens) {
        final List<String> listTokens = new ArrayList<String>();
        for (final String token : tokens) {
            if (!token.equals("")) {
                listTokens.add(token);
            }
        }
        return listTokens;
    }

    private String removeEmptyCharsFromToken(String token) {
        char[] charTmp;
        charTmp = token.toCharArray();
        int numberOfEmptyChars = 0;
        for (int i = 0; i <= charTmp.length - 1; i++) {
            if (charTmp[i] == '\uFEFF') {
                numberOfEmptyChars++;
            }
        }
        return new String(Arrays.copyOfRange(charTmp, numberOfEmptyChars, charTmp.length));
    }
}
