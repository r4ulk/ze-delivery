package com.abinbev.ze.delivery.response.data;

import com.abinbev.ze.delivery.model.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Json {
    private List<Store> pdvs;
}
