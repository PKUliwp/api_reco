package reco_context.pool;

import reco_context.info.KeyPairInfo;
import reco_query.entity.entities.MethodEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwp on 2017/6/18.
 */
public class MethodPool {
    public static List<List<MethodEntity>> methodBlocks = new ArrayList<>();
    public static Map<MethodEntity, Integer> methods = new HashMap<>();
    public static Map<KeyPairInfo, Integer> methodDistributions = new HashMap<>();

    public static void build() {
        methodBlocks.forEach(block -> {
            block.forEach(method -> {
                if (methods.containsKey(methods)) {
                    methods.put(method, methods.get(method) + 1);
                } else {
                    methods.put(method, 1);
                }
            });

            ArrayList<MethodEntity> list = new ArrayList<>();
            block.forEach(value -> {
                if(list.size() > 0) {
                    list.forEach(key -> {
                        KeyPairInfo info = new KeyPairInfo(key, value);
                        if(methodDistributions.containsKey(info)) {
                            methodDistributions.put(info, methods.get(info) + 1);
                        } else {
                            methodDistributions.put(info, 1);
                        }
                    });
                }
                list.add(value);
            });
        });
    }
}
