package com.example.jpa_hikari_jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.persistence.*;

import static com.example.jpa_hikari_jdbc.utils.Constants.CUSTOMER_EMPLOYEE_SUPPLIER_TABLE;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = CUSTOMER_EMPLOYEE_SUPPLIER_TABLE)
public class CustomerEmployeeSupplier {
    @Id
    @Column(name = "id_ces", nullable = false, unique = true)
    private int idCES;
    @Column(nullable = false)
    private String name;
    @Column(length = 10)
    private String EIN;
    @Column(name = "type_ces", nullable = false)
    private char typeCES;
    private String address;
    @Column(nullable = false)
    private String IBAN;
    private String SSN;
}
