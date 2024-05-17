package Bussiness;

public class SupplierProduct {

    private int orderId;
    private String name;
    private int supplierId;
    private double price;
    private double priceAfterDiscount;
    private int discountPrecentage;
    private int discountThreshold;

    public SupplierProduct(int orderId, String name, int supplierId, double price, double priceAfterDiscount, int discountPrecentage, int discountThreshold) {
        this.orderId = orderId;
        this.name = name;
        this.supplierId = supplierId;
        this.price = price;
        this.priceAfterDiscount = priceAfterDiscount;
        this.discountPrecentage = discountPrecentage;
        this.discountThreshold = discountThreshold;
    }
}
