package com.carmanufacturing.behavioral.chain;

public class DiscountRequest {
    private final double orderTotal;
    private final double discountPercent;
    private boolean approved;
    private String approvedBy;

    public DiscountRequest(double orderTotal, double discountPercent) {
        this.orderTotal = orderTotal;
        this.discountPercent = discountPercent;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved, String approvedBy) {
        this.approved = approved;
        this.approvedBy = approvedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }
}
