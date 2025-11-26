package com.carmanufacturing.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrderNotifier implements OrderSubject {
    private final List<OrderObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String eventType,
                                String orderDescription,
                                double orderTotal,
                                double discountPercent) {
        for (OrderObserver o : observers) {
            o.onOrderEvent(eventType, orderDescription, orderTotal, discountPercent);
        }
    }
}
