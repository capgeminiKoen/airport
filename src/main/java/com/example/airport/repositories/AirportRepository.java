package com.example.airport.repositories;

import com.example.airport.models.Airport;
import com.example.airport.models.Plane;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirportRepository extends CrudRepository<Airport, Long> {
    public Airport findOneByPlanes(Plane plane);
}
