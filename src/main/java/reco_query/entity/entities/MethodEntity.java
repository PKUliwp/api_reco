package reco_query.entity.entities;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.graphdb.Node;
import reco_query.entity.Entity;
import reco_query.mapper.utils.MapperUtils;

/**
 * Created by liwp on 2017/5/3.
 */
@Getter
@Setter
public class MethodEntity extends Entity {
    private String name;
    private String rt;
    private String belongTo;
    private String absoluteName;
    private String params;
    private String comment;
    private StackoverflowEntity refSoEntity;

    @Override
    public void build(Node node) {
        buildFromNode(node);
    }

    @Override
    public String displayName() {
        return "Method: " + absoluteName + "(" + params + ")";
    }

    private void buildFromNode(Node node) {
        this.setBelongTo(node.getProperty("belongTo").toString());
        this.setName(node.getProperty("name").toString());
        this.setRt(node.getProperty("rt").toString());
        this.setAbsoluteName(MapperUtils.getMethodName(node));
        this.setParams(node.getProperty("params").toString());
        this.setComment(node.getProperty("comment").toString());
        this.refSoEntity = new StackoverflowEntity();
        this.refSoEntity.build(node);
    }

    @Override
    public int hashCode() {
        return this.displayName().hashCode();
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof MethodEntity) {
            MethodEntity methodEntity = (MethodEntity) anObject;
            return this.displayName().equals(methodEntity.displayName());
        }
        return false;
    }
}
