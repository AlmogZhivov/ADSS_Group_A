package Service;

import Business.SupplierFacade;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Business.Contact;
import Business.Supplier;
import Business.SupplierAgreement;
import Business.Supplier.PaymentMethod;
import Service.Responses.ResponseT;
import Service.Responses.Response;

public class SupplierService {
    private SupplierFacade supplierFacade;

    public SupplierService(SupplierFacade supplierFacade) {
        this.supplierFacade = supplierFacade;
    }

    // Supplier related functions

    // Adds a new supplier to the system
    public Response addSupplier(int supplierId, String name, String compNumber, String bankNumber, PaymentMethod payment) {
        try {
            supplierFacade.addSupplier(supplierId, name, compNumber, bankNumber, payment);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Removes the supplier with the given id
    public Response removeSupplier(int supplierId) {
        try {
            supplierFacade.removeSupplier(supplierId);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Updates the name of the supplier with the given id
    public Response updateSupplierName(int supplierId, String newName)
    {
        try {
            supplierFacade.updateSupplierName(supplierId, newName);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Updates the bank account of the supplier with the given id
    public Response updateSupplierBankAccount(int supplierId, String newBankAccount) {
        try {
            supplierFacade.updateSupplierBankAccount(supplierId, newBankAccount);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Updates the payment method of the supplier with the given id
    public Response updateSupplierPaymentMethod(int supplierId, PaymentMethod newPaymentMethod) {
        try {
            supplierFacade.updateSupplierPaymentMethod(supplierId, newPaymentMethod);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Returns the supllier with the given id
    public ResponseT<Supplier> getSupplier(int supplierId) {
        try {
            Supplier supplier = supplierFacade.getSupplier(supplierId);
            return new ResponseT<>(new Supplier(supplier));
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }

    // Returns a list of all suppliers
    public ResponseT<List<Supplier>> getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierFacade.getAllSuppliers();
            return new ResponseT<>(suppliers);
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }

    // Adds a new contact to the supplier with the given id
    public Response addContact(int supplierId, String contactName, String phoneNumber) {
        try {
            supplierFacade.addContact(supplierId, contactName, phoneNumber);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    // Returns a list of all contacts
    public ResponseT<List<Contact>> getAllContacts() {
        try {
            List<Contact> contacts = supplierFacade.getAllContacts();
            return new ResponseT<>(contacts);
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }

    // Supplier Agreement related functions

    public ResponseT<SupplierAgreement> getSupplierAgreement(int supplierId) {
        try {
            return new ResponseT<>(new SupplierAgreement(supplierFacade.getSupplierAgreement(supplierId)));
        } catch (Exception e) {
            return new ResponseT<>(e.getMessage());
        }
    }

    public Response updateProductPrice(int supplierId, int catalogNumber, double newPrice) {
        try {
            supplierFacade.updateProductPrice(supplierId, catalogNumber, newPrice);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }


    public Response addProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int discountPercentage) {
        try {
            supplierFacade.addProductDiscountAccordingToAmount(supplierId, catalogNumber, amount, discountPercentage);
            return new Response();
        } catch (Exception e){
            return new Response(e.getMessage());
        }
    }

    public Response updateProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount, int newDiscountPercentage) {
        try {
            supplierFacade.updateProductDiscountAccordingToAmount(supplierId, catalogNumber, amount, newDiscountPercentage);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response removeProductDiscountAccordingToAmount(int supplierId, int catalogNumber, int amount) {
        try {
            supplierFacade.removeProductDiscountAccordingToAmount(supplierId, catalogNumber, amount);
            return new Response();
        }  catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response addProductToSupplier(int supplierId, int catalogNumber, double price, String name) {
        try {
            supplierFacade.addProductToSupplier(supplierId, catalogNumber, price, name);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response removeItemFromSupplier(int supplierId, int catalogNumber) {
        try {
            supplierFacade.removeItem(supplierId, catalogNumber);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    public Response updateItemName(int supplierId, int catalogNumber, String newName) {
        try {
            supplierFacade.updateItemName(supplierId, catalogNumber, newName);
            return new Response();
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }
}
