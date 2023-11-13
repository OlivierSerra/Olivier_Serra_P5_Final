package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import com.openclassroom.SafetyNetAlertsEndOfMission.repository.MedicalRecordRepository;

@SpringBootTest
public class MedicalRecordRepositoryTest {
    private MedicalRecordRepository medicalRecordRepository;

    /****************** test to getMedicalRecord     *************/
    @Test    
    public void testGetMedicalRecord() {
        
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord getMedicalRecord = new MedicalRecord();
        getMedicalRecord.setBirthdate("01/01/1990");
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        getMedicalRecord.setMedications(medications);
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");        
        getMedicalRecord.setAllergies(allergies);

        // Appelez la méthode updateMedicalRecord
        MedicalRecord result = medicalRecordRepository.medicalRecord(firstName, lastName);

        // Vérifiez si le dossier médical a été mis à jour correctement
        assertNotNull(result);

        // Vérifiez si les données du dossier médical sont correctes
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }



/******************** test to save   ***************/
    @Test    
    public void testSaveMedicalRecord() {
        
       
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord savedMedicalRecord = new MedicalRecord();
        savedMedicalRecord.setBirthdate("01/01/1990");
        
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        savedMedicalRecord.setMedications(medications);
        
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        savedMedicalRecord.setAllergies(allergies);
        
        // Appelez la méthode updateMedicalRecord
        MedicalRecord result = medicalRecordRepository.saveMedicalRecord(savedMedicalRecord);

        // Vérifiez si le dossier médical a été mis à jour correctement
        assertNotNull(result);

        // Vérifiez si les données du dossier médical sont correctes
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }

/************ test to delete    ***********/
    @Test    
    public void testDeleteMedicalRecord() {
        
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord deleteMedicalRecord = new MedicalRecord();
        deleteMedicalRecord.setBirthdate("01/01/1990");
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        deleteMedicalRecord.setMedications(medications);
        
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        deleteMedicalRecord.setAllergies(allergies);

        // Appelez la méthode updateMedicalRecord
        MedicalRecord result = medicalRecordRepository.deleteMedicalRecord(firstName, lastName);

        // Vérifiez si le dossier médical a été mis à jour correctement
        assertNotNull(result);

        // Vérifiez si les données du dossier médical sont correctes
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }
/**************** test to update   *********************/
    @Test    
    public void testUpdateMedicalRecord() {
        
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord updatedMedicalRecord = new MedicalRecord();
        updatedMedicalRecord.setBirthdate("01/01/1990");
        
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        updatedMedicalRecord.setMedications(medications);
        
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        updatedMedicalRecord.setAllergies(allergies);

        // Appelez la méthode updateMedicalRecord
        MedicalRecord result = medicalRecordRepository.updateMedicalRecord(firstName, lastName, updatedMedicalRecord);

        // Vérifiez si le dossier médical a été mis à jour correctement
        assertNotNull(result);

        // Vérifiez si les données du dossier médical sont correctes
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }
    
}

