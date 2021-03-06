package reco_query.entity.entities;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.graphdb.Node;
import reco_query.entity.ReboundEntity;

/**
 * Created by liwp on 2017/5/6.
 */
@Setter
@Getter
public class ClassEntity extends ReboundEntity {
    private String fullName;
    private String comment;

    @Override
    public String prefix() {
        return fullName;
    }

    @Override
    public String suffix() {
        return "Class";
    }

    @Override
    public void build(Node node) {
        buildFromNode(node);
    }

    @Override
    public String displayName() {
        return "Class: " + fullName;
    }

    private void buildFromNode(Node node) {
        this.setFullName(node.getProperty("fullName").toString());
        this.setComment(node.getProperty("comment").toString());
    }


}
