package com.example.jpa_hikari_jdbc.controller;

import com.example.jpa_hikari_jdbc.model.*;
import com.example.jpa_hikari_jdbc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/itStore")
public class ITStoreController {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CharacteristicRepository characteristicRepository;
    @Autowired
    CustomerEmployeeSupplierRepository customerEmployeeSupplierRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/products")
    public Flux<Products> getAllProducts(){
        return Flux.fromIterable(productsRepository.findAll()) ;
    }

    @GetMapping("/first_N_products")
    public Iterable<Products> getFirstNProducts(@RequestParam int N) {
        return productsRepository.findProductsByIdProdIsBefore(N);
    }

    @GetMapping("/product_max_profit")
    public Flux<ProductsRepository.CategoryProductProfit> getProductWithMaxProfit() {
        var set = productsRepository.findProductWithGreatestProfitInCategory();
        return Flux.fromIterable(set);
    }
    @GetMapping("/category_profit")
    public Flux<ProductsRepository.CategoryProfit> getCategoryProfit() {
        return Flux.fromIterable(productsRepository.findProfitForEachCategory());
    }

    @GetMapping("/characteristics")
    public Flux<Characteristic> getAllCharacteristics() {
        return Flux.fromIterable(characteristicRepository.findAll());
    }

    @GetMapping("/characteristics_for_product")
    public Flux<Characteristic> getCharacteristicsForProduct(@RequestParam(name = "productId", required = true) Integer productId) {
        return Flux.fromIterable(characteristicRepository.findCharacteristicByProduct(productId));
    }

    @GetMapping("/customer_employee_suppliers")
    public Flux<CustomerEmployeeSupplier> getAllCustomerEmployeeSupplier() {
        return Flux.fromIterable(customerEmployeeSupplierRepository.findAll());
    }
    @GetMapping("/employee")
    public Flux<String> getEmployeeWithGreatestNrOfInvoices() {
        return Flux.fromIterable(customerEmployeeSupplierRepository.findEmployeeWithGreatestNrOfInvoices());
    }

    @GetMapping("/ces_by_type")
    public Flux<CustomerEmployeeSupplier> getCustomerEmployeeSupplierByTypeCES(@RequestParam(name = "type", required = true) char type) {
        return Flux.fromIterable(customerEmployeeSupplierRepository.findCustomerEmployeeSupplierByTypeCES(type));
    }

    @GetMapping("/invoices")
    public Flux<Invoice> getAllInvoices() {
        return Flux.fromIterable(invoiceRepository.findAll());
    }

    @GetMapping("/max_invoice")
    public Mono<Float> getMaxInvoice() {
        return Mono.just(invoiceRepository.findMaxValue());
    }

    @GetMapping("/month_with_greatest_sale")
    public Flux<InvoiceRepository.MonthSale> getMonthWithGreatestSale() {
        return Flux.fromIterable(invoiceRepository.getMonthWithGreatestSales());
    }

    @GetMapping("/sales_cost")
    public Flux<InvoiceRepository.SalesCostMonthYear> getSalesCost() {
        return Flux.fromIterable(invoiceRepository.findSalesCostMonthYear());
    }

    @GetMapping("/profit_on_each_product")
    public Flux<InvoiceRepository.ProfitOnEachProduct> getProfitOnEachProduct() {
        return Flux.fromIterable(invoiceRepository.findProfitOnEachProduct());
    }

    @GetMapping("/items")
    public Flux<Item> getAllItems() {
        return Flux.fromIterable(itemRepository.findAll());
    }

    @GetMapping("/items_between_dates")
    public Flux<ItemRepository.ProdQuantity1> getItemsBetweenDates() {
        return  Flux.fromIterable(itemRepository.findItemsBetweenDates());
    }

}
