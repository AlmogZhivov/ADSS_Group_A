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

    // Switches the supplier agreement to a new one
    public Response addSupplierAgreement(int supplierId) {
        try {
            supplierFacade.addSupplierAgreement(supplierId);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Changes a product's price
    public Response updateProductPrice(int supplierId, int catalogNumber, double newPrice) {
        try {
            supplierFacade.updateProductPrice(supplierId, catalogNumber, newPrice);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Sets a new product discount according to an amount given
    public Response addProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int discountPercentage) {
        try {
            supplierFacade.addProductDiscountAccordingToAmount(supplierId, catalogNumber, amount, discountPercentage);
            return gson.toJson(new Response());
        } catch (Exception e){
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Updates the discount to a discount given
    public Response updateProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int newDiscountPercentage) {
        try {
            supplierFacade.updateProductDiscountAccordingToAmount(supplierId, catalogNumber, amount, newDiscountPercentage);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Removes a product discount
    public Response removeProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount) {
        try {
            supplierFacade.removeProductDiscountAccordingToAmount(supplierId, catalogNumber, amount);
            return gson.toJson(new Response());
        }  catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Adds a new product supplied by the given supplier
    public Response addProductToSupplier(int supplierId, int catalogNumber, double price, String name) {
        try {
            supplierFacade.addProductToSupplier(supplierId, catalogNumber, price, name);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Removes a product from the supplied products for a given supplier
    public Response removeProductFromSupplier(int supplierId, int catalogNumber) {
        try {
            supplierFacade.removeProduct(supplierId, catalogNumber);
            return gson.toJson(new Response());
        } catch (Exception e) {
            return gson.toJson(new Response(e.getMessage()));
        }
    }

    // Changes a supplier product's name
    public Response updateProductName(int supplierId, int catalogNumber, String newName) {
        try {
            supplierFacade.updateProductName(supplierId, catalogNumber, newName);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Returns a string describing the supplier for printing
    public Response getSupplierString(int supplierId) {
        try {
            String str = supplierFacade.getSupplierString(supplierId);
            return new ResponseT<>(str);
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }
}
