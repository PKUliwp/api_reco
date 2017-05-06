package reco_query.mapper.mappers;

import lombok.Getter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import reco_query.entity.entities.ClassEntity;
import reco_query.mapper.Mapper;
import reco_query.mapper.utils.MapperUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwp on 2017/5/6.
 */
public class ClassMapper implements Mapper {
    private final GraphDatabaseService graphDb;
    @Getter
    private Map<ClassEntity, Integer> classMap = new HashMap<>();

    public ClassMapper(GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }

    @Override
    public void build() {
        buildClassMap();
    }

    private void buildClassMap() {
        try (Transaction tx = graphDb.beginTx()) {
            graphDb.getAllNodes().stream().forEach(node -> {
                if(MapperUtils.checkNodeLabel(node, "Class")) {
                    ClassEntity classEntity = MapperUtils.buildClass(node);
                    if(classMap.containsKey(classEntity)) {
                        classMap.put(classEntity, classMap.get(classEntity) + 1);
                    } else {
                        classMap.put(classEntity, 1);
                    }
                }
            });
            tx.success();
        }
    }

    @Override
    public void close() {
        graphDb.shutdown();
    }
}
