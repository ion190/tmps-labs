package com.carmanufacturing.client;

import com.carmanufacturing.behavioral.chain.DiscountHandler;
import com.carmanufacturing.behavioral.chain.DiscountRequest;
import com.carmanufacturing.behavioral.chain.DirectorHandler;
import com.carmanufacturing.behavioral.chain.ManagerHandler;
import com.carmanufacturing.behavioral.chain.SalesRepresentativeHandler;
import com.carmanufacturing.behavioral.strategy.PricingStrategy;
import com.carmanufacturing.behavioral.strategy.SportPricingStrategy;
import com.carmanufacturing.behavioral.strategy.StandardPricingStrategy;
import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.structural.composite.CarOrderItem;
import com.carmanufacturing.structural.composite.OrderComponent;
import com.carmanufacturing.structural.composite.OrderGroup;
import com.carmanufacturing.structural.facade.ManufacturingFacade;

import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        ManufacturingFacade facade = new ManufacturingFacade();

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

        System.out.println("[Strategy] Order total (sum of strategy-based prices): "
                + mainOrder.getPrice());

        // CHAIN OF RESPONSIBILITY: discount approval
        System.out.println("\nChain of Responsibility: discount approval");

        // build the approval chain: SalesRep -> Manager -> Director
        DiscountHandler sales    = new SalesRepresentativeHandler();
        DiscountHandler manager  = new ManagerHandler();
        DiscountHandler director = new DirectorHandler();
        sales.setNext(manager).setNext(director);

        double orderTotal = mainOrder.getPrice();

        // try three different discount requests
        applyDiscountRequest(sales, orderTotal, 3.0);   // should be approved by SalesRep
        applyDiscountRequest(sales, orderTotal, 10.0);  // should be approved by Manager
        applyDiscountRequest(sales, orderTotal, 25.0);  // should be approved by Director
        applyDiscountRequest(sales, orderTotal, 40.0);  // too high, rejected

    }

    // helper: send a discount request through the chain and print the result
    private static void applyDiscountRequest(DiscountHandler chainStart,
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
        } else {
            System.out.println("[Chain] Discount NOT approved. Final price remains: " + orderTotal);
        }
    }
}
