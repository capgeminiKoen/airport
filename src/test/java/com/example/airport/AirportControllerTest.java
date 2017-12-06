package com.example.airport;

import com.example.airport.controllers.AirportController;
import com.example.airport.models.Airport;
import com.example.airport.repositories.AirportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AirportControllerTest {

    @InjectMocks
    private AirportController airportController;

    @Mock
    private AirportRepository airportRepository;

    private MockMvc mockMvc;

    private Airport airport;
    private List<Airport> airports;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
        init();
    }


    public void init(){
        airport = new Airport();
        airport.setCity("Rotterdam");
        airport.setName("Rotterdam airport");
        airport.setId(1);

        airports = new ArrayList<>();
        airports.add(airport);
    }

    /**
     * Get All planes test.
     * @throws Exception
     */
    @Test
    public void getAirportsAPITest() throws Exception{

        when(airportRepository.findAll()).thenReturn(airports);

        // Perform get
        this.mockMvc.perform(get("/api/airport/airports/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is((int)airport.getId())))
                .andExpect(jsonPath("$.[0].city", is(airport.getCity())))
                .andExpect(jsonPath("$.[0].name", is(airport.getName())))
                .andExpect(status().isOk());
    }


    /**
     * Add plane test.
     * @throws Exception
     */
    @Test
    public void addAirportAPITest() throws Exception{

        // Make json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(airport);

        when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airport);

        // Perform post
        this.mockMvc.perform(post("/api/airport/airports/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is((int)airport.getId())))
                .andExpect(jsonPath("$.city", is(airport.getCity())))
                .andExpect(jsonPath("$.name", is(airport.getName())))
                .andExpect(status().isOk());

    }
}
