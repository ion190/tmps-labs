package com.carmanufacturing.domain.builder;

import com.carmanufacturing.domain.models.*;

import java.util.List;

public class CarDirector {
    public Car constructSportsCar(CarBuilder builder, String modelName, Engine engine, List<Wheel> wheels) {
        return builder.withModel(modelName)
                .withEngine(engine)
                .withWheels(wheels)
                .withPaint(new Paint("Red"))
                .build();
    }

    public Car constructSUV(CarBuilder builder, String modelName, Engine engine, List<Wheel> wheels) {
        return builder.withModel(modelName)
                .withEngine(engine)
                .withWheels(wheels)
                .withPaint(new Paint("Green"))
                .build();
    }
}
