package com.abinbev.ze.delivery.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GeoJsonTypeEnum {
    POINT("Point"),
    MULTIPOLYGON("Multipolygon");

    private String value = null;
}
