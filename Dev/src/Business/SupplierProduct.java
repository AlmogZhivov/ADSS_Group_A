package Business;

import java.util.Map;

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

    public SupplierProduct(int catalogNumber, double price, String name) {
        this.catalogNumber = catalogNumber;
        this.price = price;
        this.name = name;
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

    public double getPrice() {
        return price;
    }

    public int getSupplierId() {
        return supplierId;  
    }

    public String getName() {
        return name;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public Map<Integer, Double> getProductDiscounts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemDiscounts'");
    }

    public boolean isAmountExists(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAmountExists'");
    }

    public double getDiscountForOrder(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiscountForOrder'");
    }

    public void setPrice(double price2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrice'");
    }

    public void addProductDiscountAccordingToAmount(int amount, double discount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProductDiscountAccordingToAmount'");
    }

    public void updateProductDiscountAccordingToAmount(int amount, double newDiscount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProductDiscountAccordingToAmount'");
    }

    public void removeProductDiscountAccordingToAmount(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeProductDiscountAccordingToAmount'");
    }

    public void setName(String newName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }
}
