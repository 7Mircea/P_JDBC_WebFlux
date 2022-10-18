package com.example.jpa_hikari_jdbc.repository;

import com.example.jpa_hikari_jdbc.model.CustomerEmployeeSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerEmployeeSupplierRepository extends JpaRepository<CustomerEmployeeSupplier,Integer> {
    Set<CustomerEmployeeSupplier> findCustomerEmployeeSupplierByTypeCES(char c);

    @Query(value = "select CES.Name\n" +
            "from (select count(*) invoice_number, id_employee\n" +
            "    from invoice\n" +
            "    group by id_employee) S inner join CUSTOMER_EMPLOYEE_SUPPLIER CES on S.id_employee = CES.id_ces\n" +
            "where invoice_number = (select max(S2.invoice_number) \n" +
            "                    from (select count(*) invoice_number, id_employee\n" +
            "                        from invoice\n" +
            "                        group by id_employee) S2);\n", nativeQuery = true)
    Set<String> findEmployeeWithGreatestNrOfInvoices();
}
