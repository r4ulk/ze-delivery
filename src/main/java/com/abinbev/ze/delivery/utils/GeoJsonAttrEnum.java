package com.abinbev.ze.delivery.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GeoJsonAttrEnum {
    TYPE("type"),
    COORDINATES("coordinates");

    private String value = null;
}
