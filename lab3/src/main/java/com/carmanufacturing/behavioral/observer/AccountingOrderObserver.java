package com.carmanufacturing.behavioral.observer;

public class AccountingOrderObserver implements OrderObserver {

    @Override
    public void onOrderEvent(String eventType,
                             String orderDescription,
                             double orderTotal,
                             double discountPercent) {

        switch (eventType) {
            case "ORDER_CREATED":
                System.out.println("[Observer-Accounting] Order created: " +
                        orderDescription + ", total=" + orderTotal);
                break;
            case "DISCOUNT_APPROVED":
                System.out.println("[Observer-Accounting] Discount " + discountPercent +
                        "% approved by chain for " + orderDescription);
                break;
            case "DISCOUNT_REJECTED":
                System.out.println("[Observer-Accounting] Discount " + discountPercent +
                        "% rejected for " + orderDescription);
                break;
            default:
                System.out.println("[Observer-Accounting] Event " + eventType +
                        " for " + orderDescription);
        }
    }
}
