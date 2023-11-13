package com.openclassroom.SafetyNetAlertsEndOfMission.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.MedicalRecordService;


@ExtendWith(MockitoExtension.class)
public class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordControllerTest medicalRecordControllerTest;

    private MockMvc mockMvc;

    @Test
    void testMedicalRecords() throws Exception {
        // Données de test
        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
        List<MedicalRecord> medicalRecords = Arrays.asList(medicalRecord1);

        // Définir le comportement du service mock
        when(medicalRecordService.findAll()).thenReturn(medicalRecords);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();

        // Effectuer la requête HTTP GET
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].birthdate").value("03/06/1984"))
                .andExpect(jsonPath("$[0].medications[0]").value("aznol:350mg"))
                .andExpect(jsonPath("$[0].medications[1]").value("hydrapermazol:100mg"))
                .andExpect(jsonPath("$[0].allergies[0]").value("nillacilan"))
                .andReturn();
    }

    
    @Test
    void testAddMedicalRecord() throws Exception {
        // Données de test
        MedicalRecord medicalRecordToAdd = new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
        MedicalRecord savedMedicalRecord = new MedicalRecord("John", "Boyd", "08/07/1985", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));

        // Définir le comportement du service mock
        when(medicalRecordService.save(medicalRecordToAdd)).thenReturn(savedMedicalRecord);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();

        // Créer le JSON à envoyer dans la requête
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(medicalRecordToAdd);

        // Effectuer la requête HTTP POST avec le JSON en tant que corps de la requête
        mockMvc.perform(post("/medicalRecord")
                .contentType("application/json")
                .content(jsonInput))
                .andExpect(status().isOk())
                // Ajouter d'autres assertions selon les besoins
                // Vous pouvez vérifier le contenu de la réponse JSON, l'objet ajouté, etc.
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.birthdate").value("03/06/1984"))
                .andExpect(jsonPath("$.medications[0]").value("aznol:350mg"))
                .andExpect(jsonPath("$.medications[1]").value("hydrapermazol:100mg"))
                .andExpect(jsonPath("$.allergies[0]").value("nillacilan"))
                .andReturn();
    }

    @Test
    void testDeleteMedicalRecord() throws Exception {
        // Données de test
        
        MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "03/06/1984",
                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));

        // Définir le comportement du service mock
        when(medicalRecordService.delete("John", "Boyd")).thenReturn(medicalRecordToDelete);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();

        // Effectuer la requête HTTP DELETE sans corps de requête (utilisez simplement l'URL)
        mockMvc.perform(delete("/medicalRecord/{firstName}/{lastName}", "John", "Boyd")
                .contentType("application/json"))
                .andExpect(status().isOk())
                // Ajouter d'autres assertions selon les besoins
                // Vous pouvez vérifier le contenu de la réponse JSON, l'objet supprimé, etc.
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.birthdate").value("03/06/1984"))
                .andExpect(jsonPath("$.medications[0]").value("aznol:350mg"))
                .andExpect(jsonPath("$.medications[1]").value("hydrapermazol:100mg"))
                .andExpect(jsonPath("$.allergies[0]").value("nillacilan"))
                .andReturn();
    }

    @Test
    void testUpdateMedicalRecord() throws Exception {
        // Données de test
        MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Boyd", "03/06/1984",
                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
        MedicalRecord updatedMedicalRecord = new MedicalRecord(/* initialiser avec les valeurs mises à jour */);

        // Définir le comportement du service mock
        when(medicalRecordService.update("John", "Boyd", medicalRecordToUpdate)).thenReturn(updatedMedicalRecord);

        // Initialiser le MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();
        // Utiliser ObjectMapper pour sérialiser l'objet MedicalRecord en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(medicalRecordToUpdate);

        // Effectuer la requête HTTP PUT avec le JSON en tant que corps de la requête
        mockMvc.perform(put("/medicalRecord/{firstName}/{lastName}", "John", "Boyd")
        .contentType("application/json")
        .content(jsonInput))
        .andExpect(status().isOk())
        // Ajouter d'autres assertions selon les besoins
        // Vous pouvez vérifier le contenu de la réponse JSON, l'objet mis à jour, etc.
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Boyd"))
        .andExpect(jsonPath("$.birthdate").value("03/06/1984"))
        .andExpect(jsonPath("$.medications[0]").value("aznol:350mg"))
        .andExpect(jsonPath("$.medications[1]").value("hydrapermazol:100mg"))
        .andExpect(jsonPath("$.allergies[0]").value("nillacilan"))
        .andReturn();
}
}