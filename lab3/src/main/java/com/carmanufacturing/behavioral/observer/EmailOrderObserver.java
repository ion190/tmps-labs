package com.carmanufacturing.behavioral.observer;

public class EmailOrderObserver implements OrderObserver {

    @Override
    public void onOrderEvent(String eventType,
                             String orderDescription,
                             double orderTotal,
                             double discountPercent) {

        switch (eventType) {
            case "ORDER_CREATED":
                System.out.println("[Observer-Email] New order created: " +
                        orderDescription + ", total=" + orderTotal);
                break;
            case "DISCOUNT_APPROVED":
                System.out.println("[Observer-Email] Discount " + discountPercent +
                        "% approved for " + orderDescription + " (total=" + orderTotal + ")");
                break;
            case "DISCOUNT_REJECTED":
                System.out.println("[Observer-Email] Discount " + discountPercent +
                        "% REJECTED for " + orderDescription + " (total=" + orderTotal + ")");
                break;
            default:
                System.out.println("[Observer-Email] Event " + eventType +
                        " for " + orderDescription);
        }
    }
}
