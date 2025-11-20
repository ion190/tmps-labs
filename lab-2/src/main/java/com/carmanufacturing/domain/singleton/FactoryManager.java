package com.carmanufacturing.domain.singleton;

import com.carmanufacturing.domain.factory.ElectricCarFactory;
import com.carmanufacturing.domain.factory.ElectricPartsFactory;
import com.carmanufacturing.domain.factory.GasCarFactory;
import com.carmanufacturing.domain.factory.GasPartsFactory;
import com.carmanufacturing.domain.factory.CarFactory;
import com.carmanufacturing.domain.models.Engine;

// Singleton that manages factories, prototype registry
public class FactoryManager {
    private static volatile FactoryManager instance;

    private final CarFactory gasCarFactory;
    private final CarFactory electricCarFactory;

    private FactoryManager() {
        // initialize resources
        this.gasCarFactory = new GasCarFactory(new GasPartsFactory(Engine.FuelType.GASOLINE));
        this.electricCarFactory = new ElectricCarFactory(new ElectricPartsFactory());
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
}
