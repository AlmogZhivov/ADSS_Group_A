package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Business.Supplier.PaymentMethod;

public class SupplierFacade {

    private Map<Integer, Supplier> suppliers = new HashMap<>();

    public Supplier getSupplier(int supplierId) {
        return suppliers.get(supplierId);
    }

    public void addSupplier(int supplierId, String name, String compNumber, String bankNumber, PaymentMethod payment) {
        Supplier supplier = new Supplier(supplierId, name, compNumber, bankNumber, payment);
        suppliers.put(supplierId, supplier);
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
        return (List<Supplier>) suppliers.values();
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSupplierAgreement'");
    }

    public void updateProductPrice(int supplierId, int catalogNumber, double newPrice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProductPrice'");
    }

    public void addProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int discountPercentage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemDiscountAccordingToAmount'");
    }

    public void updateProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount,
            int newDiscountPercentage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItemDiscountAccordingToAmount'");
    }

    public void removeProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItemDiscountAccordingToAmount'");
    }

    public void addProductToSupplier(int supplierId, int catalogNumber, double price, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToSupplier'");
    }

    public void removeItem(int supplierId, int catalogNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
    }

    public void updateItemName(int supplierId, int catalogNumber, String newName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItemName'");
    }
}
