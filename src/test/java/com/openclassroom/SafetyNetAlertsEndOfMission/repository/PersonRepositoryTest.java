package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PersonRepositoryTest {

    
    private PersonRepository personRepository;
    private JsonReader dataReaderMock;

    @Autowired
    public PersonRepositoryTest(JsonReader dataReaderMock) throws Exception {
        this.dataReaderMock = dataReaderMock; 
        this.personRepository = new PersonRepository(dataReaderMock);
    }

    @Test
    public void testGetPerson() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
       Person personToFind = new Person(
            "Olivier", 
            "Serra",
            "1565 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "olivierSerra@email.com");
        List<Person> PersonList = Arrays.asList(personToFind);
        //act
        when(dataReaderMock.getPersonsData()).thenReturn(PersonList);
        PersonRepository PersonRepository = new PersonRepository(dataReaderMock); 
        List<Person> result = PersonRepository.getPersons();
        //assert
        assertEquals(1, result.size());
        assertEquals(personToFind, result.get(0));
    }
/********************** test Save    ***********************/

     @Test
    public void testSavePerson() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
        Person personToSave = new Person(
            "Olivier", 
            "Serra",
            "1565 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "olivierSerra@email.com");
        //act
        PersonRepository personRepository = new PersonRepository(dataReaderMock);
        personRepository.save(personToSave);
        //assert 
        List<Person> result = personRepository.getPersons();
        assertEquals(1, result.size());
        assertEquals(personToSave, result.get(0));
    }
/************************************ test delete    **************************/
    
     @Test
    public void testDeletePerson() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
        Person personToSave = new Person(
            "Olivier", 
            "Serra",
            "1565 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "olivierSerra@email.com");
        //act
        PersonRepository personRepository = new PersonRepository(dataReaderMock);
        personRepository.deletePerson(
            "Olivier", 
            "Serra");
        //assert 
        List<Person> result = personRepository.getPersons();
        assertEquals(0, result.size());
    }

    /****************************** test update *************************/
    
     @Test
    public void testUpdatePerson() throws Exception {
        //arrange
        JsonReader dataReaderMock = mock(JsonReader.class);
        String firstNameToUpdate = "Olivier";
        String lastNameToUpdate = "Serra";
        Person personToUpdate = new Person(
            "Olivier", 
            "Serra",
            "1565 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "olivierSerra@email.com");
        Person updatedPerson = new Person(
            "Olivier", 
            "Serra",
            "1556 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "olivierSerra@email.com");    
        //act
        PersonRepository personRepository = new PersonRepository(dataReaderMock);
        personRepository.save(personToUpdate);
        personRepository.updatePerson(firstNameToUpdate,lastNameToUpdate, updatedPerson);
        //assert 
        List<Person> result = personRepository.getPersons();
        assertEquals(1, result.size());
        assertEquals(updatedPerson, result.get(0));
    }
}










