package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;

@SpringBootTest
public class MedicalRecordRepositoryTest {
    private MedicalRecordRepository medicalRecordRepository;

    /****************** test to getMedicalRecord     *************/
    @Test    
    public void testGetMedicalRecord() {
        //arrange
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord getMedicalRecord = new MedicalRecord();
        getMedicalRecord.setBirthdate("01/01/1990");
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        getMedicalRecord.setMedications(medications);
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");        
        getMedicalRecord.setAllergies(allergies);

        //act
        MedicalRecord result = medicalRecordRepository.medicalRecord(firstName, lastName);

        // assert
        assertNotNull(result);
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }



/******************** test to save   ***************/
    @Test    
    public void testSaveMedicalRecord() {
        //arrange
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord savedMedicalRecord = new MedicalRecord();
        savedMedicalRecord.setBirthdate("01/01/1990");
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        savedMedicalRecord.setMedications(medications);
        
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        savedMedicalRecord.setAllergies(allergies);
        
        //act 
        MedicalRecord result = medicalRecordRepository.saveMedicalRecord(savedMedicalRecord);

        //assert
        assertNotNull(result);
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }

/************ test to delete    ***********/
    @Test    
    public void testDeleteMedicalRecord() {
        //arrange
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord deleteMedicalRecord = new MedicalRecord();
        deleteMedicalRecord.setBirthdate("01/01/1990");
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        deleteMedicalRecord.setMedications(medications);
        
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        deleteMedicalRecord.setAllergies(allergies);

        // act 
        MedicalRecord result = medicalRecordRepository.deleteMedicalRecord(firstName, lastName);

        //assert 
        assertNotNull(result);
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }
/**************** test to update   *********************/
    @Test    
    public void testUpdateMedicalRecord() {
        //arrange
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord updatedMedicalRecord = new MedicalRecord();
        updatedMedicalRecord.setBirthdate("01/01/1990");
        
        List<String> medications = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        updatedMedicalRecord.setMedications(medications);
        
        List<String> allergies = Arrays.asList("aznol:350mg", "hydrapermazol:100mg");
        updatedMedicalRecord.setAllergies(allergies);

        //act 
        MedicalRecord result = medicalRecordRepository.updateMedicalRecord(firstName, lastName, updatedMedicalRecord);

        //assert
        assertNotNull(result);
        assertEquals("01/01/1990", result.getBirthdate());
        assertEquals("aznol:350mg, hydrapermazol:100mg", result.getMedications());
        assertEquals("nillacilan", result.getAllergies());
    }
    
}

