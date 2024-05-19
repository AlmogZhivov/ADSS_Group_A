package Business;

import java.util.*;

public class OrderFacade {

    private Map<Integer, Order> orders;
    private int id;
    private SupplierFacade sf;

    public OrderFacade(SupplierFacade sf){
        this.orders = new HashMap<>();
        this.id = 0;
        this.sf = sf;
    }

    public void addOrder(Map<Integer, Integer> products, Date shipmentDate, int supplierId){
        Order order = new Order(this.id, products, shipmentDate, supplierId);
        orders.put(id, order);
        id++;
    }

    public void removeOrder(int orderId){
        orders.remove(orderId);
    }

    public void updateShipmentDate(int orderId, Date shipmentDate){
        Order order = orders.get(orderId);
        order.setShipmentDate(shipmentDate);
    }

    public void addProduct(int orderId, int catalogNumber, int amount){
        Order order = orders.get(orderId);
        order.addProduct(catalogNumber, amount);
    }

    public void removeProduct(int orderId, int catalogNumber){
        Order order = orders.get(orderId);
        order.removeProduct(catalogNumber);
    }

    public void getOrder(int orderId){
        //TODO - return a response
    }

    public void getAllOrders(){
        //TODO - return a response
    }


}
