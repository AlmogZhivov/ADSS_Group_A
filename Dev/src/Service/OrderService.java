package Service;

import Business.Order;
import Business.OrderFacade;
import Service.Responses.Response;
import Service.Responses.ResponseT;
ddimport netscape.javascript.JSObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final OrderFacade orderFacade;

    public OrderService(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public Response addOrder(Map<Integer, Integer> products, Date shipmentDate, int supplierId){
        try {
            orderFacade.addOrder(products, shipmentDate, supplierId);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response removeOrder(int orderId){
        try {
            orderFacade.removeOrder(orderId);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response addProduct(int orderId, int catalogNumber, int amount){
        try {
            orderFacade.addProduct(orderId, catalogNumber, amount);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response removeProduct(int orderId, int catalogNumber){
        try {
            orderFacade.removeProduct(orderId, catalogNumber);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response getOrder(int orderId){
        try {
            Order order = orderFacade.getOrder(orderId);
            return new ResponseT<>(order);
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }

    public Response getAllOrders(){
        try {
            List<Order> orders = orderFacade.getAllOrders();
            return new ResponseT<>(orders);
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }

    public Response getOrderPrice(int orderId){
        try {
            double price = orderFacade.getOrderPrice(orderId);
            return new ResponseT<>(price);
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }





}
