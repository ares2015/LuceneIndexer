package com.luceneIndexer.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 7/17/2017.
 */
public class LuceneIndexer {

    //    private static final String DATA_PATH = "C:\\Users\\Oliver\\Documents\\NlpTrainingData\\SemanticExtraction\\LuceneTestData.txt";
    private static final String DATA_PATH = "C:\\Users\\Oliver\\Documents\\NlpTrainingData\\SemanticExtraction\\WikipediaAggregatedData.txt";

    public static void main(String[] args) throws IOException {
        int numberOfSentences = 0;
        Directory indexDir = FSDirectory.open(new File("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Lucene\\Index"));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_0, new StandardAnalyzer(CharArraySet.EMPTY_SET));
        config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);

        List<Document> docList = new ArrayList<Document>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(DATA_PATH));
//            br = new BufferedReader(new FileReader("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingData\\DummyData.txt"));
//            br = new BufferedReader(new FileReader("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingData\\WikiWord2VecFile.txt"));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        long startTime = System.currentTimeMillis();
        try {
            String rowToIndex = br.readLine();
            while (rowToIndex != null) {
                if (!"".equals(rowToIndex)) {
                    String[] split = rowToIndex.split("#");
                    String sentence = split[0];
                    String topic = split[1];
                    numberOfSentences++;
                    try {
                        Document doc = new Document();
                        System.out.println("Indexing -> sentence: " + sentence + ", topic: " + topic);
                        doc.add(new TextField("sentence", sentence, Field.Store.YES));
//                        doc.add(new TextField("topic", topic, Field.Store.YES));
                        docList.add(doc);
                    } catch (Exception e) {
                        rowToIndex = br.readLine();
                    }
                }
                rowToIndex = br.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        try {
            IndexWriter writer = new IndexWriter(indexDir, config);
            int count = 0;
            int nrWrittenSentences = 0;
            for (Document d : docList) {
                System.out.println("Writing into index: " + d.getField("sentence"));
                writer.addDocument(d);
                nrWrittenSentences++;
                System.out.println("Number of written sentences: " + nrWrittenSentences);
                // make sure we get 2 segments
                if (++count % 5 == 0) {
                    writer.commit();
                }
            }
            writer.commit();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(numberOfSentences + " sentences indexed in " + (elapsedTime / 1000) / 60 + " minutes and "
                + +(elapsedTime / 1000) % 60 + " seconds");
    }

}