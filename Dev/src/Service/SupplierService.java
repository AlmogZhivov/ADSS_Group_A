package Service;

import Business.Contact;
import Business.Supplier;
import Business.Supplier.PaymentMethod;
import Business.SupplierAgreement;
import Business.SupplierFacade;
import Service.Responses.Response;
import Service.Responses.ResponseT;
import com.google.gson.Gson;

import java.util.List;

public class SupplierService {
    private SupplierFacade supplierFacade;
    private Gson gson = new Gson();

    public SupplierService(SupplierFacade supplierFacade) {
        this.supplierFacade = supplierFacade;
    }
    
    
    // Supplier related functions

    // Adds a new supplier to the system
    public String addSupplier(int supplierId, String name, String compNumber, String bankNumber, PaymentMethod payment) {
        try {
            supplierFacade.addSupplier(supplierId, name, compNumber, bankNumber, payment);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Removes the supplier with the given id
    public String removeSupplier(int supplierId) {
        try {
            supplierFacade.removeSupplier(supplierId);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Updates the name of the supplier with the given id
    public String updateSupplierName(int supplierId, String newName)
    {
        try {
            supplierFacade.updateSupplierName(supplierId, newName);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Updates the bank account of the supplier with the given id
    public String updateSupplierBankAccount(int supplierId, String newBankAccount) {
        try {
            supplierFacade.updateSupplierBankAccount(supplierId, newBankAccount);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Updates the payment method of the supplier with the given id
    public String updateSupplierPaymentMethod(int supplierId, PaymentMethod newPaymentMethod) {
        try {
            supplierFacade.updateSupplierPaymentMethod(supplierId, newPaymentMethod);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Returns the supllier with the given id
    public String getSupplier(int supplierId) {
        try {
            Supplier supplier = supplierFacade.getSupplier(supplierId);
            return gson.toJson(new ResponseT<>(new Supplier(supplier)));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Returns a list of all suppliers
    public String getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierFacade.getAllSuppliers();
            return gson.toJson(new ResponseT<>(suppliers));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Adds a new contact to the supplier with the given id
    public String addContact(int supplierId, String contactName, String phoneNumber) {
        try {
            supplierFacade.addContact(supplierId, contactName, phoneNumber);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Returns a list of all contacts
    public String getAllContacts() {
        try {
            List<Contact> contacts = supplierFacade.getAllContacts();
            return gson.toJson(new ResponseT<>(contacts));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Supplier Agreement related functions

    // Returns the supplier agreement of the supplier with the given id
    public String getSupplierAgreement(int supplierId) {
        try {
            return gson.toJson(new ResponseT<>(new SupplierAgreement(supplierFacade.getSupplierAgreement(supplierId))));
        } catch (Exception e) {
            return gson.toJson(new ResponseT<>(e.getMessage()));
        }
    }

    // Adds a supplier agreement to the supplier with the given id
    public String addSupplierAgreement(int supplierId) {
        try {
            supplierFacade.addSupplierAgreement(supplierId);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // updates the price of the product with the given catalog number from the supplier with the given id
    public String updateProductPrice(int supplierId, int catalogNumber, double newPrice) {
        try {
            supplierFacade.updateProductPrice(supplierId, catalogNumber, newPrice);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Adds a discount to the product with the given catalog number and amount from the supplier with the given id
    public String addProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int discountPercentage) {
        try {
            supplierFacade.addProductDiscountAccordingToAmount(supplierId, catalogNumber, amount, discountPercentage);
            return gson.toJson(new Response());
        } catch (Exception e){
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Updates the discount of the product with the given catalog number and amount from the supplier with the given id
    public String updateProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int newDiscountPercentage) {
        try {
            supplierFacade.updateProductDiscountAccordingToAmount(supplierId, catalogNumber, amount, newDiscountPercentage);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Removes the discount of the product with the given catalog number and amount from the supplier with the given id
    public String removeProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount) {
        try {
            supplierFacade.removeProductDiscountAccordingToAmount(supplierId, catalogNumber, amount);
            return gson.toJson(new Response());
        }  catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // adds a product with the given catalog number, price and name to the supplier with the given id
    public String addProductToSupplier(int supplierId, int catalogNumber, double price, String name) {
        try {
            supplierFacade.addProductToSupplier(supplierId, catalogNumber, price, name);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }
    
    // Removes the product with the given catalog number from the supplier with the given id
    public String removeProductFromSupplier(int supplierId, int catalogNumber) {
        try {
            supplierFacade.removeProduct(supplierId, catalogNumber);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // updates the name of the product with the given catalog number from the supplier with the given id
    public String updateProductName(int supplierId, int catalogNumber, String newName) {
        try {
            supplierFacade.updateProductName(supplierId, catalogNumber, newName);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }
}
