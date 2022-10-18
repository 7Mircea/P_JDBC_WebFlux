package com.example.jpa_hikari_jdbc.model;

import java.io.Serializable;

public class CharacteristicId implements Serializable {
//    private int idProd;
    private Products product;
    private int idCharacteristic;

    @Override
    public int hashCode() {
        return product.hashCode() + idCharacteristic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null)
            return false;
        if (!obj.getClass().getName().equals(this.getClass().getName())) return false;
        return this.hashCode()==obj.hashCode();
    }
}