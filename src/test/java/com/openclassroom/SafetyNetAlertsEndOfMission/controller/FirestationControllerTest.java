package com.openclassroom.SafetyNetAlertsEndOfMission.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        // Créer des données de test
        Firestation firestation1 = new Firestation("10 Culver St","5");
        Firestation firestation2 = new Firestation("10 Steppes Pl", "3" );
        List<Firestation> firestations = Arrays.asList(firestation1, firestation2);

        // Définir le comportement du service mock
        when(firestationService.findAll()).thenReturn(firestations);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        // Effectuer la requête HTTP GET
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                // Ajouter d'autres assertions selon les besoins
                // Vous pouvez vérifier le contenu de la réponse JSON, le nombre d'éléments, etc.
                .andReturn();
    }

     @Test
    void testAddFirestation() throws Exception {

        Firestation firestation = new Firestation("10 Culver St","5");

        // Définir le comportement du service mock
        when(firestationRepository.saveFirestation(any(Firestation.class))).thenReturn(firestation);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(firestation)))
                .andExpect(status().isOk())
                // Ajouter d'autres assertions selon les besoins
                // Vous pouvez vérifier le contenu de la réponse JSON, l'identifiant généré, etc.
                .andExpect(jsonPath("$.address").value("10 Culver St"))
                .andExpect(jsonPath("$.station").value("5"))
                .andReturn();
    }

    @Test
    void testDeleteFirestation() throws Exception {

        Firestation firestationToDelete = new Firestation("1509 Culver St", "5");

        // Définir le comportement du service mock
        when(firestationRepository.deleteFirestation("1509 Culver St", "5")).thenReturn(firestationToDelete);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        // Créer le JSON à envoyer dans la requête
        String jsonInput = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";

        // Effectuer la requête HTTP DELETE avec le JSON en tant que corps de la requête
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
        // Données de test
        Firestation firestationToUpdate = new Firestation("1509 Culver St", "5");
        Firestation updatedFirestation = new Firestation("1510 Culver St", "5");

        // Définir le comportement du service mock
        when(firestationRepository.updateFirestation("1509 Culver St", "5", firestationToUpdate)).thenReturn(updatedFirestation);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(firestationControllerTest).build();

        // Créer le JSON à envoyer dans la requête
        String jsonInput = new ObjectMapper().writeValueAsString(firestationToUpdate);

        // Effectuer la requête HTTP PUT avec le JSON en tant que corps de la requête
        mockMvc.perform(put("/firestation/{address}/{station}", "1510 Culver St", "5")
                .contentType("application/json")
                .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("10 Culver St"))
                .andExpect(jsonPath("$.station").value("5"))
                .andReturn();
    }
}