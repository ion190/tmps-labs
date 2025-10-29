package com.carmanufacturing.domain.factory;

import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;
import com.carmanufacturing.domain.models.Paint;

import java.util.ArrayList;
import java.util.List;

// Abstract Factory
public class ElectricPartsFactory implements PartsFactory {

    @Override
    public Engine createEngine() {
        return new Engine(Engine.FuelType.ELECTRIC, 220);
    }

    @Override
    public List<Wheel> createWheels() {
        List<Wheel> wheels = new ArrayList<>();
        for (int i = 0; i < 4; i++) wheels.add(new Wheel(18, "winter"));
        return wheels;
    }

    @Override
    public Paint createPaint() {
        return new Paint("Pearl White");
    }
}
