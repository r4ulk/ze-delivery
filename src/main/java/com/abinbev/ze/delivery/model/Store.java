package com.abinbev.ze.delivery.model;

import com.abinbev.ze.delivery.response.data.GeoJsonMultiPolygonDeserializer;
import com.abinbev.ze.delivery.response.data.GeoJsonMultiPolygonSerializer;
import com.abinbev.ze.delivery.response.data.GeoJsonPointDeserializer;
import com.abinbev.ze.delivery.response.data.GeoJsonPointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Store model is a concrete representation of pdv
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Store {

    @Id
    private long id;

    private String tradingName;
    private String ownerName;

    @Indexed(unique = true)
    private String document;

    @Indexed
    @JsonSerialize(using = GeoJsonMultiPolygonSerializer.class)
    @JsonDeserialize(using = GeoJsonMultiPolygonDeserializer.class)
    private GeoJsonMultiPolygon coverageArea;

    @JsonSerialize(using = GeoJsonPointSerializer.class)
    @JsonDeserialize(using = GeoJsonPointDeserializer.class)
    private GeoJsonPoint address;
}
