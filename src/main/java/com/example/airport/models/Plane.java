package com.example.airport.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
