package com.carmanufacturing.domain.models;

public class Wheel {
    private final int size;
    private final String type;

    public Wheel(int size, String type) {
        this.size = size;
        this.type = type;
    }

    // copy
    public Wheel(Wheel other) {
        this.size = other.size;
        this.type = other.type;
    }

    public int getSize() { return size; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return "Wheel{" + size + "in," + type + "}";
    }
}
