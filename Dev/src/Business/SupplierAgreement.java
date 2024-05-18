package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SupplierAgreement {

    // <catalogNumber, product>
    private final Map<Integer, SupplierProduct> products;

    public SupplierAgreement(SupplierAgreement agreement) {
        products = new ConcurrentHashMap<>();
        for(SupplierProduct supplierProduct : agreement.getProducts().values())
        products.put(supplierProduct.getCatalogNumber(), new SupplierProduct(supplierProduct));
    }

    public Map<Integer, SupplierProduct> getProducts() {
        return products;
    }

    // More functions needed to be added regarding the products
}
