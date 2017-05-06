package reco_query.reco;


import lombok.Getter;
import reco_query.entity.Entity;
import reco_query.entity.entities.ClassEntity;
import reco_query.entity.entities.InterfaceEntity;
import reco_query.entity.entities.MethodEntity;
import reco_query.factory.MapFactory;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by liwp on 2017/5/3.
 */
@Getter
public class Recommendation {
    private final Map<String, Map<Entity, Integer>> wordToMethodMap;
    private final Map<MethodEntity, Integer> methodMap;
    private final Map<ClassEntity, Integer> classMap;
    private final Map<InterfaceEntity, Integer> interfaceMap;

    private Map<Entity, Integer> methodScores;

    private Collection<String> topMethods;

    public Recommendation() {
        wordToMethodMap = MapFactory.buildAndGetWordToRefMap();
        methodMap = MapFactory.buildAndGetMethodMap();
        classMap = MapFactory.buildAndGetClassMap();
        interfaceMap = MapFactory.buildAndGetInterfaceMap();
        methodScores = methodMap.keySet().stream().collect(Collectors.toMap(Function.identity(), method -> 0));
    }

    public void calScoresFromQuerys(String [] querys) {
        for(String query : querys) {
            if(!wordToMethodMap.containsKey(query)) {
                continue;
            }
            wordToMethodMap.get(query).forEach((entity, num) -> {
                if(methodScores.containsKey(entity)) {
                    methodScores.put(entity, methodScores.get(entity) + num);
                } else {
                    methodScores.put(entity, num);
                }
            });
        }
        methodScores.forEach((method, num) -> {
            if(num > 0) {
                System.out.println(method.displayName()+num);
            }
        });
    }

    public Collection<String> recommend(String queryLine) {
        String[] queryWords = queryLine.toLowerCase().split(" ");
        calScoresFromQuerys(queryWords);
        return null;
    }

    public static void main(String args[]) {
        Recommendation reco = new Recommendation();
        String s = "index read write close open  ";
        reco.recommend(s);
        System.out.println(reco.getMethodMap().size());
        System.out.println(reco.getClassMap().size());
        System.out.println(reco.getInterfaceMap().size());
    }
}
