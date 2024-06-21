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

    @BeforeEach
    public void setUp(){
        supplierService.loadData();
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
        Date shipmentDate0 = new Date("2025/12/12");
        Date shipmentDate1 = new Date("2023/12/12");
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate0, 0);
        orderService.addGeneralOrder(products, shipmentDate1, 1);
        //Thread.sleep(100);
        Response res1 = orderService.updateOrders();
        assertFalse(res1.errorOccurred());
        ResponseT<List<Order>> res2 = orderService.getAllOrders();
        assertEquals(1, res2.getValue().size());

    }

    @Test
    public void testAddProduct(){
        Date shipmentDate1 = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate1, 0);
        supplierService.addProductToSupplier(0, 0, 5, "Milk");
        Response res1 = orderService.addProduct(0, 0, 3);
        assertFalse(res1.errorOccurred());
        ResponseT<Order> res2 = orderService.getOrder(0);
        assertFalse(res2.errorOccurred());
        Map<Integer, Integer> order = res2.getValue().getProducts();
        assertEquals(1, order.size());
    }

    @Test
    public void testRemoveProduct(){
        Date shipmentDate1 = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate1, 0);
        supplierService.addProductToSupplier(0, 0, 5, "Milk");
        orderService.addProduct(0, 0, 3);
        Response res1 = orderService.removeProduct(0, 0);
        assertFalse(res1.errorOccurred());
        ResponseT<Order> res2 = orderService.getOrder(0);
        assertFalse(res2.errorOccurred());
        Map<Integer, Integer> order = res2.getValue().getProducts();
        assertTrue(order.isEmpty());
    }

    @Test
    public void testGetPrice(){
        Date shipmentDate1 = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate1, 0);
        supplierService.addProductToSupplier(0, 0, 5, "Milk");
        orderService.addProduct(0, 0, 3);
        supplierService.addProductToSupplier(0, 1, 6, "Butter");
        orderService.addProduct(0, 1, 2);
        ResponseT<Double> res1 = orderService.getOrderPrice(0);
        assertFalse(res1.errorOccurred());
        assertEquals((5 * 3) + (6 * 2), res1.getValue(), 0.001);
    }

    @Test
    public void testUpdateProductDiscountAccordingToAmount(){
        supplierService.addProductToSupplier(0, 0, 5, "Milk");
        Response res1 = supplierService.updateProductDiscountAccordingToAmount(0, 0, 3, 20);
        assertFalse(res1.errorOccurred());
        Date shipmentDate1 = new Date();
        Map<Integer, Integer> products = new HashMap<>();
        orderService.addGeneralOrder(products, shipmentDate1, 0);
        orderService.addProduct(0, 0, 3);
        ResponseT<Double> res2 = orderService.getOrderPrice(0);
        assertEquals(12, res2.getValue());
        Response res3 = supplierService.updateProductDiscountAccordingToAmount(0, 0, 3, 200);
        assertTrue(res3.errorOccurred());
        Response res4 = supplierService.removeProductDiscountAccordingToAmount(0, 0, 3);
        assertFalse(res4.errorOccurred());
        ResponseT<Double> res5 = orderService.getOrderPrice(0);
        assertEquals(15, res5.getValue());

    }

//    @Test
//    public void testAddProductByName(){
//        supplierService.addProductToSupplier(0, 0, 5, "Milk");
//        supplierService.addProductToSupplier(1, 1, 4, "Milk");
//        supplierService.addProductToSupplier(2, 2, 5, "Milk");
//        Date shipmentDate1 = new Date();
//        Map<Integer, Integer> products = new HashMap<>();
//        orderService.addGeneralOrder(products, shipmentDate1, 0);
//        Response res1 = orderService.addProductByName(0, "Milk", 3);
//        assertFalse(res1.errorOccurred());
//        ResponseT<Double> res2 = orderService.getOrderPrice(0);
//        assertEquals(12, res2.getValue());
//    }





}
