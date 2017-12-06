package com.example.airport.repositories;

import com.example.airport.models.Plane;
import org.springframework.data.repository.CrudRepository;

public interface PlaneRepository extends CrudRepository<Plane, Long> {
}
