package reco_query.reco;


import lombok.Getter;
import reco_query.entity.Entity;
import reco_query.entity.entities.ClassEntity;
import reco_query.entity.entities.InterfaceEntity;
import reco_query.entity.entities.MethodEntity;
import reco_query.factory.MapFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by liwp on 2017/5/3.
 */
@Getter
public class Recommendation {
    private final Map<String, Map<Entity, Integer>> wordToEntityMap;
    private final Map<MethodEntity, Integer> methodMap;
    private final Map<ClassEntity, Integer> classMap;
    private final Map<InterfaceEntity, Integer> interfaceMap;

    private Set<Entity> entityScores;

    public Recommendation() {
        wordToEntityMap = MapFactory.buildAndGetWordToRefMap();
        methodMap = MapFactory.buildAndGetMethodMap();
        classMap = MapFactory.buildAndGetClassMap();
        interfaceMap = MapFactory.buildAndGetInterfaceMap();
    }

    public Collection<Entity> recommend(String queryLine) {
        Collection<String> queryWords = QueryDealer.splitWords(queryLine);
        entityScores = Calculator.collectEntities(Calculator.calScoresFromQuerys(queryWords, wordToEntityMap));
        entityScores.forEach(entity -> {
            System.out.println(entity.prefix() + " " + entity.name() + " "+ entity.suffix());
            System.out.println(entity.urlPath());
        });
        return entityScores;
    }

    public static void main(String args[]) {
        Recommendation reco = new Recommendation();
        String s = "index  ";
        reco.recommend(s);
        System.out.println(reco.getMethodMap().size());
        System.out.println(reco.getClassMap().size());
        System.out.println(reco.getInterfaceMap().size());
    }
}
