package Service;

import Bussiness.OrderFacade;
import netscape.javascript.JSObject;

import java.util.Date;
import java.util.Map;

public class OrderService {

    private OrderFacade orderFacade;

    public OrderService(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public void order(int supplierId, Map<Integer, Integer> productList, Date shipmentDate){

    }


}
