package Bussiness;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderId;
    private Map<SupplierProduct, Integer> orderList;
    private Date shipmentDate;

    public Order(int orderId, Date shipmentDate) {
        this.orderId = orderId;
        this.orderList = new HashMap<>();
        this.shipmentDate = shipmentDate;
    }

    public void addProduct(SupplierProduct sp, int amount){
        orderList.put(sp, amount);
    }
}
