package reco_query.entity;

import reco_query.entity.entities.MethodEntity;

/**
 * Created by liwp on 2017/5/7.
 */
public abstract class ReboundEntity extends Entity{

    @Override
    public String name() {
        return "";
    }
    @Override
    public int compareTo(Entity e) {
        if(e instanceof MethodEntity) {
            return 1;
        } else {
            if(e.getTimes() != this.getTimes()) {
                return e.getTimes().compareTo(this.getTimes());
            } else {
                return e.displayName().compareTo(this.displayName());
            }
        }
    }
}
