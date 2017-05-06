package reco_query.mapper;

import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Created by liwp on 2017/5/6.
 */
public interface Mapper {

    void build();
    void close();
}
