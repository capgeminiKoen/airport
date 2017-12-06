package com.example.airport.controllers;

import com.example.airport.exceptions.NoFuelException;
import com.example.airport.exceptions.NotFoundException;
import com.example.airport.models.Airport;
import com.example.airport.models.AirportPlanePair;
import com.example.airport.models.Plane;
import com.example.airport.repositories.AirportRepository;
import com.example.airport.repositories.PlaneRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
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
     * @param airportPlanePair Object that holds both an airplane and an airport.
     */
    @RequestMapping(value="addPlane", method = RequestMethod.POST)
    public void addplaneToAirport(@RequestBody AirportPlanePair airportPlanePair){
        // Get airport and plane
        Airport airport = airportPlanePair.getAirport();
        Plane plane = airportPlanePair.getPlane();

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
        // Save them in the repo.
        airportRepository.save(dbAirport);
    }

    /**
     * Move a plane from one location to another
     * @param planeID
     * @param airportTargetID
     */
    @RequestMapping(value = "movePlane/{planeID}/{airportTargetID}", method = RequestMethod.PUT)
    public void movePlane(@PathVariable long planeID, @PathVariable long airportTargetID){
        Plane plane = planeRepository.findOne(planeID);
        if(plane == null) {
            throw new NotFoundException();
        }

        Airport airportStart = airportRepository.findOneByPlanes(plane);
        Airport airportEnd = airportRepository.findOne(airportTargetID);
        if(airportStart == null || airportEnd == null || airportEnd.getId() == airportStart.getId()){
            throw new NotFoundException();
        }

        int gasAmount = 2;

        // Check whether the plane has enough gas
        if(plane.getGasLevel() < 2){
            throw new NoFuelException();
        }

        airportStart.deletePlane(plane);
        // Save airport start
        airportRepository.save(airportStart);

        airportEnd.addPlane(plane);
        // Save airport end
        airportRepository.save(airportEnd);

        // Costs 2 gas for now.
        plane.setGasLevel(plane.getGasLevel() - gasAmount);
        planeRepository.save(plane);
    }

    /**
     * Delete functionality
     * @param id id of airport to remove
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteAircraft(@PathVariable long id){
        airportRepository.delete(id);
    }

}
