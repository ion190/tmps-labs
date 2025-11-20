package com.carmanufacturing.structural.composite;

import com.carmanufacturing.domain.models.Car;

// Leaf node in the order composite,single car order item
public class CarOrderItem implements OrderComponent {
    private final Car car;
    private final double price;

    public CarOrderItem(Car car, double price) {
        this.car = car;
        this.price = price;
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
}
