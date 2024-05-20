package Business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private final int orderId;
    private Map<Integer, Integer> products;
    private Date shipmentDate;
    private final int supplierId;
    private SupplierAgreement supplierAgreement;


    public Order(int orderId, Map<Integer, Integer> products, Date shipmentDate, int supplierId, SupplierAgreement supplierAgreement) {
        this.orderId = orderId;
        this.products = products;
        this.shipmentDate = shipmentDate;
        this.supplierId = supplierId;
        this.supplierAgreement = supplierAgreement;
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

    public double getOrderPrice(){
        double sum = 0;
        for (Map.Entry<Integer,Integer> product : products.entrySet()){
            sum += supplierAgreement.getProductPriceAccordingToAmount(product.getKey(), product.getValue());
        }
        return sum;
    }
}
