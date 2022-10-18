package com.example.jpa_hikari_jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@IdClass(CharacteristicId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Characteristic {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false, unique = true)
//    private int idProd;
    private Products product;
    @Id
    @Column(name = "id_characteristic", nullable = false, unique = true)
    private int idCharacteristic;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String value;



    @Override
    public String toString() {
        return new StringBuilder()
                .append("Characteristic : ")
                .append(product.getIdProd())
                .append(',')
                .append(idCharacteristic)
                .append(',')
                .append(name)
                .append(',')
                .append(value)
                .append('\n')
                .toString();
    }
}


