package com.example.airport.controllers;

import com.example.airport.models.Airport;
import com.example.airport.models.Plane;
import com.example.airport.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for the planes repository
 * @author kgriffio
 */

@RestController
@RequestMapping("api/airport/planes")
public class PlaneController {

    @Autowired
    PlaneRepository planeRepository;


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
}
