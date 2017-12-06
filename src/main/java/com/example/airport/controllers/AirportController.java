package com.example.airport.controllers;

import com.example.airport.exceptions.NotFoundException;
import com.example.airport.models.Airport;
import com.example.airport.models.Plane;
import com.example.airport.repositories.AirportRepository;
import com.example.airport.repositories.PlaneRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for the airport repository
 * @author kgriffio
 */

@RestController
@RequestMapping("api/airport/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private PlaneRepository planeRepository;

    /**
     * Get all airports
     * @return All airports
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Airport> getAirports(){
        return airportRepository.findAll();
    }

    /**
     * Add an airport
     * @param airport airport to add
     * @return airport that was added.
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public Airport addAirport(@RequestBody Airport airport){
        if(airport == null){
            throw new NotFoundException();
        }
        return airportRepository.save(airport);
    }

    /**
     * Deletes an airport
     * @param airport airport to delete.
     */
    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public void deleteAirport(@RequestBody Airport airport){
        airportRepository.delete(airport);
    }


    /**
     * Adds a plane to the airport
     * @param airport The airport that the plane should be added to
     * @param plane The plane that should be added. Should exist.
     */
    @RequestMapping(value="addplane", method = RequestMethod.PUT)
    public void addplaneToAirport(@RequestBody Airport airport, @RequestBody Plane plane){
        if(airport == null || plane == null)
            throw new NotFoundException(); // not going into too much specifics for now.

        // Find airport
        Airport dbAirport = airportRepository.findOne(airport.getId());
        if(dbAirport == null) {
            throw new NotFoundException(); // not going into too much specifics for now.
        }

        // Find plane
        Plane dbPlane = planeRepository.findOne(plane.getId());
        if(dbPlane == null) {
            throw new NotFoundException(); // not going into too much specifics for now.
        }

        // We found them both! add plane to the airport.
        dbAirport.addPlane(dbPlane);
    }

}
