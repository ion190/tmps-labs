package com.carmanufacturing.structural.facade;


import com.carmanufacturing.domain.builder.CarBuilder;
import com.carmanufacturing.domain.builder.CarDirector;
import com.carmanufacturing.domain.builder.SportsCarBuilder;
import com.carmanufacturing.domain.factory.CarFactory;
import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;
import com.carmanufacturing.domain.singleton.FactoryManager;

import java.util.ArrayList;
import java.util.List;

// simplified API for common manufacturing operations.
public class ManufacturingFacade {
    private final FactoryManager manager = FactoryManager.getInstance();
    private final CarDirector director = new CarDirector();

    public Car createStandardGasCar(String modelName) {
        CarFactory f = manager.getGasCarFactory();
        return f.createCar(modelName);
    }

    public Car createSportsFromElectricParts(String modelName) {
        CarFactory electric = manager.getElectricCarFactory();
        Car temp = electric.createCar("tmp");
        Engine e = new Engine(temp.getEngine());
        List<Wheel> wheels = new ArrayList<>(temp.getWheels());

        CarBuilder builder = new SportsCarBuilder();
        return director.constructSportsCar(builder, modelName, e, wheels);
    }
}
