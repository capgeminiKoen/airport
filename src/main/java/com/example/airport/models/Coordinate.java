package com.example.airport.models;

import javax.persistence.Entity;

/**
 * Simple coordinate class.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(){

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Coordinate subtract(Coordinate coordinate){
        return new Coordinate(this.x - coordinate.x, this.y - coordinate.y);
    }

    private Coordinate square(){
        x = x * x;
        y = y * y;
        return this;
    }

    // returns the distance between two coordinates.
    public int distance(Coordinate destination){
        Coordinate squaredSum = subtract(destination).square();
        return (int)Math.sqrt(squaredSum.x + squaredSum.y);
    }
}

