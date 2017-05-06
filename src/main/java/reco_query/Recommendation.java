package reco_query;


import reco_query.entity.MethodEntity;
import reco_query.factory.MapFactory;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by liwp on 2017/5/3.
 */
public class Recommendation {
    private final Map<String, Map<MethodEntity, Integer>> wordToMethodMap;
    private final Map<MethodEntity, Integer> methodMap;

    private Map<MethodEntity, Integer> methodScores;

    private Collection<String> topMethods;

    public Recommendation() {
        wordToMethodMap = MapFactory.buildAndGetWordToMethodMap();
        methodMap = MapFactory.buildAndGetMethodMap();
        methodScores = methodMap.keySet().stream().collect(Collectors.toMap(Function.identity(), method -> 0));
    }

    public void calScoresFromQuerys(String [] querys) {
        for(String query : querys) {
            if(!wordToMethodMap.containsKey(query)) {
                continue;
            }
            wordToMethodMap.get(query).forEach((method, num) -> {
                if(methodScores.containsKey(method)) {
                    methodScores.put(method, methodScores.get(method) + num);
                } else {
                    methodScores.put(method, num);
                }
            });
        }
        methodScores.forEach((method, num) -> {
            if(num > 0) {
                System.out.println(method.getAbsoluteName()+"(" + method.getParam() + ") : "+num);
                System.out.println(method.getRefSoEntity().getQuestionBody());
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
    }
}
