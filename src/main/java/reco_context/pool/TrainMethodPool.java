package reco_context.pool;

import org.neo4j.register.Register;
import reco_context.info.KeyPairInfo;
import reco_query.entity.entities.MethodEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwp on 2017/6/18.
 */
public class TrainMethodPool {
    public static List<List<MethodEntity>> methodBlocks = new ArrayList<>();
    public static Map<MethodEntity, Integer> methods = new HashMap<>();
    public static Map<KeyPairInfo, Integer> methodDistributions = new HashMap<>();
}