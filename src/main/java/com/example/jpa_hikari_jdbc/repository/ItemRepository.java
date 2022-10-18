package com.example.jpa_hikari_jdbc.repository;

import com.example.jpa_hikari_jdbc.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query(value = "select p.prod_name,idProdQ.quantity from products p inner join (select i.id_prod id_prod,sum(i.quantity) quantity\n" +
            "from item i inner join invoice on i.invoice_nr = invoice.nr and i.invoice_Date = invoice.invoice_Date\n" +
            "where EXTRACT(month from invoice.invoice_Date) = 10 and EXTRACT(year from invoice.invoice_Date) = 2022 and invoice.type='c'\n" +
            "group by i.id_prod) idProdQ on p.id_prod=idProdQ.id_prod;",
            nativeQuery = true)
    Set<ProdQuantity1> findItemsBetweenDates();

    interface ProdQuantity1 {
        String getProd_Name();
        int getQuantity();
    }
}
