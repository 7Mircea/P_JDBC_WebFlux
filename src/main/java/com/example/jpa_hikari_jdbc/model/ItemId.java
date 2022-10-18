package com.example.jpa_hikari_jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class ItemId implements Serializable {
    private Invoice invoice;
    private int idItem;

    @Override
    public int hashCode() {
        return invoice.hashCode()+idItem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!obj.getClass().getName().equals(this.getClass().getName())) return false;
        return this.hashCode() == obj.hashCode();
    }
}
