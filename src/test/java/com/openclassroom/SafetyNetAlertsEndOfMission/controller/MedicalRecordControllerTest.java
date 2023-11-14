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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
        // arrange
        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
        List<MedicalRecord> medicalRecords = Arrays.asList(medicalRecord1);

        //act
        when(medicalRecordService.findAll()).thenReturn(medicalRecords);

        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();

        //assert
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
        // arrange
        MedicalRecord medicalRecordToAdd = new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
        MedicalRecord savedMedicalRecord = new MedicalRecord("John", "Boyd", "08/07/1985", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));

        //act
        when(medicalRecordService.save(medicalRecordToAdd)).thenReturn(savedMedicalRecord);

        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(medicalRecordToAdd);

        //assert
        mockMvc.perform(post("/medicalRecord")
                .contentType("application/json")
                .content(jsonInput))
                .andExpect(status().isOk())
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
        // arrange
        MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "03/06/1984",
                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));

        //act
        when(medicalRecordService.delete("John", "Boyd")).thenReturn(medicalRecordToDelete);

        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();

        //assert
        mockMvc.perform(delete("/medicalRecord/{firstName}/{lastName}", "John", "Boyd")
                .contentType("application/json"))
                .andExpect(status().isOk())
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
        //arrange
        MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Boyd", "03/06/1984",
                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));
        MedicalRecord updatedMedicalRecord = new MedicalRecord("John", "Boyd", "21/10/1975",
                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"));

        //act
        when(medicalRecordService.update("John", "Boyd", medicalRecordToUpdate)).thenReturn(updatedMedicalRecord);

        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordControllerTest).build();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = objectMapper.writeValueAsString(medicalRecordToUpdate);

        //assert 
        mockMvc.perform(put("/medicalRecord/{firstName}/{lastName}", "John", "Boyd")
        .contentType("application/json")
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Boyd"))
        .andExpect(jsonPath("$.birthdate").value("03/06/1984"))
        .andExpect(jsonPath("$.medications[0]").value("aznol:350mg"))
        .andExpect(jsonPath("$.medications[1]").value("hydrapermazol:100mg"))
        .andExpect(jsonPath("$.allergies[0]").value("nillacilan"))
        .andReturn();
}
}