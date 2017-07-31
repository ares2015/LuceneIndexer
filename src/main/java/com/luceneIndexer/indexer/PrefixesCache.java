package com.luceneIndexer.indexer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 7/31/2017.
 */
public class PrefixesCache {

    public static Set<String> prefixesCacheSet = new HashSet<String>();

    public static Set<Integer> prefixesLengthsCacheSet = new HashSet<Integer>();

    static {
        prefixesCacheSet.add("0");
        prefixesCacheSet.add("1");
        prefixesCacheSet.add("2");
        prefixesCacheSet.add("3");
        prefixesCacheSet.add("4");
        prefixesCacheSet.add("5");
        prefixesCacheSet.add("6");
        prefixesCacheSet.add("7");
        prefixesCacheSet.add("8");
        prefixesCacheSet.add("9");
        prefixesLengthsCacheSet.add("1".length());

        prefixesCacheSet.add("one");
        prefixesLengthsCacheSet.add("one".length());

        prefixesCacheSet.add("two");
        prefixesLengthsCacheSet.add("two".length());

        prefixesCacheSet.add("three");
        prefixesLengthsCacheSet.add("three".length());

        prefixesCacheSet.add("four");
        prefixesLengthsCacheSet.add("four".length());

        prefixesCacheSet.add("five");
        prefixesLengthsCacheSet.add("five".length());

        prefixesCacheSet.add("six");
        prefixesLengthsCacheSet.add("six".length());

        prefixesCacheSet.add("seven");
        prefixesLengthsCacheSet.add("seven".length());

        prefixesCacheSet.add("eight");
        prefixesLengthsCacheSet.add("eight".length());

        prefixesCacheSet.add("nine");
        prefixesLengthsCacheSet.add("nine".length());

        prefixesCacheSet.add("ten");
        prefixesLengthsCacheSet.add("ten".length());

        prefixesCacheSet.add("eleven");
        prefixesLengthsCacheSet.add("eleven".length());

        prefixesCacheSet.add("twelve");
        prefixesLengthsCacheSet.add("twelve".length());

        prefixesCacheSet.add("thirteen");
        prefixesLengthsCacheSet.add("thirteen".length());

        prefixesCacheSet.add("fourteen");
        prefixesLengthsCacheSet.add("fourteen".length());

        prefixesCacheSet.add("fifteen");
        prefixesLengthsCacheSet.add("fifteen".length());

        prefixesCacheSet.add("sixteen");
        prefixesLengthsCacheSet.add("sixteen".length());

        prefixesCacheSet.add("seventeen");
        prefixesLengthsCacheSet.add("seventeen".length());

        prefixesCacheSet.add("eighteen");
        prefixesLengthsCacheSet.add("eighteen".length());

        prefixesCacheSet.add("nineteen");
        prefixesLengthsCacheSet.add("nineteen".length());

        prefixesCacheSet.add("twenty");
        prefixesLengthsCacheSet.add("twenty".length());

        prefixesCacheSet.add("thirty");
        prefixesLengthsCacheSet.add("thirty".length());

        prefixesCacheSet.add("forty");
        prefixesLengthsCacheSet.add("forty".length());

        prefixesCacheSet.add("fifty");
        prefixesLengthsCacheSet.add("fifty".length());

        prefixesCacheSet.add("sixty");
        prefixesLengthsCacheSet.add("sixty".length());

        prefixesCacheSet.add("seventy");
        prefixesLengthsCacheSet.add("seventy".length());

        prefixesCacheSet.add("eighty");
        prefixesLengthsCacheSet.add("eighty".length());

        prefixesCacheSet.add("ninety");
        prefixesLengthsCacheSet.add("ninety".length());

        prefixesCacheSet.add("hundred");
        prefixesLengthsCacheSet.add("hundred".length());

        prefixesCacheSet.add("thousand");
        prefixesLengthsCacheSet.add("thousand".length());

        prefixesCacheSet.add("million");
        prefixesLengthsCacheSet.add("million".length());

        prefixesCacheSet.add("billion");
        prefixesLengthsCacheSet.add("billion".length());

        prefixesCacheSet.add("trillion");
        prefixesLengthsCacheSet.add("trillion".length());

    }

}
