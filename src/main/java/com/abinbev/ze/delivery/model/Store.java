package com.abinbev.ze.delivery.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Store model is a concrete representation of pdv
 */
@Getter
@Setter
public class Store {
    private long id;
    private String tradingName;
    private String ownerName;
    private String document;
    private CoverageArea coverageArea;
    private Address address;
}
