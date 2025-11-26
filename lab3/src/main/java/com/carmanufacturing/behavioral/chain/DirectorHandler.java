package com.carmanufacturing.behavioral.chain;

// director can approve larger discounts up to a certain limit
public class DirectorHandler extends DiscountHandler {

    @Override
    protected boolean process(DiscountRequest request) {
        double discount = request.getDiscountPercent();
        if (discount > 15.0 && discount <= 30.0) {
            request.setApproved(true, "Director");
            System.out.println("[Chain] Director approved " + discount + "% discount.");
            return true;
        }
        System.out.println("[Chain] Director cannot approve " + discount + "% discount (too high).");
        return false;
    }
}
