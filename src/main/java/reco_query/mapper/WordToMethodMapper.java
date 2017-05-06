package reco_query.mapper;

import lombok.Getter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import reco_query.entity.MethodEntity;
import reco_query.entity.utils.MapperUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwp on 2017/5/3.
 */
public class WordToMethodMapper implements Mapper{

    private final GraphDatabaseService graphDb;
    private final int upperLengthLimit = 15;
    private final int lowerLengthLimit = 3;

    private Map<String, Integer> wordMap = new HashMap<>();
    @Getter
    private Map<String, Map<MethodEntity, Integer>> wordToMethodMap = new HashMap<>();

    public WordToMethodMapper(final GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }

    @Override
    public void build() {
        buildWordToMethodMap();
    }

    private void buildWordToMethodMap() {
        buildWordMap();
        wordMap.keySet().stream().forEach(word -> {
            wordToMethodMap.put(word, new HashMap<>());
        });
        try (Transaction tx = graphDb.beginTx()) {
            graphDb.getAllNodes().stream().forEach(node -> {
                if(MapperUtils.checkNodeLabel(node, "StackOverflow") && node.getProperties("body").size() > 0) {
                    Collection<MethodEntity> methodEntities = MapperUtils.getRefMethods(node);
                    Collection<String> words = MapperUtils.getBodyWords(node);
                    words.forEach(word -> {
                        Map<MethodEntity, Integer> wordToMethods = wordToMethodMap.get(word);
                        if(wordToMethods != null) {
                            methodEntities.forEach(method -> {
                                if (wordToMethods.containsKey(method)) {
                                    wordToMethods.put(method, wordToMethods.get(method) + 1);
                                } else {
                                    wordToMethods.put(method, 1);
                                }
                            });
                        }
                    });
                }
            });
            tx.success();
        }
    }

    private void buildWordMap() {
        wordMap = new HashMap<>();
        try (Transaction tx = graphDb.beginTx()) {
            graphDb.getAllNodes().stream().forEach(node -> {
                if(MapperUtils.checkNodeLabel(node, "StackOverflow") && (node.getProperties("body").size() > 0)) {
                    Collection<String> words = MapperUtils.getBodyWords(node);
                    words.forEach(word -> {
                        if(word.length() < upperLengthLimit && word.length() > lowerLengthLimit) {
                            if (wordMap.containsKey(word)) {
                                wordMap.put(word, wordMap.get(word) + 1);
                            } else {
                                wordMap.put(word, 1);
                            }
                        }
                    });
                }
            });
            tx.success();
        }
    }



    @Override
    public void close() {
        wordMap.clear();
        graphDb.shutdown();
    }

}
