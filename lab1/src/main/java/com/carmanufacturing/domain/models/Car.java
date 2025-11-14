package com.carmanufacturing.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Car {
    private final String id;
    private String modelName;
    private CarType carType;
    private Engine engine;
    private List<Wheel> wheels;
    private Paint paint;

    public Car() {
        this.id = UUID.randomUUID().toString();
        this.wheels = new ArrayList<>();
    }

    // prototype - copy
    public Car(Car other) {
        this.id = UUID.randomUUID().toString();
        this.modelName = other.modelName;
        this.carType = other.carType;
        this.engine = other.engine == null ? null : new Engine(other.engine);
        this.wheels = new ArrayList<>();
        for (Wheel w : other.wheels) {
            this.wheels.add(new Wheel(w));
        }
        this.paint = other.paint == null ? null : new Paint(other.paint);
    }

    public String getId() { return id; }
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    public CarType getCarType() { return carType; }
    public void setCarType(CarType carType) { this.carType = carType; }
    public Engine getEngine() { return engine; }
    public void setEngine(Engine engine) { this.engine = engine; }
    public List<Wheel> getWheels() { return wheels; }
    public void setWheels(List<Wheel> wheels) { this.wheels = wheels; }
    public Paint getPaint() { return paint; }
    public void setPaint(Paint paint) { this.paint = paint; }

    @Override
    public Car clone() {
        return new Car(this);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id.substring(0,8) +
                ", model='" + modelName + '\'' +
                ", type=" + carType +
                ", engine=" + engine +
                ", wheels=" + wheels +
                ", paint=" + paint +
                '}';
    }
}
