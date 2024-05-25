package Tests;

import Business.Supplier;
import Service.OrderService;
import Service.Responses.ResponseT;
import Service.SupplierService;
import org.junit.*;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SupplierTests {

    private SupplierService supplierService;
    private OrderService orderService;
    private final Gson gson = new Gson();

    public SupplierTests(SupplierService supplierService, OrderService orderService){
        this.supplierService = supplierService;
        this.orderService = orderService;
    }

    public void setUp(){
        supplierService.addSupplier("A", "0", "0000", Supplier.PaymentMethod.CASH);
        supplierService.addSupplier("B", "1", "1111", Supplier.PaymentMethod.BANK_TRANSFER);
        supplierService.addSupplier("C", "2", "1111", Supplier.PaymentMethod.CREDIT_CARD);

    }

    @Test
    public void testAddSupplier(){
        setUp();
        ResponseT<List<Supplier>> l = gson.fromJson(supplierService.getAllSuppliers(), ResponseT.class);
        assertEquals(3, l.getValue().size());
    }

    @Test
    public void testRemoveSupplier(){
        setUp();
        supplierService.removeSupplier(2);
        ResponseT<List<Supplier>> l = gson.fromJson(supplierService.getAllSuppliers(), ResponseT.class);
        assertEquals(2, l.getValue().size());
    }

    @Test
    public void testGetSupplier(){
        setUp();
        ResponseT<Supplier> l = gson.fromJson(supplierService.getSupplier(1), ResponseT.class);
        assertEquals(1, l.getValue().getSupplierId());
    }






}
