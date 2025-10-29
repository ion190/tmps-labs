package com.carmanufacturing.domain.factory;

import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;
import com.carmanufacturing.domain.models.Paint;

import java.util.ArrayList;
import java.util.List;

// Abstract Factory
public class GasPartsFactory implements PartsFactory {
    private final Engine.FuelType fuel;

    public GasPartsFactory(Engine.FuelType fuel) {
        this.fuel = fuel;
    }

    @Override
    public Engine createEngine() {
        return new Engine(fuel, 126);
    }

    @Override
    public List<Wheel> createWheels() {
        List<Wheel> wheels = new ArrayList<>();
        for (int i = 0; i < 4; i++) wheels.add(new Wheel(17, "all-season"));
        return wheels;
    }

    @Override
    public Paint createPaint() {
        return new Paint("Metallic Silver");
    }
}
