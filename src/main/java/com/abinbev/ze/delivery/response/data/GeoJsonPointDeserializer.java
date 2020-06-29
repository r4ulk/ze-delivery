package com.abinbev.ze.delivery.response.data;

import com.abinbev.ze.delivery.utils.GeoJsonAttrEnum;
import com.abinbev.ze.delivery.utils.GeoJsonTypeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.IOException;

public class GeoJsonPointDeserializer extends JsonDeserializer<GeoJsonPoint> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GeoJsonPoint deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        final JsonNode tree = jp.getCodec().readTree(jp);
        final String type = tree.get(GeoJsonAttrEnum.TYPE.getValue()).asText();
        final JsonNode coordsNode = tree.get(GeoJsonAttrEnum.COORDINATES.getValue());

        double x = 0;
        double y = 0;

        if( GeoJsonTypeEnum.POINT.getValue().equalsIgnoreCase(type)) {
            x = coordsNode.get(1).asDouble();
            y = coordsNode.get(0).asDouble();
        }else {
            logger.error("Nothing to deserialize {}", tree.asText());
        }

        return new GeoJsonPoint(x, y);
    }
}
