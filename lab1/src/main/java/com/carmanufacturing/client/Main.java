package com.carmanufacturing.client;

import com.carmanufacturing.domain.builder.*;
import com.carmanufacturing.domain.factory.*;
import com.carmanufacturing.domain.models.*;
import com.carmanufacturing.domain.prototype.CarPrototypeRegistry;
import com.carmanufacturing.domain.singleton.FactoryManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // singleton manager
        FactoryManager fm = FactoryManager.getInstance();

        CarFactory gasFactory = fm.getGasCarFactory();
        Car gasCar = gasFactory.createCar("simple sedan");
        System.out.println("\n[Factory] Created gas car:\n" + gasCar);

        CarFactory electricFactory = fm.getElectricCarFactory();
        Car electricCar = electricFactory.createCar("electric suv");
        System.out.println("\n[Factory] Created electric car:\n" + electricCar);

        CarBuilder sportsBuilder = new SportsCarBuilder();
        CarDirector director = new CarDirector();

        // clone the sample prototype and modify
        CarPrototypeRegistry registry = fm.getPrototypeRegistry();
        Car cloned = registry.getPrototype("SportBase");
        if (cloned != null) {
            cloned.setModelName("SportBase - Custom Clone");
            cloned.getEngine().setHorsepower(420);
            System.out.println("\n[Prototype] Cloned and modified:\n" + cloned);
        } else {
            System.out.println("\n[Prototype] No prototype named SportBase found.");
        }

        // build an electric SUV using builder but parts from electric factory
        // reuse electric factory parts
        Car tesla = electricFactory.createCar("tesla");
        Engine eEngine = tesla.getEngine();
        List<Wheel> suvWheels = tesla.getWheels();
        CarBuilder suvBuilder = new SUVBuilder();
        Car suv = director.constructSUV(suvBuilder, "tesla-SUV", eEngine, suvWheels);
        System.out.println("\n[Combined] Electric SUV built with Factory parts + Builder:\n" + suv);
    }
}
