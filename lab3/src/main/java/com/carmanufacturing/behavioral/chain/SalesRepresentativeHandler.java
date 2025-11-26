package com.carmanufacturing.behavioral.chain;

// sales representative can approve small discounts.
public class SalesRepresentativeHandler extends DiscountHandler {

    @Override
    protected boolean process(DiscountRequest request) {
        double discount = request.getDiscountPercent();
        if (discount <= 5.0) {
            request.setApproved(true, "Sales Representative");
            System.out.println("[Chain] Sales Representative approved " + discount + "% discount.");
            return true;
        }
        System.out.println("[Chain] Sales Representative cannot approve " + discount + "%, passing up...");
        return false;
    }
}
