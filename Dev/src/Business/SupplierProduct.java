package Business;

public class SupplierProduct {

    private int catalogNumber;
    private String name;
    private int supplierId;
    private double price;
    private double priceAfterDiscount;
    private int discountPrecentage;
    private int discountThreshold;

    public SupplierProduct(int catalogNumber, String name, int supplierId, double price, double priceAfterDiscount, int discountPrecentage, int discountThreshold) {
        this.catalogNumber = catalogNumber;
        this.name = name;
        this.supplierId = supplierId;
        this.price = price;
        this.priceAfterDiscount = priceAfterDiscount;
        this.discountPrecentage = discountPrecentage;
        this.discountThreshold = discountThreshold;
    }
    
    public SupplierProduct(SupplierProduct supplierProduct) {
        this.catalogNumber = supplierProduct.getCatalogNumber();
        this.name = supplierProduct.getName();
        this.supplierId = supplierProduct.getSupplierId();
        this.price = supplierProduct.getPrice();
        this.priceAfterDiscount = supplierProduct.getPriceAfterDiscount();
        this.discountPrecentage = supplierProduct.getDiscountPrecentage();
        this.discountThreshold = supplierProduct.getDiscountThreshold();
    }

    private int getDiscountThreshold() {
        return discountThreshold;   
    }

    private int getDiscountPrecentage() {
        return discountPrecentage;
    }

    private double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    private double getPrice() {
        return price;
    }

    private int getSupplierId() {
        return supplierId;  
    }

    private String getName() {
        return name;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }
}
