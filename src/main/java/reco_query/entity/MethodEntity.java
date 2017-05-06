package reco_query.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.graphdb.Node;
import reco_query.entity.utils.MapperUtils;

/**
 * Created by liwp on 2017/5/3.
 */
@Getter
@Setter
public class MethodEntity implements Comparable<MethodEntity>{
    private String name;
    private String rt;
    private String belongTo;
    private String absoluteName;
    private String param;
    private StackoverflowEntity refSoEntity;

    public void buildFromNode(Node node) {
        this.setBelongTo(node.getProperty("belongTo").toString());
        this.setName(node.getProperty("name").toString());
        this.setRt(node.getProperty("rt").toString());
        this.setAbsoluteName(MapperUtils.getMethodName(node));
        this.setParam(node.getProperty("params").toString());
        this.refSoEntity = new StackoverflowEntity();
        this.refSoEntity.buildFromMethodNode(node);
    }

    @Override
    public int compareTo(MethodEntity methodEntity) {
        return this.absoluteName.compareTo(methodEntity.getAbsoluteName());
    }

    @Override
    public int hashCode() {
        return this.absoluteName.hashCode();
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof MethodEntity) {
            MethodEntity methodEntity = (MethodEntity) anObject;
            return this.absoluteName.equals(methodEntity.getAbsoluteName());
        }
        return false;
    }
}
