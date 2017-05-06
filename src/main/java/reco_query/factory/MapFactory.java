package reco_query.factory;

import org.neo4j.graphdb.GraphDatabaseService;
import reco_query.entity.Entity;
import reco_query.entity.entities.ClassEntity;
import reco_query.entity.entities.InterfaceEntity;
import reco_query.entity.entities.MethodEntity;
import reco_query.mapper.mappers.ClassMapper;
import reco_query.mapper.mappers.InterfaceMapper;
import reco_query.mapper.mappers.MethodMapper;
import reco_query.mapper.mappers.WordToRefMapper;

import java.util.Map;

public class MapFactory {

    private MapFactory() {

    }

    public static Map<String, Map<Entity, Integer>> buildAndGetWordToRefMap() {
        GraphDatabaseService graphDb = GraphDbFactory.builder();
        WordToRefMapper wordToRefMapper = new WordToRefMapper(graphDb);
        wordToRefMapper.build();
        Map<String, Map<Entity, Integer>> wordToMethodMap = wordToRefMapper.getWordToMethodMap();
        wordToRefMapper.close();
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

    public static Map<InterfaceEntity, Integer> buildAndGetInterfaceMap() {
        GraphDatabaseService graphDb = GraphDbFactory.builder();
        InterfaceMapper interfaceMapper = new InterfaceMapper(graphDb);
        interfaceMapper.build();
        Map<InterfaceEntity, Integer> interfaceMap = interfaceMapper.getInterfaceMap();
        interfaceMapper.close();
        return interfaceMap;
    }

    public static Map<ClassEntity, Integer> buildAndGetClassMap() {
        GraphDatabaseService graphDb = GraphDbFactory.builder();
        ClassMapper classMapper = new ClassMapper(graphDb);
        classMapper.build();
        Map<ClassEntity, Integer> classMap = classMapper.getClassMap();
        classMapper.close();
        return classMap;
    }
}