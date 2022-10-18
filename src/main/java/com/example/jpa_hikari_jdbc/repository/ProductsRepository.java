package com.example.jpa_hikari_jdbc.repository;

import com.example.jpa_hikari_jdbc.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProductsRepository extends JpaRepository<Products,Long> {

    @Query(value = "select distinct on (SS.category) category, SS.Product_name, SS.Profit\n" +
            "from (select S.category, S.prod_name Product_name, (S.sales - C.costs) Profit\n" +
            "    from (select p.category category, p.prod_name prod_name, sum(i.quantity * i.unit_price) sales\n" +
            "        from item i inner join products p on i.id_prod = p.id_prod\n" +
            "            inner join invoice inv on inv.nr = i.invoice_nr and inv.invoice_date = i.invoice_date\n" +
            "            where inv.type = 'c'\n" +
            "            group by i.id_prod,p.prod_name,p.category) S \n" +
            "        inner join \n" +
            "        (select p.prod_name prod_name, sum(i.quantity * i.unit_price) costs\n" +
            "        from item i inner join products p on i.id_prod = p.id_prod\n" +
            "            inner join invoice f on f.nr = i.invoice_nr and f.invoice_date = i.invoice_date\n" +
            "            where f.type = 's'\n" +
            "            group by i.id_prod,p.prod_name) C \n" +
            "        on S.prod_name = C.prod_name \n" +
            "    order by S.prod_name) SS\n" +
            "where SS.Profit = (select Max((S.sales - C.costs)) Profit\n" +
            "                    from (select p.prod_name prod_name, p.category category, sum(i.quantity * i.unit_price) sales\n" +
            "                        from item i inner join products p on i.id_prod = p.id_prod\n" +
            "                            inner join invoice f on f.nr = i.invoice_nr and f.invoice_date = i.invoice_date\n" +
            "                            where f.type = 'c' and p.category = SS.category\n" +
            "                            group by i.id_prod,p.prod_name,p.category) S \n" +
            "                        inner join \n" +
            "                        (select p.prod_name prod_name, sum(i.quantity * i.unit_price) costs\n" +
            "                        from item i inner join products p on i.id_prod = p.id_prod\n" +
            "                            inner join invoice f on f.nr = i.invoice_nr and f.invoice_date = i.invoice_date\n" +
            "                            where f.type = 's' and p.category = SS.category\n" +
            "                            group by i.id_prod,p.prod_name) C \n" +
            "                        on S.prod_name = C.prod_name \n" +
            "                    group by S.category);",nativeQuery = true)
    Set<CategoryProductProfit> findProductWithGreatestProfitInCategory();

    @Query(value = "select S.category, sum(S.sales - C.costs) Profit\n" +
            "from (select p.category category, p.prod_name prod_name, sum(i.quantity * i.unit_price) sales\n" +
            "    from item i inner join products p on i.id_prod = p.id_prod\n" +
            "        inner join invoice f on f.nr = i.invoice_nr and f.invoice_date = i.invoice_date\n" +
            "        where f.type = 'c'\n" +
            "        group by i.id_prod, p.prod_name, p.category) S \n" +
            "    inner join \n" +
            "    (select p.prod_name prod_name, sum(i.quantity * i.unit_price) costs\n" +
            "    from item i inner join products p on i.id_prod = p.id_prod\n" +
            "        inner join invoice inv on inv.nr = i.invoice_nr and inv.invoice_date = i.invoice_date\n" +
            "        where inv.type = 's'\n" +
            "        group by i.id_prod,p.prod_name) C \n" +
            "    on S.prod_name = C.prod_name \n" +
            "group by category\n" +
            "order by category;",nativeQuery = true)
    Set<CategoryProfit> findProfitForEachCategory();

    interface CategoryProductProfit {
        String getCategory();
        String getProduct_Name();
        float getProfit();
    }

    interface CategoryProfit {
        String getCategory();
        float getProfit();
    }
}
