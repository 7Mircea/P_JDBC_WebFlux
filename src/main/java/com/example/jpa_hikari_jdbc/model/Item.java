package com.example.jpa_hikari_jdbc.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@IdClass(ItemId.class)
public class Item {
    @ManyToOne
    @Id
    @JoinColumns({
            @JoinColumn(name = "invoice_nr",referencedColumnName = "nr"),
            @JoinColumn(name = "invoice_date",referencedColumnName = "invoice_date")
    })
    private Invoice invoice;

    @Id
    @Column(name = "id_item", nullable = false)
    private int idItem;
    @Column(name = "id_prod", nullable = false)
    private int idProd;
    private String unit;
    private int quantity;
    @Column(name = "unit_price", nullable = false)
    private float unit_price;

}
