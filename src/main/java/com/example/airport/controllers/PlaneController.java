package com.example.airport.controllers;

import com.example.airport.exceptions.NotFoundException;
import com.example.airport.models.Airport;
import com.example.airport.models.Plane;
import com.example.airport.repositories.AirportRepository;
import com.example.airport.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for the planes repository
 * @author kgriffio
 */

@RestController
@RequestMapping("api/airport/planes")
public class PlaneController {

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    AirportRepository airportRepository;

    /**
     * Get all planes
     * @return All planes in the repo
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Iterable<Plane> getPlanes(){
        return planeRepository.findAll();
    }

    /**
     * Add a plane to the repo
     * @param plane Plane to add
     * @return Plane that was added.
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public Plane addPlane(@RequestBody Plane plane){
        return planeRepository.save(plane);
    }

    /**
     * Deletes a plane
     * @param plane plane to delete.
     */
    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public void deletePlane(@RequestBody Plane plane){
        planeRepository.delete(plane);
    }

    /**
     * Add gas to certain plane
     * @param id id of the plane in this case
     */
    @RequestMapping(value="addGas/{id}", method = RequestMethod.PUT)
    public void addGasToPlane(@PathVariable long id){
        // Find plane
        Plane dbPlane = planeRepository.findOne(id);
        if(dbPlane == null) {
            throw new NotFoundException(); // not going into too much specifics for now.
        }
        dbPlane.setGasLevel(dbPlane.getMaxGasLevel());
        planeRepository.save(dbPlane);
    }

    /**
     * Delete functionality
     * @param id id of plane to remove
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePlane(@PathVariable long id){
        // First remove plane from airfield
        Plane plane = planeRepository.findOne(id);
        if(plane == null){
            throw new NotFoundException();
        }
        Airport airport = airportRepository.findOneByPlanes(plane);
        if(airport == null){
            throw new NotFoundException();
        }
        // Delete plane from airport
        airport.deletePlane(plane);
        // Delete plane from repo
        planeRepository.delete(id);
    }
}
