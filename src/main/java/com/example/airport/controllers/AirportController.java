package com.example.airport.controllers;

import com.example.airport.models.Airport;
import com.example.airport.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/airport/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

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
        return airportRepository.save(airport);
    }
}
