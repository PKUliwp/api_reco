package reco_query.factory;

import org.neo4j.graphdb.GraphDatabaseService;
import reco_query.entity.MethodEntity;
import reco_query.mapper.MethodMapper;
import reco_query.mapper.WordToMethodMapper;

import java.util.Map;

public class MapFactory {

    private MapFactory() {

    }

    public static Map<String, Map<MethodEntity, Integer>> buildAndGetWordToMethodMap() {
        GraphDatabaseService graphDb = GraphDbFactory.builder();
        WordToMethodMapper wordToMethodMapper = new WordToMethodMapper(graphDb);
        wordToMethodMapper.build();
        Map<String, Map<MethodEntity, Integer>> wordToMethodMap = wordToMethodMapper.getWordToMethodMap();
        wordToMethodMapper.close();
        return wordToMethodMap;
    }

    public static Map<MethodEntity, Integer> buildAndGetMethodMap() {
        GraphDatabaseService graphDb = GraphDbFactory.builder();
        MethodMapper methodMapper = new MethodMapper(graphDb);
        methodMapper.build();
        Map<MethodEntity, Integer> methodMap = methodMapper.getMethodMap();
        methodMapper.close();
        return methodMap;
    }
}