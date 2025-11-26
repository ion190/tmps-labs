package com.carmanufacturing.client;

import com.carmanufacturing.behavioral.chain.DiscountHandler;
import com.carmanufacturing.behavioral.chain.DiscountRequest;
import com.carmanufacturing.behavioral.chain.DirectorHandler;
import com.carmanufacturing.behavioral.chain.ManagerHandler;
import com.carmanufacturing.behavioral.chain.SalesRepresentativeHandler;
import com.carmanufacturing.behavioral.observer.AccountingOrderObserver;
import com.carmanufacturing.behavioral.observer.EmailOrderObserver;
import com.carmanufacturing.behavioral.observer.OrderSubject;
import com.carmanufacturing.behavioral.observer.SimpleOrderNotifier;
import com.carmanufacturing.behavioral.strategy.PricingStrategy;
import com.carmanufacturing.behavioral.strategy.SportPricingStrategy;
import com.carmanufacturing.behavioral.strategy.StandardPricingStrategy;
import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.structural.composite.CarOrderItem;
import com.carmanufacturing.structural.composite.OrderGroup;
import com.carmanufacturing.structural.facade.ManufacturingFacade;

public class Main {
    public static void main(String[] args) {
        ManufacturingFacade facade = new ManufacturingFacade();

        // OBSERVER: set up order notifier and observers
        OrderSubject notifier = new SimpleOrderNotifier();
        notifier.addObserver(new EmailOrderObserver());
        notifier.addObserver(new AccountingOrderObserver());

        // STRATEGY: different pricing rules
        System.out.println("\nStrategy: pricing");

        // create some cars using the existing system
        Car familySedan = facade.createStandardGasCar("toyota-prius");
        Car sportCar = facade.createSportsFromElectricParts("bugati");

        // define pricing strategies
        PricingStrategy standardPricing = new StandardPricingStrategy();
        PricingStrategy sportPricing    = new SportPricingStrategy(standardPricing);

        // create order items that use strategies to compute their prices
        CarOrderItem sedanItem = new CarOrderItem(familySedan, standardPricing);
        CarOrderItem sportItem = new CarOrderItem(sportCar,   sportPricing);

        System.out.println("[Strategy] Sedan '" + familySedan.getModelName()
                + "' price (standard): " + sedanItem.getPrice());
        System.out.println("[Strategy] Sport '" + sportCar.getModelName()
                + "' price (sport strategy): " + sportItem.getPrice());

        OrderGroup mainOrder = new OrderGroup("MainOrder-Behavioral");
        mainOrder.add(sedanItem);
        mainOrder.add(sportItem);

        // add an extra car with standard pricing
        Car corporateSUV = facade.createStandardGasCar("Corporate-SUV");
        CarOrderItem corpItem = new CarOrderItem(corporateSUV, standardPricing);
        mainOrder.add(corpItem);

        double orderTotal = mainOrder.getPrice();

        System.out.println("[Strategy] Order total (sum of strategy-based prices): "
                + orderTotal);

        // notify observers that a new order was created
        notifier.notifyObservers("ORDER_CREATED",
                mainOrder.getDescription(),
                orderTotal,
                0.0);

        // CHAIN OF RESPONSIBILITY: discount approval
        System.out.println("\nChain of Responsibility: discount approval");

        // build the approval chain: SalesRep -> Manager -> Director
        DiscountHandler sales    = new SalesRepresentativeHandler();
        DiscountHandler manager  = new ManagerHandler();
        DiscountHandler director = new DirectorHandler();
        sales.setNext(manager).setNext(director);

        // try different discount requests
        applyDiscountRequest(sales, notifier, mainOrder.getDescription(), orderTotal, 3.0);   // SalesRep
        applyDiscountRequest(sales, notifier, mainOrder.getDescription(), orderTotal, 10.0);  // Manager
        applyDiscountRequest(sales, notifier, mainOrder.getDescription(), orderTotal, 25.0);  // Director
        applyDiscountRequest(sales, notifier, mainOrder.getDescription(), orderTotal, 40.0);  // rejected
    }

    // helper: send a discount request through the chain and print + notify
    private static void applyDiscountRequest(DiscountHandler chainStart,
                                             OrderSubject notifier,
                                             String orderDescription,
                                             double orderTotal,
                                             double discountPercent) {
        System.out.println("\n[Chain] Requesting " + discountPercent
                + "% discount for order total " + orderTotal);
        DiscountRequest request = new DiscountRequest(orderTotal, discountPercent);
        chainStart.handle(request);

        if (request.isApproved()) {
            double finalPrice = orderTotal * (1.0 - request.getDiscountPercent() / 100.0);
            System.out.println("[Chain] Discount APPROVED by " + request.getApprovedBy());
            System.out.println("[Chain] Final price after discount: " + finalPrice);

            notifier.notifyObservers(
                    "DISCOUNT_APPROVED",
                    orderDescription,
                    orderTotal,
                    discountPercent
            );
        } else {
            System.out.println("[Chain] Discount NOT approved. Final price remains: " + orderTotal);

            notifier.notifyObservers(
                    "DISCOUNT_REJECTED",
                    orderDescription,
                    orderTotal,
                    discountPercent
            );
        }
    }
}
