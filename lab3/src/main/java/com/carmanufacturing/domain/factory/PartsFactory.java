package com.carmanufacturing.domain.factory;

import com.carmanufacturing.domain.models.*;

import java.util.List;

// Abstract Factory
public interface PartsFactory {
    Engine createEngine();
    List<Wheel> createWheels();
    Paint createPaint();
}
