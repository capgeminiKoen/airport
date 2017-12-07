package com.example.airport.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Plane class. POJO
 * @author kgriffio
 */
@Entity
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private float gasLevel;
    @NotNull
    private float maxGasLevel;
    @NotNull
    private float speed;

    private boolean travelling;
    private Airport travellingTo;
    private Airport travelingFrom;

    public float getGasLevel() {
        return gasLevel;
    }

    public void setGasLevel(float gasLevel) {
        this.gasLevel = gasLevel;
    }

    public float getMaxGasLevel() {
        return maxGasLevel;
    }

    public void setMaxGasLevel(float maxGasLevel) {
        this.maxGasLevel = maxGasLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Airport getTravellingTo() {
        return travellingTo;
    }

    public void setTravellingTo(Airport travellingTo) {
        this.travellingTo = travellingTo;
    }

    public Airport getTravelingFrom() {
        return travelingFrom;
    }

    public void setTravelingFrom(Airport travelingFrom) {
        this.travelingFrom = travelingFrom;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
