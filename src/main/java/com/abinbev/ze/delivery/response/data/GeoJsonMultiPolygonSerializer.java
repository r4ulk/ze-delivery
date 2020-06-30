package com.abinbev.ze.delivery.response.data;

import com.abinbev.ze.delivery.utils.GeoJsonAttrEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.IOException;

/**
 * Serializer of GeoJsonMultiPolygon
 * reference:
 * https://www.alessandrorosa.com/geojson-serialization-issues-with-spring-data-mongodb/
 */
public class GeoJsonMultiPolygonSerializer extends JsonSerializer<GeoJsonMultiPolygon> {

    @Override
    public void serialize(GeoJsonMultiPolygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(GeoJsonAttrEnum.TYPE.getValue(), value.getType());
        gen.writeArrayFieldStart(GeoJsonAttrEnum.COORDINATES.getValue());
        for (GeoJsonPolygon polygon : value.getCoordinates()) {
            gen.writeStartArray();
            for (GeoJsonLineString ls : polygon.getCoordinates()) {
                gen.writeStartArray();
                for (Point p : ls.getCoordinates()) {
                    gen.writeObject(new double[]{p.getX(), p.getY()});
                }
                gen.writeEndArray();
            }
            gen.writeEndArray();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
