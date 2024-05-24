package Service;

import Business.Order;
import Business.OrderFacade;
import Service.Responses.Response;
import Service.Responses.ResponseT;
import netscape.javascript.JSObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class OrderService {

    private final OrderFacade orderFacade;
    private Gson gson = new Gson();

    public OrderService(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    // Adds a single order to the system
    public String addGeneralOrder(Map<Integer, Integer> products, Date shipmentDate, int supplierId){
        try {
            orderFacade.addGeneralOrder(products, shipmentDate, supplierId);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Adds a repeating order for a specific day of the week
    public String addRepOrder(Map<Integer, Integer> products, Date shipmentDate, int supplierId, int day){
        try {
            orderFacade.addRepOrder(products, shipmentDate, supplierId, day);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Removes all general (non-repeating) orders that have been shipped
    public String updateOrders(){
        try {
            orderFacade.updateOrders();
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Removes an order from the system
    public String removeOrder(int orderId){
        try {
            orderFacade.removeOrder(orderId);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Adds a product to an existing order
    public String addProduct(int orderId, int catalogNumber, int amount){
        try {
            orderFacade.addProduct(orderId, catalogNumber, amount);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    //Removes a product from an existing order
    public String removeProduct(int orderId, int catalogNumber){
        try {
            orderFacade.removeProduct(orderId, catalogNumber);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Returns an order according to the id given
    public String getOrder(int orderId){
        try {
            Order order = orderFacade.getOrder(orderId);
            return gson.toJson(new ResponseT<>(order));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Returns a list of all orders in the system
    public String getAllOrders(){
        try {
            List<Order> orders = orderFacade.getAllOrders();
            return gson.toJson(new ResponseT<>(orders));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Returns the price of an order according to the id given
    public String getOrderPrice(int orderId){
        try {
            double price = orderFacade.getOrderPrice(orderId);
            return gson.toJson(new ResponseT<>(price));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Returns a string describing the order according to the id given
    public String getOrderString(int orderId){
        try {
             String str = orderFacade.getOrderString(orderId);
            return gson.toJson(new ResponseT<>(str));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }
}
