package com.carmanufacturing.domain.prototype;

import com.carmanufacturing.domain.models.Car;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Prototype registry: store named prototypes and clone them on request
public class CarPrototypeRegistry {
    private final Map<String, Car> prototypes = new ConcurrentHashMap<>();

    public void addPrototype(String name, Car car) {
        prototypes.put(name, car);
    }

    public Car getPrototype(String name) {
        Car p = prototypes.get(name);
        if (p == null) return null;
        return p.clone();
    }
}
