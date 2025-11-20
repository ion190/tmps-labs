package com.carmanufacturing.structural.adapter;

import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;

import java.util.ArrayList;
import java.util.List;

// external third-party inventory API with different method signatures.
public class ExternalInventory {
    public Engine getMotor(String engineType) {
        if ("electric".equalsIgnoreCase(engineType)) {
            return new Engine(Engine.FuelType.ELECTRIC, 200);
        } else if ("diesel".equalsIgnoreCase(engineType)) {
            return new Engine(Engine.FuelType.DIESEL, 160);
        }
        return new Engine(Engine.FuelType.GASOLINE, 150);
    }

    public List<Wheel> retrieveWheelSet(int size, String kind) {
        List<Wheel> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new Wheel(size, kind));
        }
        return list;
    }
}
