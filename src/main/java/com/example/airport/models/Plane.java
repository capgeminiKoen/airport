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
    @ManyToOne
    private Airport travellingTo;
    @ManyToOne
    private Airport travellingFrom;
    private float currentTravelDistance;
    private float currentJourneyProgress;

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

    public Airport getTravellingFrom() {
        return travellingFrom;
    }

    public void setTravellingFrom(Airport travellingFrom) {
        this.travellingFrom = travellingFrom;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isTravelling() {
        return travelling;
    }

    public void setTravelling(boolean travelling) {
        this.travelling = travelling;
    }

    public float getCurrentTravelDistance() {
        return currentTravelDistance;
    }

    public void setCurrentTravelDistance(float currentTravelDistance) {
        this.currentTravelDistance = currentTravelDistance;
    }

    public float getCurrentJourneyProgress() {
        return currentJourneyProgress;
    }

    public void setCurrentJourneyProgress(float currentJourneyProgress) {
        this.currentJourneyProgress = currentJourneyProgress;
    }

    public void startTravelling(Airport from, Airport to){
        int distance = from.distance(to);
        travellingFrom = from;
        travellingTo = to;
        currentTravelDistance = distance;
        currentJourneyProgress = 0.0f;
        travelling = true;
        from.deletePlane(this);
    }

    /**
     *
     * @return arrival airport upon arrival
     */
    public Airport updateJourney(){
        // Increment current progress
        currentJourneyProgress += speed;
        if(currentJourneyProgress > currentTravelDistance){
            travelling = false;
            Airport temp_travellingTo = travellingTo;
            travellingTo = null;
            travellingFrom = null;
            currentJourneyProgress = 0;
            return temp_travellingTo;
        }
        return null;
    }
}
