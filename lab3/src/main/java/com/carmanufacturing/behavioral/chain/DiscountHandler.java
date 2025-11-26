package com.carmanufacturing.behavioral.chain;

public abstract class DiscountHandler {
    private DiscountHandler next;

    public DiscountHandler setNext(DiscountHandler next) {
        this.next = next;
        return next;
    }

    public void handle(DiscountRequest request) {
        if (request == null) return;

        boolean handled = process(request);
        if (!handled && next != null) {
            next.handle(request);
        }
    }

    protected abstract boolean process(DiscountRequest request);
}
