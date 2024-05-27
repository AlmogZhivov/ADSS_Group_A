package Tests;

import Business.Supplier;
import Service.OrderService;
import Service.SupplierService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderTests {

    private SupplierService supplierService;
    private OrderService orderService;

    public OrderTests(SupplierService supplierService, OrderService orderService){
        this.supplierService = supplierService;
        this.orderService = orderService;
    }

    @BeforeEach
    public void setUp(){
        supplierService.addSupplier("A", "0", "0000", Supplier.PaymentMethod.CASH);

        Date shipmentDate = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate, 0);
    }

    @Test
    public void testAddGeneralOrder(){

    }

}
