package com.example.airport.repositories;

import com.example.airport.models.Plane;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaneRepository extends CrudRepository<Plane, Long> {
    List<Plane> findByTravelling(boolean travelling);
}
