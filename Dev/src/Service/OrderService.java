package Service;

import Bussiness.OrderFacade;

public class OrderService {

    private OrderFacade orderFacade;

    public OrderService(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }
}
