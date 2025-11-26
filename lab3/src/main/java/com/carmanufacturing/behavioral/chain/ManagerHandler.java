package com.carmanufacturing.behavioral.chain;

// manager can approve medium discounts
public class ManagerHandler extends DiscountHandler {

    @Override
    protected boolean process(DiscountRequest request) {
        double discount = request.getDiscountPercent();
        if (discount > 5.0 && discount <= 15.0) {
            request.setApproved(true, "Manager");
            System.out.println("[Chain] Manager approved " + discount + "% discount.");
            return true;
        }
        System.out.println("[Chain] Manager cannot approve " + discount + "%, passing up...");
        return false;
    }
}
