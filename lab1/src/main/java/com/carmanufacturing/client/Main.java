package com.carmanufacturing.client;

import com.carmanufacturing.domain.builder.*;
import com.carmanufacturing.domain.factory.*;
import com.carmanufacturing.domain.models.*;
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

        CarDirector director = new CarDirector();

        // build an electric SUV using builder but parts from electric factory
        Car tesla = electricFactory.createCar("tesla");
        Engine eEngine = tesla.getEngine();
        List<Wheel> suvWheels = tesla.getWheels();
        CarBuilder suvBuilder = new SUVBuilder();
        Car suv = director.constructSUV(suvBuilder, "tesla-SUV", eEngine, suvWheels);
        System.out.println("\n[Combined] Electric SUV built with Factory parts + Builder:\n" + suv);
    }
}
