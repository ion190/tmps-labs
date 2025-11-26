package com.carmanufacturing.behavioral.strategy;

import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.domain.models.CarType;
import com.carmanufacturing.domain.models.Engine;

// price is based on car type and engine horsepower.
public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Car car) {
        if (car == null) return 0.0;

        double base = 20000.0;
        CarType type = car.getCarType();

        if (type != null) {
            switch (type) {
                case SEDAN:
                    base = 22000.0;
                    break;
                case SUV:
                    base = 28000.0;
                    break;
                case SPORTS:
                    base = 35000.0;
                    break;
                case ELECTRIC:
                    base = 30000.0;
                    break;
                case HYBRID:
                    base = 29000.0;
                    break;
                default:
                    base = 20000.0;
            }
        }

        Engine engine = car.getEngine();
        if (engine != null) {
            int hp = engine.getHorsepower();
            if (hp > 100) {
                base += (hp - 100) * 20; // small extra cost per HP above 100
            }
        }

        return base;
    }
}
