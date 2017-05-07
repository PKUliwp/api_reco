package reco_query;

import reco_query.entity.Entity;
import reco_query.reco.Recommendation;

import java.util.Collection;

/**
 * Created by liwp on 2017/5/7.
 */
public class test {
    public static void main(String args[]) {
        Recommendation reco = new Recommendation();
        Collection<Entity> entities = reco.recommend("index");

    }

}
