package Business;

import java.util.*;

public class OrderFacade {

    private Map<Integer, Order> orders;
    private int id;
    private final SupplierFacade sf;

    public OrderFacade(SupplierFacade sf){
        this.orders = new HashMap<>();
        this.id = 0;
        this.sf = sf;
    }

    public void addGeneralOrder(Map<Integer, Integer> products, Date shipmentDate, int supplierId){
        SupplierAgreement sa = sf.getSupplierAgreement(supplierId);
        Order order = new Order(this.id, products, shipmentDate, supplierId, sa, -1);
        orders.put(id, order);
        id++;
    }

    public void addRepOrder(Map<Integer, Integer> products, Date shipmentDate, int supplierId, int day){
        SupplierAgreement sa = sf.getSupplierAgreement(supplierId);
        Order order = new Order(this.id, products, shipmentDate, supplierId, sa, day);
        orders.put(id, order);
        id++;
    }

    public void updateOrders(){
        Map<Integer, Order> copyOrders = new HashMap<>(orders);
        for (Order order : copyOrders.values()){
            if (order.getDay() == -1 && order.getShipmentDate().after(new Date())){
                removeOrder(order.getOrderId());
            }
        }
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

    public Order getOrder(int orderId){
        return orders.get(orderId);
    }

    public List<Order> getAllOrders(){
        List<Order> ordersList = new ArrayList<>();
        for (Order order : orders.values()){
            ordersList.add(order);
        }
        return ordersList;
    }

    public double getOrderPrice(int orderId){
        Order order = orders.get(orderId);
        return order.getOrderPrice();
    }

    public String getOrderString(int orderId){
        Order order = orders.get(orderId);
        return order.toString();
    }
}
