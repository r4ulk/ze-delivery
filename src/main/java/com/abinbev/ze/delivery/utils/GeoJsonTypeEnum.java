package com.abinbev.ze.delivery.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GeoJsonTypeEnum {
    POINT("POINT"),
    MULTIPOLYGON("MULTIPOLYGON");

    private String value = null;
}
