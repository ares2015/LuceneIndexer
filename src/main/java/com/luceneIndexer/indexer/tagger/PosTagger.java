package com.luceneIndexer.indexer.tagger;

import java.util.List;

/**
 * Created by Oliver on 7/31/2017.
 */
public interface PosTagger {

    List<String> tag(String inputSentence);

}
