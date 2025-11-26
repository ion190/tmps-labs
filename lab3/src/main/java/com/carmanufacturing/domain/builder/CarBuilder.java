package com.carmanufacturing.domain.builder;

import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;
import com.carmanufacturing.domain.models.Paint;

import java.util.List;

public interface CarBuilder {
    CarBuilder withModel(String name);
    CarBuilder withEngine(Engine engine);
    CarBuilder withWheels(List<Wheel> wheels);
    CarBuilder withPaint(Paint paint);
    Car build();
}
