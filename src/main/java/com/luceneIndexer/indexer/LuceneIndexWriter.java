package com.luceneIndexer.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oliver on 7/21/2017.
 */
public class LuceneIndexWriter implements Runnable {

    Directory indexDir = FSDirectory.open(new File("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Lucene\\Index"));

    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_0, new StandardAnalyzer(CharArraySet.EMPTY_SET));

    private List<Document> docList;

    public LuceneIndexWriter(List<Document> docList) throws IOException {
        this.docList = docList;
    }

    @Override
    public void run() {
        write();
    }

    public void write() {
        try {
            IndexWriter writer = new org.apache.lucene.index.IndexWriter(indexDir, config);
            int count = 0;
            int nrWrittenSentences = 0;
            for (Document d : docList) {
                System.out.println("Writing into index: " + d.getField("sentence"));
                writer.addDocument(d);
                nrWrittenSentences++;
                System.out.println("Number of written sentences: " + nrWrittenSentences);
                writer.commit();
                // make sure we get 2 segments
//                if (++count % 5 == 0) {
//                    writer.commit();
//                }
            }
//            writer.commit();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}