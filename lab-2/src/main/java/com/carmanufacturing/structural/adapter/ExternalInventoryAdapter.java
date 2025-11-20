package com.carmanufacturing.structural.adapter;

import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;

import java.util.List;

// adapter that maps the ExternalInventory API to our PartsInventory interface.
public class ExternalInventoryAdapter implements PartsInventory {
    private final ExternalInventory ext;

    public ExternalInventoryAdapter(ExternalInventory ext) {
        this.ext = ext;
    }

    @Override
    public Engine fetchEngine(String spec) {
        // map simple spec strings to external method
        if (spec == null) return ext.getMotor("gasoline");
        if (spec.toLowerCase().contains("electric")) return ext.getMotor("electric");
        if (spec.toLowerCase().contains("diesel")) return ext.getMotor("diesel");
        return ext.getMotor("gasoline");
    }

    @Override
    public List<Wheel> fetchWheels(String spec) {
        if (spec == null || spec.trim().isEmpty()) {
            return ext.retrieveWheelSet(17, "all-season");
        }
        String[] parts = spec.split(",");
        int size = Integer.parseInt(parts[0].trim());
        String kind = parts.length > 1 ? parts[1].trim() : "all-season";
        return ext.retrieveWheelSet(size, kind);
    }
}
