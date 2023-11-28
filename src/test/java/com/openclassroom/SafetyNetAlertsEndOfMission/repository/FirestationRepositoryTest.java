package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;


@SpringBootTest
public class FirestationRepositoryTest {

    private FirestationRepository firestationRepository; 
    private JsonReader dataReaderMock;

    @Autowired
    public FirestationRepositoryTest(JsonReader dataReaderMock) throws Exception {
        this.dataReaderMock = dataReaderMock; 
        this.firestationRepository = new FirestationRepository(dataReaderMock);
    }

    /*************** test pour accéder à une firestation    *****/
    @Test
    public void testGetFirestation() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
        Firestation firestationToFind = new Firestation(
            "29 15th St", 
            "9");
        List<Firestation> firestationList = Arrays.asList(firestationToFind);
        //act
        when(dataReaderMock.getFirestationsData()).thenReturn(firestationList);
        FirestationRepository firestationRepository = new FirestationRepository(dataReaderMock); 
        List<Firestation> result = firestationRepository.getFirestations();
        //assert
        assertEquals(1, result.size());
        assertEquals(firestationToFind, result.get(0));
    }

/******************** test pour save  ********************/

    @Test
    public void testSaveFirestation() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
        Firestation firestationToSave = new Firestation(
            "29 15th St", 
            "9");
        //act
        FirestationRepository firestationRepository = new FirestationRepository(dataReaderMock);
        firestationRepository.saveFirestation(firestationToSave);
        //assert 
        List<Firestation> result = firestationRepository.getFirestations();
        assertEquals(1, result.size());
        assertEquals(firestationToSave, result.get(0));
    }

/******************** test pour delete  *****************/                
    
    @Test
    public void testDeleteFirestation() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
        Firestation firestationToSave = new Firestation(
            "29 15th St", 
            "9");
        //act
        FirestationRepository firestationRepository = new FirestationRepository(dataReaderMock);
        firestationRepository.deleteFirestation("29 15th St", "9");
        //assert 
        List<Firestation> result = firestationRepository.getFirestations();
        assertEquals(0, result.size());
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

     @Test
    public void testUpdateFirestationBis() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
         String address = "29 15th St";
        String station = "9";
        Firestation firestationToUpdate = new Firestation(
            "29 15th St", 
            "9");
        Firestation updatedFirestation = new Firestation(
            "29 15th St", 
            "10");
        //act
        FirestationRepository firestationRepository = new FirestationRepository(dataReaderMock);
        firestationRepository.saveFirestation(firestationToUpdate);
        firestationRepository.updateFirestation(address, station, updatedFirestation);
        //assert 
        List<Firestation> result = firestationRepository.getFirestations();
        assertEquals(1, result.size());
        assertEquals(updatedFirestation, result.get(0));
        
    }
}

    