package com.luceneIndexer.indexer.cache;

import com.luceneIndexer.indexer.tags.StanfordTags;
import com.luceneIndexer.indexer.tags.Tags;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver on 7/31/2017.
 */
public class TagsConversionCache {

    public static Map<String, String> cache = new HashMap<String, String>();

    static {
        cache.put(StanfordTags.NOUN, Tags.NOUN);
        cache.put(StanfordTags.NOUN_PLURAL, Tags.NOUN);
        cache.put(StanfordTags.PROPER_NOUN_SINGULAR, Tags.NOUN);
        cache.put(StanfordTags.PROPER_NOUN_PLURAL, Tags.NOUN);

        cache.put(StanfordTags.VERB_BASE_FORM, Tags.VERB);
        cache.put(StanfordTags.VERB_3RD_PERSON_SINGULAR_PRESENT, Tags.VERB);
        cache.put(StanfordTags.VERB_GERUND_PRESENT_PARTICIPLE, Tags.VERB_ING);
        cache.put(StanfordTags.VERB_NON_3RD_PERSON_SINGULAR_PRESENT, Tags.VERB);

        cache.put(StanfordTags.VERB_PAST_TENSE, Tags.VERB_ED);
        cache.put(StanfordTags.VERB_PAST_PARTICIPLE, Tags.VERB_ED);

        cache.put(StanfordTags.ADJECTIVE, Tags.ADJECTIVE);
        cache.put(StanfordTags.ADJECTIVE_COMPARATIVE, Tags.ADJECTIVE);
        cache.put(StanfordTags.ADJECTIVE_SUPERLATIVE, Tags.ADJECTIVE);

        cache.put(StanfordTags.ADVERB, Tags.ADVERB);
        cache.put(StanfordTags.ADVERB_COMPARATIVE, Tags.ADVERB);
        cache.put(StanfordTags.ADVERB_SUPERLATIVE, Tags.ADVERB);

        cache.put(StanfordTags.CARDINAL_NUMBER, Tags.NUMBER);

        cache.put(StanfordTags.DETERMINER, Tags.DETERMINER);

        cache.put(StanfordTags.EXISTENTIAL_THERE, Tags.THERE);

        cache.put(StanfordTags.MODAL, Tags.MODAL_VERB);

        cache.put(StanfordTags.TO, Tags.TO);

        cache.put(StanfordTags.PERSONAL_PRONOUN, Tags.PRONOUN_PERSONAL);

        cache.put(StanfordTags.POSSESIVE_PRONOUN, Tags.PRONOUN_POSSESIVE);

        cache.put(StanfordTags.WH_PRONOUN, Tags.WH_PRONOUN);

        cache.put(StanfordTags.WH_ADVERB, Tags.WH_ADVERB);

        cache.put(StanfordTags.POSSESSIVE_WH_PRONOUN, Tags.WH_PRONOUN_POSSESSIVE);

        cache.put(StanfordTags.WH_DETERMINER, Tags.WH_DETERMINER);

        cache.put(StanfordTags.COORDINATING_CONJUNCTION, Tags.CONJUNCTION);

        cache.put(StanfordTags.PREPOSITION_SUB_CONJUNCTION, Tags.CONJUNCTION);

        cache.put(StanfordTags.PREPOSITION_SUB_CONJUNCTION, Tags.CONJUNCTION);

        cache.put(StanfordTags.FOREIGN_WORD, Tags.FOREIGN_WORD);

        cache.put(StanfordTags.INTERJECTION, Tags.INTERJECTION);

        cache.put(StanfordTags.PREDETERMINER, Tags.PREDETERMINER);

        cache.put(StanfordTags.PARTICLE, Tags.PARTICLE);

        cache.put(StanfordTags.SYMBOL, Tags.SYMBOL);

        cache.put(StanfordTags.LIST_ITEM_MARKER, Tags.LIST_ITEM_MARKER);


        cache.put(",", "");

    }
}
