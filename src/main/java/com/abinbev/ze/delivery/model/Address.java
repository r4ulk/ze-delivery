package com.abinbev.ze.delivery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String type;
    private Float[][][] coordinates;
}
