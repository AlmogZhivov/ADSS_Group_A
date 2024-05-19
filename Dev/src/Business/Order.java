package Business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private final int orderId;
    private Map<Integer, Integer> products;
    private Date shipmentDate;
    private final int supplierId;


    public Order(int orderId, Map<Integer, Integer> products, Date shipmentDate, int supplierId) {
        this.orderId = orderId;
        this.products = products;
        this.shipmentDate = shipmentDate;
        this.supplierId = supplierId;
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public void addProduct(int catalogNumber, int amount){
        products.put(catalogNumber, amount);
    }

    public void removeProduct(int catalogNumber){
        products.remove(catalogNumber);
    }
}
