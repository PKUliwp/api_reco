package reco_query.entity;

import org.neo4j.graphdb.Node;
import reco_query.entity.entities.MethodEntity;

/**
 * Created by liwp on 2017/5/6.
 */
public abstract class Entity {

    abstract public void build(Node node);
    abstract public String displayName();

    @Override
    public int hashCode() {
        return this.displayName().hashCode();
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Entity) {
            Entity entity = (Entity) anObject;
            return this.displayName().equals(entity.displayName());
        }
        return false;
    }
}
