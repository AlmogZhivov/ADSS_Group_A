package Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Business.OrderFacade;
import Business.SupplierFacade;
import Business.Supplier.PaymentMethod;

public class PresentService {

    private static PresentService instance;
    private final SupplierService ss;
    private final OrderService os;
    private OrderFacade of;
    private SupplierFacade sf;

    private PresentService() {
        ss = SupplierService.getInstance(sf);
        os = OrderService.getInstance(of);
    }

    public static PresentService getInstance() {
        if (instance == null)
            instance = new PresentService();
        return instance;
    }

    public String call(String command) {

        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "addSupplier":
                return parts.length == 4 ? ss.addSupplier(parts[1], parts[2], parts[3], PaymentMethod.valueOf(parts[4])) : "Invalid number of args";
            case "removeSupplier":
                return parts.length == 2 ? ss.removeSupplier(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "updateSupplierName":
                return parts.length == 3 ? ss.updateSupplierName(Integer.parseInt(parts[1]), parts[2]) : "Invalid number of args";
            case "updateSupplierBankAccount":
                return parts.length == 3 ? ss.updateSupplierBankAccount(Integer.parseInt(parts[1]), parts[2]) : "Invalid number of args";
            case "addGeneralOrder":
                // Format: catalogNumber1-amount1,catalogNumber2-amount2 supplierId
                return parts.length == 3 ? os.addGeneralOrder(itemIdAndAmount(parts[1]), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(parts[2])) : "Invalid number of args";
            case "addRepOrder":
                // Format: catalogNumber1-amount1,catalogNumber2-amount2 supplierId day
                return parts.length == 4 ? os.addRepOrder(itemIdAndAmount(parts[1]), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])) : "Invalid number of args";
            case "updateOrders":
                return os.updateOrders();
            case "removeOrder":
                return parts.length == 2 ? os.removeOrder(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "addProduct":
                return parts.length == 4 ? os.addProduct(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])) : "Invalid number of args";
            case "removeProduct":    
                return parts.length == 3 ? os.removeProduct(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) : "Invalid number of args";
            case "addSupplierAgreement":
                return parts.length == 2 ? ss.addSupplierAgreement(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "addContact":
                return parts.length == 4 ? ss.addContact(Integer.parseInt(parts[1]), parts[2], parts[3]) : "Invalid number of args";
            case "updateProductPrice":
                return parts.length == 4 ? ss.updateProductPrice(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3])) : "Invalid number of args";
            case "updateSupplierPaymentMethod":
                return parts.length == 3 ? ss.updateSupplierPaymentMethod(Integer.parseInt(parts[1]), PaymentMethod.valueOf(parts[2])) : "Invalid number of args";
            case "getAllSuppliers":
                return ss.getAllSuppliers();
            case "getAllContacts":
                return ss.getAllContacts();
            case "getSupplierAgreement":
                return parts.length == 2 ? ss.getSupplierAgreement(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "getSupplier":
                return parts.length == 2 ? ss.getSupplier(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "getOrder":
                return parts.length == 2 ? os.getOrder(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "getAllOrders":
                return os.getAllOrders();
            case "getOrderPrice":
                return parts.length == 2 ? os.getOrderPrice(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "getOrderString":
                return parts.length == 2 ? os.getOrderString(Integer.parseInt(parts[1])) : "Invalid number of args";
            case "addProductDiscountAccordingToAmount":
                return parts.length == 5 ? ss.addProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])) : "Invalid number of args";
            case "updateProductDiscountAccordingToAmount":
                return parts.length == 5 ? ss.updateProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])) : "Invalid number of args";
            case "removeProductDiscountAccordingToAmount":
                return parts.length == 4 ? ss.removeProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])) : "Invalid number of args";
            case "updateProductName":  
                return parts.length == 4 ? ss.updateProductName(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]) : "Invalid number of args";
            case "addProductToSupplier":
                return parts.length == 5 ? ss.addProductToSupplier(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[4]) : "Invalid number of args";
            case "removeProductFromSupplier":
                return parts.length == 3 ? ss.removeProductFromSupplier(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) : "Invalid number of args";
            case "getSupplierString":
                return parts.length == 2 ? ss.getSupplierString(Integer.parseInt(parts[1])) : "Invalid number of args";
            default:
                return "Invalid command";
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
