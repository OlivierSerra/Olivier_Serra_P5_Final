package com.openclassroom.SafetyNetAlertsEndOfMission.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.FirestationService;


    @SpringBootTest
        public class FirestationServiceTest {
            private FirestationService firestationService; 

        @Test    
        public void testUpdateFirestation() {
            String address = "1509 Culver St";
            String station = "3";
            
            Firestation firestationToUpdate = new Firestation();
            firestationToUpdate.setAddress(address);
            firestationToUpdate.setStation(station);
            
            Firestation result = firestationService.update(address, station, firestationToUpdate);
            
            assertNotNull(result);
            
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
                }
                
        @Test    
        public void testDeleteFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation FirestationToDelete = new Firestation();
            FirestationToDelete.setAddress(address);
            FirestationToDelete.setStation(station);
    
            Firestation result = firestationService.delete(address, station);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

        @Test    
        public void testSaveFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation savedFirestation = new Firestation();
            savedFirestation.setAddress(address);
            savedFirestation.setStation(station);
    
            Firestation result = firestationService.save(savedFirestation);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

        @Test    
        public void testAddFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation addedFirestation = new Firestation();
            addedFirestation.setAddress(address);
            addedFirestation.setStation(station);
    
            Firestation result = firestationService.save(addedFirestation);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

        @Test    
        public void testGetFirestation() {
            
            String address = "1509 Culver St";
            String station = "3";
            Firestation getFirestation = new Firestation();
            getFirestation.setAddress(address);
            getFirestation.setStation(station);
    
            Firestation result = firestationService.save(getFirestation);
    
            assertNotNull(result);
    
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }
}

    