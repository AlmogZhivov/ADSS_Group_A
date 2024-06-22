package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Business.Supplier.PaymentMethod;
import DataAccess.ContactDAO;
import DataAccess.ContactDTO;
import DataAccess.SupplierDAO;
import DataAccess.SupplierDTO;

public class SupplierFacade {

    // <supplierId, Supplier>
    private Map<Integer, Supplier> suppliers = new HashMap<>();
    private int id = 0;

    public Supplier getSupplier(int supplierId) {
        return suppliers.get(supplierId);
    }

    public void addSupplier(String name, String compNumber, String bankNumber, PaymentMethod payment, String address) {
        Supplier supplier = new Supplier(id, name, compNumber, bankNumber, payment, address);
        supplier.supplierDTO.insert();
        suppliers.put(id, supplier);
        this.id++;
    }

    public void removeSupplier(int supplierId) {
        if (!suppliers.containsKey(supplierId))
            throw new IllegalArgumentException("Supplier does not exist");
        Supplier supplier = suppliers.get(supplierId);
        suppliers.remove(supplierId);
        supplier.supplierDTO.delete();
    }

    public void updateSupplierName(int supplierId, String newName) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.setName(newName);
        supplier.supplierDTO.setName(newName);
    }

    public void updateSupplierBankAccount(int supplierId, String newBankAccount) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.setBankNumber(newBankAccount);
        supplier.supplierDTO.setBankNumber(newBankAccount);
    }

    public void updateSupplierPaymentMethod(int supplierId, PaymentMethod newPaymentMethod) {
        Supplier supplier = suppliers.get(supplierId);
        supplier.setPayment(newPaymentMethod);
        supplier.supplierDTO.setPayment(newPaymentMethod.toString());
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

    public void loadAllContacts() {
        ContactDAO contactDAO = new ContactDAO();
        List<ContactDTO> contacts = contactDAO.LoadAllContacts();
        for (ContactDTO contact : contacts) {
            Supplier supplier = suppliers.get(contact.getSupplierId());
            if (supplier != null)
                supplier.contact = new Contact(contact.getName(), contact.getPhoneNumber(), contact.getContactId());
        }
    }

    public void loadAllSuppliers() {
        SupplierDAO supplierDAO = new SupplierDAO();
        List<SupplierDTO> suppliers = supplierDAO.loadAllSuppliers();
        for (SupplierDTO supplier : suppliers) {
            Supplier newSupplier = new Supplier(supplier.getSupplierId(), supplier.getName(), supplier.getCompNumber(),
                    supplier.getBankNumber(), PaymentMethod.valueOf(supplier.getPayment()), supplier.getAddress());
            this.id++;
            // check if the newSupplier already exists in this.suppliers
            if (!this.suppliers.containsKey(supplier.getSupplierId()))
                this.suppliers.put(supplier.getSupplierId(), newSupplier);
        }
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
