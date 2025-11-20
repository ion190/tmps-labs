package com.carmanufacturing.domain.factory;

import com.carmanufacturing.domain.models.Car;

public abstract class CarFactory {
    protected PartsFactory partsFactory;

    public CarFactory(PartsFactory partsFactory) {
        this.partsFactory = partsFactory;
    }

    public abstract Car createCar(String modelName);
}
