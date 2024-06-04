package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Business.Supplier.PaymentMethod;

public class SupplierFacade {

    // <supplierId, Supplier>
    private Map<Integer, Supplier> suppliers = new HashMap<>();
    private int id = 0;

    public Supplier getSupplier(int supplierId) {
        return suppliers.get(supplierId);
    }

    public void addSupplier(String name, String compNumber, String bankNumber, PaymentMethod payment) {
        Supplier supplier = new Supplier(id, name, compNumber, bankNumber, payment);
        suppliers.put(id, supplier);
        this.id++;
    }

    public void removeSupplier(int supplierId) {
        suppliers.remove(supplierId);
    }

    public void updateSupplierName(int supplierId, String newName) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.setName(newName);
    }

    public void updateSupplierBankAccount(int supplierId, String newBankAccount) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.setBankNumber(newBankAccount);
    }

    public void updateSupplierPaymentMethod(int supplierId, PaymentMethod newPaymentMethod) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.setPayment(newPaymentMethod);
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliersList = new ArrayList<>();
        for (Supplier supplier : suppliers.values()) {
            suppliersList.add(supplier);
        }
        return suppliersList;
    }

    // Adds contact to supplier
    public void addContact(int supplierId, String contactName, String phoneNumber) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.addContact(contactName, phoneNumber);
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        for (Supplier supplier : suppliers.values()) {
            contacts.add(supplier.getContact());
        }
        return contacts;
    }

    public SupplierAgreement getSupplierAgreement(int supplierId) {
        return suppliers.get(supplierId).getSupplierAgreement();
    }

    public void updateProductPrice(int supplierId, int catalogNumber, double newPrice) {
        suppliers.get(supplierId).updateProductPrice(catalogNumber, newPrice);
    }

    public void addProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int discountPercentage) {
        suppliers.get(supplierId).addProductDiscountAccordingToAmount(catalogNumber, amount, discountPercentage);
    }

    public void updateProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount,
            int newDiscountPercentage) {
        suppliers.get(supplierId).updateProductDiscountAccordingToAmount(catalogNumber, amount, newDiscountPercentage);
    }

    public void removeProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount) {
        suppliers.get(supplierId).removeProductDiscountAccordingToAmount(catalogNumber, amount);    
    }

    public void addProductToSupplier(int supplierId, int catalogNumber, double price, String name) {
        SupplierProduct product = new SupplierProduct(supplierId, catalogNumber, price, name);
        suppliers.get(supplierId).addProduct(product);
    }

    public void removeProduct(int supplierId, int catalogNumber) {
        suppliers.get(supplierId).removeProduct(catalogNumber);
    }

    public void updateProductName(int supplierId, int catalogNumber, String newName) {
        suppliers.get(supplierId).updateProductName(catalogNumber, newName);
    }
}
