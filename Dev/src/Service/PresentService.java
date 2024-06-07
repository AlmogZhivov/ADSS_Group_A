package Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Business.OrderFacade;
import Business.SupplierFacade;
import Business.Supplier.PaymentMethod;
import Service.Responses.Response;

public class PresentService {

    private static PresentService instance;
    private final SupplierService ss;
    private final OrderService os;
    private SupplierFacade sf = new SupplierFacade();
    private OrderFacade of = new OrderFacade(sf);


    private PresentService() {
        ss = SupplierService.getInstance(sf);
        os = OrderService.getInstance(of);
    }

    public static PresentService getInstance() {
        if (instance == null)
            instance = new PresentService();
        return instance;
    }

    public Response call(String command) {
        String[] parts = command.split(" ");
        Response errResponse = new Response("Invalid number of args");
        switch (parts[0]) {
            case "help":
                System.out.println("""
                        Available commands:
                        loadData
                        addSupplier name compNumber bankNumber paymentMethod(CASH/BANK_TRANSFER/CREDIT_CARD)
                        removeSupplier supplierId
                        updateSupplierName supplierId newName
                        updateSupplierBankAccount supplierId newBankAccount
                        addGeneralOrder catalogNumber1-amount1,catalogNumber2-amount2 supplierId
                        addRepOrder catalogNumber1-amount1,catalogNumber2-amount2 supplierId day
                        updateOrders
                        removeOrder orderId
                        addProduct orderId catalogNumber amount
                        removeProduct orderId catalogNumber
                        addContact supplierId name phone
                        updateProductPrice supplierId catalogNumber newPrice
                        updateSupplierPaymentMethod supplierId paymentMethod(CASH/BANK_TRANSFER/CREDIT_CARD)
                        getAllSuppliers
                        getAllContacts
                        getSupplierAgreement supplierId
                        getSupplier supplierId
                        getOrder orderId
                        getAllOrders
                        getOrderPrice orderId
                        addProductDiscountAccordingToAmount supplierId catalogNumber amount discount
                        updateProductDiscountAccordingToAmount supplierId catalogNumber amount newDiscount
                        removeProductDiscountAccordingToAmount supplierId catalogNumber amount
                        updateProductName supplierId catalogNumber newName
                        addProductToSupplier supplierId catalogNumber price name
                        removeProductFromSupplier supplierId catalogNumber
                        exit
                        """);
                        return new Response();
            case "loadData":
                return ss.loadData();            
            case "addSupplier":
                return parts.length == 5 ? ss.addSupplier(parts[1], parts[2], parts[3], PaymentMethod.valueOf(parts[4])) : errResponse;
            case "removeSupplier":
                return parts.length == 2 ? ss.removeSupplier(Integer.parseInt(parts[1])) : errResponse;
            case "updateSupplierName":
                return parts.length == 3 ? ss.updateSupplierName(Integer.parseInt(parts[1]), parts[2]) : errResponse;
            case "updateSupplierBankAccount":
                return parts.length == 3 ? ss.updateSupplierBankAccount(Integer.parseInt(parts[1]), parts[2]) : errResponse;
            case "addGeneralOrder":
                // Format: catalogNumber1-amount1,catalogNumber2-amount2 supplierId
                return parts.length == 3 ? os.addGeneralOrder(itemIdAndAmount(parts[1]), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(parts[2])) : errResponse;
            case "addRepOrder":
                // Format: catalogNumber1-amount1,catalogNumber2-amount2 supplierId day
                return parts.length == 4 ? os.addRepOrder(itemIdAndAmount(parts[1]), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])) : errResponse;
            case "updateOrders":
                return os.updateOrders();
            case "removeOrder":
                return parts.length == 2 ? os.removeOrder(Integer.parseInt(parts[1])) : errResponse;
            case "addProduct":
                return parts.length == 4 ? os.addProduct(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])) : errResponse;
            case "removeProduct":    
                return parts.length == 3 ? os.removeProduct(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) : errResponse;
            case "addContact":
                return parts.length == 4 ? ss.addContact(Integer.parseInt(parts[1]), parts[2], parts[3]) : errResponse;
            case "updateProductPrice":
                return parts.length == 4 ? ss.updateProductPrice(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3])) : errResponse;
            case "updateSupplierPaymentMethod":
                return parts.length == 3 ? ss.updateSupplierPaymentMethod(Integer.parseInt(parts[1]), PaymentMethod.valueOf(parts[2])) : errResponse;
            case "getAllSuppliers":
                return ss.getAllSuppliers();
            case "getAllContacts":
                return ss.getAllContacts();
            case "getSupplierAgreement":
                return parts.length == 2 ? ss.getSupplierAgreement(Integer.parseInt(parts[1])) : errResponse;
            case "getSupplier":
                return parts.length == 2 ? ss.getSupplier(Integer.parseInt(parts[1])) : errResponse;
            case "getOrder":
                return parts.length == 2 ? os.getOrder(Integer.parseInt(parts[1])) : errResponse;
            case "getAllOrders":
                return os.getAllOrders();
            case "getOrderPrice":
                return parts.length == 2 ? os.getOrderPrice(Integer.parseInt(parts[1])) : errResponse;
            case "addProductDiscountAccordingToAmount":
                return parts.length == 5 ? ss.addProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])) : errResponse;
            case "updateProductDiscountAccordingToAmount":
                return parts.length == 5 ? ss.updateProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])) : errResponse;
            case "removeProductDiscountAccordingToAmount":
                return parts.length == 4 ? ss.removeProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])) : errResponse;
            case "updateProductName":  
                return parts.length == 4 ? ss.updateProductName(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]) : errResponse;
            case "addProductToSupplier":
                return parts.length == 5 ? ss.addProductToSupplier(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[4]) : errResponse;
            case "removeProductFromSupplier":
                return parts.length == 3 ? ss.removeProductFromSupplier(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) : errResponse;
            default:
                return new Response("Invalid command");
        }
    }

    // A helper method to parse the item id and amount from the command
    private Map<Integer, Integer> itemIdAndAmount(String string) {
        Map<Integer, Integer> itemIdAndAmount = new HashMap<>();
        String[] orderDetails = string.split(",");
        for (String detail : orderDetails) {
            String[] item = detail.split("-");
            int itemId = Integer.parseInt(item[0]);
            int amount = Integer.parseInt(item[1]);
            itemIdAndAmount.put(itemId, amount);
        }
        return itemIdAndAmount;
    }
}
