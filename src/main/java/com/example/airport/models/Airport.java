package com.example.airport.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kgriffio
 * Airport class. Contains Planes and stuff.
 */
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    List<Plane> planes;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private int xCoordinate;
    @NotNull
    private int yCoordinate;

    /**
     * Add a plane to the list.
     * @param plane
     */
    public void addPlane(Plane plane){
        if(plane != null){
            planes.add(plane);
        }
    }

    public boolean containsPlane(Plane plane){
        return planes.contains(plane);
    }

    /**
     * Remove a plane from the list
     * @param plane
     */
    public void deletePlane(Plane plane){
        if(plane != null){
            planes.remove(plane);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Calculates distance between two airports
     * @return distance between two airports
     */
    public int distance(Airport airport){
        Coordinate end = new Coordinate(airport.xCoordinate, airport.yCoordinate);
        Coordinate start = new Coordinate(this.xCoordinate, this.yCoordinate);
        return start.distance(end);
    }
}
