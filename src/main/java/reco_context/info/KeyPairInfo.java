package reco_context.info;

import reco_query.entity.entities.MethodEntity;

/**
 * Created by liwp on 2017/6/18.
 */
public class KeyPairInfo {

    public KeyPairInfo(MethodEntity key, MethodEntity value) {
        this.key = key;
        this.value = value;
    }

    public MethodEntity key;
    public MethodEntity value;

    @Override
    public int hashCode() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(key.displayName())
                .append('#')
                .append(key.displayName());
        return builder.toString().hashCode();
    }
}
