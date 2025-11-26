package com.carmanufacturing.behavioral.strategy;

import com.carmanufacturing.domain.models.Car;

public interface PricingStrategy {
    double calculatePrice(Car car);
}
