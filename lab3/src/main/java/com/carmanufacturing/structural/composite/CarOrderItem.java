package com.carmanufacturing.structural.composite;

import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.behavioral.strategy.PricingStrategy;


public class CarOrderItem implements OrderComponent {
    private final Car car;
    private double price;
    private PricingStrategy pricingStrategy;

    public CarOrderItem(Car car, double price) {
        this.car = car;
        this.price = price;
    }

    public CarOrderItem(Car car, PricingStrategy strategy) {
        this.car = car;
        this.pricingStrategy = strategy;
        this.price = (strategy != null ? strategy.calculatePrice(car) : 0.0);
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
        if (strategy != null) {
            this.price = strategy.calculatePrice(car);
        }
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return car == null ? "Unknown Car" : car.getModelName();
    }

    public Car getCar() {
        return car;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
