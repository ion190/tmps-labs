package com.carmanufacturing.behavioral.strategy;

import com.carmanufacturing.domain.models.Car;

public class SportPricingStrategy implements PricingStrategy {

    private final PricingStrategy baseStrategy;

    public SportPricingStrategy(PricingStrategy baseStrategy) {
        this.baseStrategy = baseStrategy;
    }

    @Override
    public double calculatePrice(Car car) {
        double base = baseStrategy.calculatePrice(car);
        // add 15%
        return base * 1.15;
    }
}
