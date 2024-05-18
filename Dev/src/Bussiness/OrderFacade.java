package Bussiness;

import java.util.*;

public class OrderFacade {

    private Map<Integer, Order> orderList;
    private int id;

    public OrderFacade(){
        this.orderList = new HashMap<>();
        this.id = 0;
    }

    public void addOrder(Map<Integer, Integer> products, Date shipmentDate){

        id++;
    }


}
