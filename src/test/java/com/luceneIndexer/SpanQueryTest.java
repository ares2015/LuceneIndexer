package com.luceneIndexer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermContext;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.search.spans.Spans;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 7/17/2017.
 */
public class SpanQueryTest {

    @Test
    public void test() throws IOException {
        Directory directory = new SimpleFSDirectory(new File("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Lucene\\Index"));
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        SpanTermQuery directed = new SpanTermQuery(new Term("sentence", "directed"));
        SpanTermQuery elMariachi = new SpanTermQuery(new Term("sentence", "film"));

        SpanNearQuery nearQueryMariachi = new SpanNearQuery(new SpanQuery[]{directed, elMariachi}, 100, true);

        Map<Term, TermContext> termContextsMariachi = new HashMap<>();
        Spans spansMariachi = nearQueryMariachi.getSpans(reader.getContext().leaves().get(0), null, termContextsMariachi);
        List<String> logMariachi = dumpSpans(spansMariachi);
        System.out.println(logMariachi.get(0));


        SpanTermQuery was = new SpanTermQuery(new Term("sentence", "was"));
        SpanTermQuery washington = new SpanTermQuery(new Term("sentence", "washington"));

        SpanNearQuery nearQueryWashington = new SpanNearQuery(new SpanQuery[]{washington, was}, 100, true);

        Map<Term, TermContext> termContextsWashington = new HashMap<>();
        Spans spansWashington = nearQueryWashington.getSpans(reader.getContext().leaves().get(0), null, termContextsWashington);
        List<String> logWashington = dumpSpans(spansWashington);
        System.out.println(logWashington.get(0));


//        SpanTermQuery frog = new SpanTermQuery(new Term("span", "frog"));
//        SpanTermQuery diet = new SpanTermQuery(new Term("span", "diet"));
//
//        SpanNearQuery nearQueryFrog = new SpanNearQuery(new SpanQuery[]{frog, diet}, 100, true);
//        Map<Term, TermContext> termContextsFrog = new HashMap<>();
//        Spans spansFrog = nearQueryFrog.getSpans(reader.getContext().leaves().get(0), null, termContextsFrog);
//        List<String> logFrog = dumpSpans(spansFrog);
//        System.out.println(logFrog.get(0));
//
        reader.close();
    }


    private static List<String> dumpSpans(Spans spans) throws IOException {
        Directory directory = new SimpleFSDirectory(new File("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Lucene\\Index"));
        IndexReader reader = DirectoryReader.open(directory);
        List<String> result = new ArrayList<String>();
        while (spans.next()) {
            int id = spans.doc();
            Document doc = reader.document(id);
            Analyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);

            TokenStream stream = analyzer.tokenStream("", new StringReader(doc.get("sentence")));
            stream.reset();

            StringBuffer buffer = new StringBuffer();

            int i = 0;
            while (stream.incrementToken()) {
                if (i == spans.start()) {
                    buffer.append("<");
                }
                buffer.append(stream.getAttribute(CharTermAttribute.class).toString());
                if (i + 1 == spans.end()) {
                    buffer.append(">");
                }
                buffer.append(" ");
                i++;
            }
            result.add(buffer.toString());
        }
        return result;
    }
}
