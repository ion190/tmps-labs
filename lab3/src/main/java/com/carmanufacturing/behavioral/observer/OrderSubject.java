package com.carmanufacturing.behavioral.observer;

public interface OrderSubject {
    void addObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);

    void notifyObservers(String eventType,
                         String orderDescription,
                         double orderTotal,
                         double discountPercent);
}
