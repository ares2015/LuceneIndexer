package com.luceneIndexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Oliver on 7/31/2017.
 */
public class SimpleSearchTest {

    @Test
    public void testSimpleSearch() throws IOException, ParseException {
        Directory directory = new SimpleFSDirectory(new File("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Lucene\\Index"));
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexReader reader = DirectoryReader.open(directory);
        Query q = new QueryParser("sentence", analyzer).parse("Lincoln was");
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, 10);
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document doc = searcher.doc(docId);
            System.out.println(doc.get("sentence"));
        }
    }
}
