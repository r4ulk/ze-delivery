package com.abinbev.ze.delivery.response.data;

import com.abinbev.ze.delivery.utils.GeoJsonAttrEnum;
import com.abinbev.ze.delivery.utils.GeoJsonTypeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonMultiPolygonDeserializer extends JsonDeserializer<GeoJsonMultiPolygon> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GeoJsonMultiPolygon deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        final JsonNode tree = jp.getCodec().readTree(jp);
        final String type = tree.get(GeoJsonAttrEnum.TYPE.getValue()).asText();
        final JsonNode coordsNode = tree.get(GeoJsonAttrEnum.COORDINATES.getValue());
        List<GeoJsonPolygon> geoJsonPolygons = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        if(GeoJsonTypeEnum.MULTIPOLYGON.getValue().equalsIgnoreCase(type)) {

            coordsNode.elements().forEachRemaining(multipolygons -> {
                multipolygons.elements().forEachRemaining(polygons -> {
                    polygons.elements().forEachRemaining(point -> {
                        Double x = point.get(0).asDouble();
                        Double y = point.get(1).asDouble();
                        points.add(new GeoJsonPoint(x, y));
                    });
                    geoJsonPolygons.add(new GeoJsonPolygon(points));
                });
            });
        }else {
            logger.error("Nothing to deserialize {}", tree.asText());
        }

        return new GeoJsonMultiPolygon(geoJsonPolygons);
    }

}
