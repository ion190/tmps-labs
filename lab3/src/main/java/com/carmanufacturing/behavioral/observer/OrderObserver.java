package com.carmanufacturing.behavioral.observer;

public interface OrderObserver {
    void onOrderEvent(String eventType,
                      String orderDescription,
                      double orderTotal,
                      double discountPercent);
}
