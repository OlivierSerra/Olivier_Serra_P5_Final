package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;

    @SpringBootTest
        public class FirestationRepositoryTest {
            private FirestationRepository firestationRepository; 

/*************** test pour accéder à une firestation    *****/
        @Test    
        public void testGetFirestation() {
            //arrange
            String address = "1509 Culver St";
            String station = "3";
            Firestation getFirestation = new Firestation();
            getFirestation.setAddress(address);
            getFirestation.setStation(station);
            //act
            Firestation result = firestationRepository.saveFirestation(getFirestation);
            //assert
            assertNotNull(result);
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

/******************** test pour save  ********************/

        @Test    
        public void testSaveFirestation() {
            //arrange
            String address = "1509 Culver St";
            String station = "3";
            Firestation savedFirestation = new Firestation();
            savedFirestation.setAddress(address);
            savedFirestation.setStation(station);
            //act
            Firestation result = firestationRepository.saveFirestation(savedFirestation);
            //assert
            assertNotNull(result);
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

/******************** test pour delete  *****************/                
        @Test    
        public void testDeleteFirestation() {
            //arrange
            String address = "1509 Culver St";
            String station = "3";
            Firestation FirestationToDelete = new Firestation();
            FirestationToDelete.setAddress(address);
            FirestationToDelete.setStation(station);
            //act
            Firestation result = firestationRepository.deleteFirestation(address, station);
            //assert
            assertNotNull(result);
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
        }

/********************* test pour update ********************/
        @Test    
        public void testUpdateFirestation() {
            //arrange
            String address = "1509 Culver St";
            String station = "3";
            Firestation firestationToUpdate = new Firestation();
            firestationToUpdate.setAddress(address);
            firestationToUpdate.setStation(station);
            //act
            Firestation result = firestationRepository.updateFirestation(address, station, firestationToUpdate);
            //assert
            assertNotNull(result);
            assertEquals("1509 Culver St", result.getAddress());
            assertEquals("3", result.getStation());
                }

}

    