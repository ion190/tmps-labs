package com.carmanufacturing.structural.adapter;

import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;

import java.util.List;

public interface PartsInventory {
    Engine fetchEngine(String spec);
    List<Wheel> fetchWheels(String spec);
}
