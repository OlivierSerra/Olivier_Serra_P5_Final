package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.PersonService;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonRepositoryTest {

/*************** test pour accéder à une personne  **********************/
    @Test
    public void testGetPerson() {
        // arrange 
        PersonRepository personRepositoryMock = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepositoryMock);
        String firstNameToGet = "Olivier";
        String lastNameToGet = "Serra";
        Person personToGet = new Person("Olivier", "Serra", "1565 Culver St", "Culver", "97451", "841-874-6565","OlivierSerra@email.com");

        //act
        when(personRepositoryMock.FindByNameFirstAndLastName(firstNameToGet, lastNameToGet)).thenReturn(personToGet);
        Person result = personService.getPerson(firstNameToGet, lastNameToGet);

        //assert
        verify(personRepositoryMock, times(1)).FindByNameFirstAndLastName(firstNameToGet, lastNameToGet);
        assertSame(personToGet, result);
    }

/********************** test Save    ***********************/
    @Test
    public void testSave() {
        //arrange
        PersonRepository personRepositoryMock = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepositoryMock);
        Person personToSave = new Person(
            "Olivier", 
            "Serra",
            "1565 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "olivierSerra@email.com");

        // act
        when(personRepositoryMock.save(personToSave)).thenReturn(personToSave);

        Person savedPerson = personService.save(personToSave);

        //assert
        verify(personRepositoryMock, times(1)).save(personToSave);
        assertSame(personToSave, savedPerson);
    }
/************************************ test delete    **************************/
    @Test
    public void testDelete() {
        // arrange
        PersonRepository personRepositoryMock = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepositoryMock);
        String firstNameToDelete = "Olivier";
        String lastNameToDelete = "Serra";

        Person deletedPerson = new Person("Olivier", "Serra", "1565 Culver St", "Culver", "97451", "841-874-6565","OlivierSerra@email.com");
        //act
        when(personRepositoryMock.deletePerson(firstNameToDelete, lastNameToDelete)).thenReturn(deletedPerson);

        Person result = personService.delete(firstNameToDelete, lastNameToDelete);

        //assert
        verify(personRepositoryMock, times(1)).deletePerson(firstNameToDelete, lastNameToDelete);
        assertSame(deletedPerson, result);
    }

    /****************************** test update *************************/
    
    @Test
    public void testUpdate() {
        // arrange
        PersonRepository personRepositoryMock = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepositoryMock);
        String firstNameToUpdate = "Olivier";
        String lastNameToUpdate = "Serra";
        Person personToUpdate = new Person("Olivier", "Serra", "1565 Culver St", "Culver", "97451", "841-874-6565","OlivierSerra@email.com");

        // act
        when(personRepositoryMock.updatePerson(firstNameToUpdate, lastNameToUpdate, personToUpdate)).thenReturn(personToUpdate);
        Person result = personService.update(firstNameToUpdate, lastNameToUpdate, personToUpdate);

        // assert
        verify(personRepositoryMock, times(1)).updatePerson(firstNameToUpdate, lastNameToUpdate, personToUpdate);
        assertSame(personToUpdate, result);
    }
}










