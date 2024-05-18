package Bussiness;

public class SupplierProduct {

    private int catalogNumber;
    private String name;
    private double price;
    private double priceAfterDiscount;
    private int discountPrecentage;
    private int discountThreshold;

    public SupplierProduct(int catalogNumber, String name, int supplierId, double price, double priceAfterDiscount, int discountPrecentage, int discountThreshold) {
        this.catalogNumber = catalogNumber;
        this.name = name;
        this.price = price;
        this.priceAfterDiscount = priceAfterDiscount;
        this.discountPrecentage = discountPrecentage;
        this.discountThreshold = discountThreshold;
    }
}
