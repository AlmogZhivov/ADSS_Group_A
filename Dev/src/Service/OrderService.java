package Service;

import Business.OrderFacade;
import Service.Responses.Response;
import jdk.jshell.spi.ExecutionControl;
import netscape.javascript.JSObject;

import java.util.Date;
import java.util.Map;

public class OrderService {

    private OrderFacade orderFacade;

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
        //TODO - implement
         return null;
    }

    public Response getAllOrders(){
        //TODO - implement
        return null;
    }





}
