package com.carmanufacturing.domain.models;

public class Paint {
    private final String color;

    public Paint(String color) {
        this.color = color;
    }

    public Paint(Paint other) {
        this.color = other.color;
    }

    public String getColor() { return color; }

    @Override
    public String toString() {
        return "Paint{" + color + "}";
    }
}
