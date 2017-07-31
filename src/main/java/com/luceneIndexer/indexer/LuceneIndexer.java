package com.luceneIndexer.indexer;

import com.luceneIndexer.indexer.tagger.PosTagger;
import com.luceneIndexer.indexer.tagger.PosTaggerImpl;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Oliver on 7/17/2017.
 */
public class LuceneIndexer {

    private static final String DATA_PATH = "C:\\Users\\Oliver\\Documents\\NlpTrainingData\\SemanticExtraction\\LuceneTestData.txt";
//    private static final String DATA_PATH = "C:\\Users\\Oliver\\Documents\\NlpTrainingData\\SemanticExtraction\\WikipediaAggregatedData.txt";

    public static void main(String[] args) throws IOException {
        PosTagger posTagger = new PosTaggerImpl();
        int numberOfSentencesIndexed = 0;
        Directory indexDir = FSDirectory.open(new File("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\Lucene\\Index"));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_0, new StandardAnalyzer(CharArraySet.EMPTY_SET));
//        config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);

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
                    String[] tokens = sentence.split(" ");
                    List<String> tagsList = posTagger.tag(sentence);
                    if (tokens.length >= 2 && tagsList.get(0).contains("N") &&
                            (tagsList.get(0).contains("V") || tagsList.get(0).contains("IA"))) {
                        String topic = split[1];
                        numberOfSentencesIndexed++;
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
                }
                rowToIndex = br.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        int sublistSize = docList.size() / 4;

        List<Document> sublist1 = docList.subList(0, sublistSize);
        List<Document> sublist2 = docList.subList(sublistSize, sublistSize * 2);
        List<Document> sublist3 = docList.subList(sublistSize * 2, sublistSize * 3);
        List<Document> sublist4 = docList.subList(sublistSize * 3, sublistSize * 4);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable writer1 = new LuceneIndexWriter(sublist1);
        Runnable writer2 = new LuceneIndexWriter(sublist2);
        Runnable writer3 = new LuceneIndexWriter(sublist3);
        Runnable writer4 = new LuceneIndexWriter(sublist4);

        Future<?> future1 = executor.submit(writer1);
        Future<?> future2 = executor.submit(writer2);
        Future<?> future3 = executor.submit(writer3);
        Future<?> future4 = executor.submit(writer4);


        boolean areDataWritten = false;
        while (!areDataWritten) {
            areDataWritten = !future1.isDone() && !future2.isDone() && !future3.isDone() && !future4.isDone();
        }
        executor.shutdown();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(numberOfSentencesIndexed + " sentences indexed in " + (elapsedTime / 1000) / 60 + " minutes and "
                + +(elapsedTime / 1000) % 60 + " seconds");
//        PrintWriter pw1 = new PrintWriter(DATA_PATH);
//        pw1.close();

    }

}