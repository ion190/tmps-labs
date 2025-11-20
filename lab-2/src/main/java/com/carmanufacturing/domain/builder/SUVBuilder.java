package com.carmanufacturing.domain.builder;

import com.carmanufacturing.domain.models.*;

import java.util.List;

public class SUVBuilder implements CarBuilder {
    private Car car;

    public SUVBuilder() {
        car = new Car();
    }

    @Override
    public CarBuilder withModel(String name) {
        car.setModelName(name);
        car.setCarType(CarType.SUV);
        return this;
    }

    @Override
    public CarBuilder withEngine(Engine engine) {
        engine.setHorsepower(Math.max(engine.getHorsepower(), 220));
        car.setEngine(engine);
        return this;
    }

    @Override
    public CarBuilder withWheels(List<Wheel> wheels) {
        car.setWheels(wheels);
        return this;
    }

    @Override
    public CarBuilder withPaint(Paint paint) {
        car.setPaint(paint);
        return this;
    }

    @Override
    public Car build() {
        return car;
    }
}
