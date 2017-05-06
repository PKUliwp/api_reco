package reco_query.entity.utils;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import reco_query.entity.MethodEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by liwp on 2017/5/3.
 */
public class MapperUtils {

    public static Collection<String> getBodyWords(Node node) {
        String body = node.getProperty("body").toString().toLowerCase();
        String[] words = shave(body);
        return new ArrayList(Arrays.asList(words));
    }

    private static String[] shave(String body) {
        body = body.trim()
                .replaceAll("\n", " ")
                .replaceAll("\\(", " ")
                .replaceAll("\\)", " ")
                .replaceAll(",", " ")
                .replaceAll(":", " ")
                .replaceAll("\\.", " ")
                .replaceAll("\\?", " ")
                .replaceAll("<code>[^<>]*</code>", "")
                .replaceAll("<[^<>]*>"," ");
        String[] words = body.split(" ");
        return words;
    }

    public static String getMethodName(Node node) {
        StringBuilder sbuilder = new StringBuilder();
        String methodName = sbuilder
                .append(node.getProperty("belongTo"))
                .append(".")
                .append(node.getProperty("name"))
                .toString();
        return methodName;
    }

    public static Collection<MethodEntity> getRefMethods(Node node) {
        Collection<MethodEntity> methodEntities = new ArrayList<>();
        node.getRelationships(RelationshipType.withName("docRef")).forEach(relationship -> {
            Node srcNode = relationship.getEndNode();
            if(srcNode.getProperties("belongTo").size() > 0) {
                MethodEntity methodEntity = buildMethod(srcNode);
                methodEntities.add(methodEntity);
            }
        });
        return methodEntities;
    }

    public static MethodEntity buildMethod(Node node) {
        MethodEntity oneMethodEntity = new MethodEntity();
        oneMethodEntity.buildFromNode(node);
        return oneMethodEntity;
    }

    public static boolean checkNodeLabel(Node node, String target) {
        for(Label label : node.getLabels()) {
            if(label.toString().contains(target)) {
                return true;
            }
        }
        return false;
    }
}
