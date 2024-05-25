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
                return ss.addSupplier(parts[1], parts[2], parts[3], PaymentMethod.valueOf(parts[4]));
            case "removeSupplier":
                return ss.removeSupplier(Integer.parseInt(parts[1]));
            case "updateSupplierName":
                return ss.updateSupplierName(Integer.parseInt(parts[1]), parts[2]);
            case "updateSupplierBankAccount":
                return ss.updateSupplierBankAccount(Integer.parseInt(parts[1]), parts[2]);
            case "addGeneralOrder":
                // Format: catalogNumber1-amount1,catalogNumber2-amount2 supplierId
                return os.addGeneralOrder(itemIdAndAmount(parts[1]), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(parts[2]));
            case "addRepOrder":
                // Format: catalogNumber1-amount1,catalogNumber2-amount2 supplierId day
                return os.addRepOrder(itemIdAndAmount(parts[1]), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            case "updateOrders":
                return os.updateOrders();
            case "removeOrder":
                return os.removeOrder(Integer.parseInt(parts[1]));
            case "addProduct":
                return os.addProduct(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            case "removeProduct":    
                return os.removeProduct(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            case "addSupplierAgreement":
                return ss.addSupplierAgreement(Integer.parseInt(parts[1]));
            case "addContact":
                return ss.addContact(Integer.parseInt(parts[1]), parts[2], parts[3]);
            case "updateProductPrice":
                return ss.updateProductPrice(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
            case "updateSupplierPaymentMethod":
                return ss.updateSupplierPaymentMethod(Integer.parseInt(parts[1]), PaymentMethod.valueOf(parts[2]));
            case "getAllSuppliers":
                return ss.getAllSuppliers();
            case "getAllContacts":
                return ss.getAllContacts();
            case "getSupplierAgreement":
                return ss.getSupplierAgreement(Integer.parseInt(parts[1]));
            case "getSupplier":
                return ss.getSupplier(Integer.parseInt(parts[1]));
            case "getOrder":
                return os.getOrder(Integer.parseInt(parts[1]));
            case "getAllOrders":
                return os.getAllOrders();
            case "getOrderPrice":
                return os.getOrderPrice(Integer.parseInt(parts[1]));
            case "getOrderString":
                return os.getOrderString(Integer.parseInt(parts[1]));
            case "addProductDiscountAccordingToAmount":
                return ss.addProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            case "updateProductDiscountAccordingToAmount":
                return ss.updateProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            case "removeProductDiscountAccordingToAmount":
                return ss.removeProductDiscountAccordingToAmount(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            case "updateProductName":  
                return ss.updateProductName(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
            case "addProductToSupplier":
                return ss.addProductToSupplier(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[4]);
            case "removeProductFromSupplier":
                return ss.removeProductFromSupplier(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            case "getSupplierString":
                return ss.getSupplierString(Integer.parseInt(parts[1]));
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
