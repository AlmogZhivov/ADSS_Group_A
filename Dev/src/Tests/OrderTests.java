package Tests;

import Business.Order;
import Business.OrderFacade;
import Business.Supplier;
import Business.SupplierFacade;
import Service.OrderService;
import Service.Responses.Response;
import Service.Responses.ResponseT;
import Service.SupplierService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTests {

    private static SupplierFacade supplierFacade = new SupplierFacade();
    private static SupplierService supplierService = new SupplierService(supplierFacade);
    private OrderService orderService = new OrderService(new OrderFacade(supplierFacade));

    public OrderTests(){
    }

    @BeforeAll
    public static void setUp(){
        System.out.println("Setting up");
        supplierService.addSupplier("A", "0", "0000", Supplier.PaymentMethod.CASH);
        supplierService.addSupplier("B", "1", "1111", Supplier.PaymentMethod.BANK_TRANSFER);
        supplierService.addSupplier("C", "2", "2222", Supplier.PaymentMethod.CREDIT_CARD);
    }

    @Test
    public void testAddGeneralOrder(){
        Date shipmentDate = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        Response res1 = orderService.addGeneralOrder(products, shipmentDate, 0);
        assertFalse(res1.errorOccurred());
        Response res2 = orderService.getOrder(0);
        assertFalse(res2.errorOccurred());
    }

    @Test
    public void testAddRepOrder(){
        Date shipmentDate = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        Response res1 = orderService.addRepOrder(products, shipmentDate, 0, 1);
        assertFalse(res1.errorOccurred());
        Response res2 = orderService.getOrder(0);
        assertFalse(res2.errorOccurred());
    }

    @Test
    public void testRemoveOrder(){
        Date shipmentDate = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addRepOrder(products, shipmentDate, 0, 1);
        Response res = orderService.removeOrder(1);
        assertFalse(res.errorOccurred());
    }

    @Test
    public void testGetAllOrders(){
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, new Date(), 0);
        ResponseT<List<Order>> res1 = orderService.getAllOrders();
        assertFalse(res1.errorOccurred());
        assertFalse(res1.getValue().isEmpty());
    }

    @Test
    public void testUpdateOrders(){
        Date shipmentDate = new Date("2025/12/12");
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate, 0);
        orderService.addGeneralOrder(products, shipmentDate, 1);
        //Thread.sleep(100);
        Response res1 = orderService.updateOrders();
        assertFalse(res1.errorOccurred());
        ResponseT<List<Order>> res2 = orderService.getAllOrders();
        assertTrue(res2.getValue().isEmpty());

    }

}
