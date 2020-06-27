package com.abinbev.ze.delivery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store {
    private long id;
    private String tradingName;
    private String ownerName;
    private String document;
    private CoverageArea coverageArea;
}
