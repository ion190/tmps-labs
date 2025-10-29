package com.carmanufacturing.domain.singleton;

import com.carmanufacturing.domain.factory.ElectricCarFactory;
import com.carmanufacturing.domain.factory.ElectricPartsFactory;
import com.carmanufacturing.domain.factory.GasCarFactory;
import com.carmanufacturing.domain.factory.GasPartsFactory;
import com.carmanufacturing.domain.factory.CarFactory;
import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.domain.prototype.CarPrototypeRegistry;

// Singleton that manages factories, prototype registry and engine pool
public class FactoryManager {
    private static volatile FactoryManager instance;

    private final CarPrototypeRegistry prototypeRegistry = new CarPrototypeRegistry();
    private final CarFactory gasCarFactory;
    private final CarFactory electricCarFactory;

    private FactoryManager() {
        // initialize resources
        this.gasCarFactory = new GasCarFactory(new GasPartsFactory(Engine.FuelType.GASOLINE));
        this.electricCarFactory = new ElectricCarFactory(new ElectricPartsFactory());

        // add a sample prototype
        Car sportProto = gasCarFactory.createCar("SportBase");
        prototypeRegistry.addPrototype("SportBase", sportProto);
    }

    public static FactoryManager getInstance() {
        if (instance == null) {
            synchronized (FactoryManager.class) {
                if (instance == null) instance = new FactoryManager();
            }
        }
        return instance;
    }

    public CarFactory getGasCarFactory() { return gasCarFactory; }
    public CarFactory getElectricCarFactory() { return electricCarFactory; }
    public CarPrototypeRegistry getPrototypeRegistry() { return prototypeRegistry; }
}
