package com.openclassroom.SafetyNetAlertsEndOfMission.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;
import com.openclassroom.SafetyNetAlertsEndOfMission.repository.FirestationRepository;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.FirestationService;


@ExtendWith(MockitoExtension.class)
public class FirestationControllerTest {

    @Mock
    private FirestationService firestationService;
    private FirestationRepository firestationRepository;

    @InjectMocks
    private FirestationControllerTest firestationControllerTest;

    private MockMvc mockMvc;

    @Test
    void testFirestations() throws Exception {
        //arrange
        Firestation firestation1 = new Firestation("10 Culver St","5");
        Firestation firestation2 = new Firestation("10 Steppes Pl", "3" );
        List<Firestation> firestations = Arrays.asList(firestation1, firestation2);

        //act
        when(firestationService.findAll()).thenReturn(firestations);

       
        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        //assert
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andReturn();
    }

     @Test
    void testAddFirestation() throws Exception {
        //aarange
        Firestation firestation = new Firestation("10 Culver St","5");

        //act 
        when(firestationRepository.saveFirestation(any(Firestation.class))).thenReturn(firestation);

        
        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();
        //assert
        mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(firestation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("10 Culver St"))
                .andExpect(jsonPath("$.station").value("5"))
                .andReturn();
    }

    @Test
    void testDeleteFirestation() throws Exception {
        //arrange
        Firestation firestationToDelete = new Firestation("1509 Culver St", "5");

        //act
        when(firestationRepository.deleteFirestation("1509 Culver St", "5")).thenReturn(firestationToDelete);

        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        String jsonInput = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";

        //assert
        mockMvc.perform(delete("/firestation/{address}/{station}", "1509 Culver St", "5")
                .contentType("application/json")
                .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("10 Culver St"))
                .andExpect(jsonPath("$.station").value("5"))
                .andReturn();
    }

    @Test
    void testUpdateFirestation() throws Exception {
        //arrange
        Firestation firestationToUpdate = new Firestation("1509 Culver St", "5");
        Firestation updatedFirestation = new Firestation("1510 Culver St", "5");

        //act
        when(firestationRepository.updateFirestation("1509 Culver St", "5", firestationToUpdate)).thenReturn(updatedFirestation);

        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        
        String jsonInput = new ObjectMapper().writeValueAsString(firestationToUpdate);

        //assert
        mockMvc.perform(put("/firestation/{address}/{station}", "1510 Culver St", "5")
                .contentType("application/json")
                .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("10 Culver St"))
                .andExpect(jsonPath("$.station").value("5"))
                .andReturn();
    }
}