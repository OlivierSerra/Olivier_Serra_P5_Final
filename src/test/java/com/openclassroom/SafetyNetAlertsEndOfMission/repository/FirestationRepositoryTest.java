package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;
import com.openclassroom.SafetyNetAlertsEndOfMission.repository.FirestationRepository;


    @SpringBootTest
        public class FirestationRepositoryTest {
            private FirestationRepository firestationRepository; 

/*************** test pour accéder à une firestation    *****/
        @Test    
        public void testGetFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation getFirestation = new Firestation();
            getFirestation.setAddress(address);
            getFirestation.setStation(station);
    
            Firestation result = firestationRepository.saveFirestation(getFirestation);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

/******************** test pour save  ********************/

        @Test    
        public void testSaveFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation savedFirestation = new Firestation();
            savedFirestation.setAddress(address);
            savedFirestation.setStation(station);
    
            Firestation result = firestationRepository.saveFirestation(savedFirestation);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

/******************** test pour delete  *****************/                
        @Test    
        public void testDeleteFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation FirestationToDelete = new Firestation();
            FirestationToDelete.setAddress(address);
            FirestationToDelete.setStation(station);
    
            Firestation result = firestationRepository.deleteFirestation(address, station);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

/********************* test pour update ********************/
        @Test    
        public void testUpdateFirestation() {
            String address = "1509 Culver St";
            String station = "3";
            
            Firestation firestationToUpdate = new Firestation();
            firestationToUpdate.setAddress(address);
            firestationToUpdate.setStation(station);
            
            Firestation result = firestationRepository.updateFirestation(address, station, firestationToUpdate);
            
            assertNotNull(result);
            
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
                }

}

    