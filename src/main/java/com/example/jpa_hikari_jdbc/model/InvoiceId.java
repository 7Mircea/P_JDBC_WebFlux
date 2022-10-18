package com.example.jpa_hikari_jdbc.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class InvoiceId implements Serializable {
    private int nr;
    private LocalDate invoiceDate;

    @Override
    public int hashCode() {
        return nr + invoiceDate.getYear() + invoiceDate.getMonthValue() + invoiceDate.getDayOfMonth();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!obj.getClass().getName().equals(this.getClass().getName()))
            return false;
        InvoiceId objN = (InvoiceId) obj;
        return hashCode() == objN.hashCode();
    }
}
