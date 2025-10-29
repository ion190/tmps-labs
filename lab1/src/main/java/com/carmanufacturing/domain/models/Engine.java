package com.carmanufacturing.domain.models;

public class Engine {
    public enum FuelType {GASOLINE, DIESEL, ELECTRIC}

    private FuelType fuelType;
    private int horsepower;

    public Engine(FuelType fuelType, int horsepower) {
        this.fuelType = fuelType;
        this.horsepower = horsepower;
    }

    // for cloning - prototype
    public Engine(Engine other) {
        this.fuelType = other.fuelType;
        this.horsepower = other.horsepower;
    }

    public FuelType getFuelType() { return fuelType; }
    public int getHorsepower() { return horsepower; }

    public void setHorsepower(int horsepower) { this.horsepower = horsepower; }
    public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }

    public void reset() {
        // Reset fields to default for pooling reuse
        this.horsepower = 100;
        this.fuelType = FuelType.GASOLINE;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "fuel=" + fuelType +
                ", hp=" + horsepower +
                '}';
    }
}
