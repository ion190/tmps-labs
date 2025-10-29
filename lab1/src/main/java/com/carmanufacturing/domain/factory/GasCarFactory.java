package com.carmanufacturing.domain.factory;

import com.carmanufacturing.domain.models.*;

import java.util.List;

// Factory Method
public class GasCarFactory extends CarFactory {
    public GasCarFactory(PartsFactory partsFactory) {
        super(partsFactory);
    }

    @Override
    public Car createCar(String modelName) {
        Car car = new Car();
        car.setModelName(modelName);
        car.setCarType(CarType.SEDAN);
        Engine engine = partsFactory.createEngine();
        car.setEngine(engine);
        List<Wheel> wheels = partsFactory.createWheels();
        car.setWheels(wheels);
        car.setPaint(partsFactory.createPaint());
        return car;
    }
}
