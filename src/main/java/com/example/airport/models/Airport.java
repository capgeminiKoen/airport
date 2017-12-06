package com.example.airport.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author kgriffio
 * Airport class. Contains Planes and stuff.
 */
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    Plane planes;
}
