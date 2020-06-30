package com.abinbev.ze.delivery.response.data;

import com.abinbev.ze.delivery.utils.GeoJsonAttrEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.IOException;

/**
 * Serializer of GeoJsonPoint
 * reference:
 * https://www.alessandrorosa.com/geojson-serialization-issues-with-spring-data-mongodb/
 */
public class GeoJsonPointSerializer extends JsonSerializer<GeoJsonPoint> {

    @Override
    public void serialize(GeoJsonPoint value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(GeoJsonAttrEnum.TYPE.getValue(), value.getType());
        gen.writeArrayFieldStart(GeoJsonAttrEnum.COORDINATES.getValue());
        gen.writeObject(value.getCoordinates());
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
