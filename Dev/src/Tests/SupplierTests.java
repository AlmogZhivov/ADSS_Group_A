package Tests;

import Business.Supplier;
import Business.SupplierAgreement;
import Business.SupplierProduct;
import Service.OrderService;
import Service.Responses.Response;
import Service.Responses.ResponseT;
import Service.SupplierService;
import org.junit.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SupplierTests {

    private SupplierService supplierService;

    public SupplierTests(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @BeforeAll
    public void setUp(){
        supplierService.addSupplier("A", "0", "0000", Supplier.PaymentMethod.CASH);
        supplierService.addSupplier("B", "1", "1111", Supplier.PaymentMethod.BANK_TRANSFER);
        supplierService.addSupplier("C", "2", "2222", Supplier.PaymentMethod.CREDIT_CARD);
    }

    @Test
    public void testAddSupplier(){
        Response res = supplierService.addSupplier("D", "3", "3333", Supplier.PaymentMethod.CREDIT_CARD);
        ResponseT<List<Supplier>> l = supplierService.getAllSuppliers();
        assertFalse(res.errorOccurred());
        assertEquals(3, l.getValue().size());
    }

    @Test
    public void testRemoveSupplier(){
        Response res = supplierService.removeSupplier(2);
        ResponseT<List<Supplier>> l = supplierService.getAllSuppliers();
        assertFalse(res.errorOccurred());
        assertEquals(2, l.getValue().size());
    }

    @Test
    public void testGetSupplier(){
        ResponseT<Supplier> l = supplierService.getSupplier(1);
        assertEquals(1, l.getValue().getSupplierId());
    }

    @Test
    public void testAddProductToSupplier(){
        Response res1 = supplierService.addProductToSupplier(0, 0, 5, "Milk");
        Response res2 = supplierService.addProductToSupplier(0, 1, 6, "Butter");
        ResponseT<SupplierAgreement> responseAgree = supplierService.getSupplierAgreement(0);
        assertFalse(res1.errorOccurred());
        assertFalse(res2.errorOccurred());
        assertNotNull(responseAgree.getValue().getProduct(0));
        assertNotNull(responseAgree.getValue().getProduct(1));
        assertNull(responseAgree.getValue().getProduct(2));
    }

    @Test
    public void testRemoveProductFromSupplier(){
        supplierService.addProductToSupplier(0, 0, 5, "Milk");
        ResponseT<SupplierAgreement> responseAgree = supplierService.getSupplierAgreement(0);
        assertNotNull(responseAgree.getValue().getProduct(0));
        Response res = supplierService.removeProductFromSupplier(0,0);
        ResponseT<SupplierAgreement> responseAgree2 = supplierService.getSupplierAgreement(0);
        assertFalse(res.errorOccurred());
        assertNull(responseAgree2.getValue().getProduct(0));
    }






}
