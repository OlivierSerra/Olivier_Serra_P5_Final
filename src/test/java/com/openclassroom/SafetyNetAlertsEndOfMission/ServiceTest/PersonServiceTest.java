package com.openclassroom.SafetyNetAlertsEndOfMission.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;
import com.openclassroom.SafetyNetAlertsEndOfMission.repository.PersonRepository;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.PersonService;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.jupiter.api.Test;



@SpringBootTest
public class PersonServiceTest {

    //private PersonService personService;

/********************** test Save    ***********************/
    @Test
    public void testSave() {
        // Créer un mock de PersonRepository
        PersonRepository personRepositoryMock = mock(PersonRepository.class);

        // Créer une instance de PersonService avec le mock
        PersonService personService = new PersonService(personRepositoryMock);

        // Créer une personne fictive
        Person personToSave = new Person(
            "Marie", 
            "Moore",
            "1565 Culver St",
            "Culver",
            "97451",
            "841-874-6565",
            "MarieMoore@email.com");

        // Configurer le comportement du mock pour retourner la même personne lors de l'appel à save
        when(personRepositoryMock.save(personToSave)).thenReturn(personToSave);

        // Appeler la méthode save de PersonService
        Person savedPerson = personService.save(personToSave);

        // Vérifier si la méthode save de PersonRepository a été appelée avec la personne correcte
        verify(personRepositoryMock, times(1)).save(personToSave);

        // Vérifier si la personne retournée par PersonService est la même que celle sauvegardée
        assertSame(personToSave, savedPerson);
    }
/************************************ test delete    **************************/
    @Test
    public void testDelete() {
        // Créer un mock de PersonRepository
        PersonRepository personRepositoryMock = mock(PersonRepository.class);

        // Créer une instance de PersonService avec le mock
        PersonService personService = new PersonService(personRepositoryMock);

        // Créer une personne fictive à supprimer
        String firstNameToDelete = "Marie";
        String lastNameToDelete = "Moore";

        // Configurer le comportement du mock pour retourner la même personne lors de l'appel à deletePerson
        Person deletedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6565","MarieMoore@email.com");
        when(personRepositoryMock.deletePerson(firstNameToDelete, lastNameToDelete)).thenReturn(deletedPerson);

        // Appeler la méthode delete de PersonService
        Person result = personService.delete(firstNameToDelete, lastNameToDelete);

        // Vérifier si la méthode deletePerson de PersonRepository a été appelée avec les paramètres corrects
        verify(personRepositoryMock, times(1)).deletePerson(firstNameToDelete, lastNameToDelete);

        // Vérifier si la personne retournée par PersonService est la même que celle supprimée
        assertSame(deletedPerson, result);
    }

    /****************************** test update *************************/
    
    @Test
    public void testUpdate() {
        // Créer un mock de PersonRepository
        PersonRepository personRepositoryMock = mock(PersonRepository.class);

        // Créer une instance de PersonService avec le mock
        PersonService personService = new PersonService(personRepositoryMock);

        // Créer une personne fictive à mettre à jour
        String firstNameToUpdate = "Marie";
        String lastNameToUpdate = "Moore";
        Person personToUpdate = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6565","MarieMoore@email.com");

        // Configurer le comportement du mock pour retourner la même personne lors de l'appel à updatePerson
        when(personRepositoryMock.updatePerson(firstNameToUpdate, lastNameToUpdate, personToUpdate)).thenReturn(personToUpdate);

        // Appeler la méthode update de PersonService
        Person result = personService.update(firstNameToUpdate, lastNameToUpdate, personToUpdate);

        // Vérifier si la méthode updatePerson de PersonRepository a été appelée avec les paramètres corrects
        verify(personRepositoryMock, times(1)).updatePerson(firstNameToUpdate, lastNameToUpdate, personToUpdate);

        // Vérifier si la personne retournée par PersonService est la même que celle mise à jour
        assertSame(personToUpdate, result);
    }
/* 
    @Test
    public void testGetPerson() {
        // Créer un mock de PersonRepository
        PersonRepository personRepositoryMock = mock(PersonRepository.class);

        // Créer une instance de PersonService avec le mock
        PersonService personService = new PersonService(personRepositoryMock);

        // Créer une personne fictive à récupérer
        String firstNameToGet = "Marie";
        String lastNameToGet = "Moore";
        Person personToGet = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6565","MarieMoore@email.com");

        // Configurer le comportement du mock pour retourner la même personne lors de l'appel à getPerson
        when(personRepositoryMock.FindByNameFirstAndLastName(firstNameToGet, lastNameToGet)).thenReturn(personToGet);

        // Appeler la méthode getPerson de PersonService
        Person result = personService.getPerson(firstNameToGet, lastNameToGet);

        // Vérifier si la méthode getPerson de PersonRepository a été appelée avec les paramètres corrects
        verify(personRepositoryMock, times(1)).FindByNameFirstAndLastName(firstNameToGet, lastNameToGet);

        // Vérifier si la personne retournée par PersonService est la même que celle récupérée
        assertSame(personToGet, result);
    }
*/
}










