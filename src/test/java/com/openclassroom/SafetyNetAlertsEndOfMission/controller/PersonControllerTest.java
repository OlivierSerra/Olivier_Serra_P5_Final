package com.openclassroom.SafetyNetAlertsEndOfMission.controller;

import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;
//import com.openclassroom.SafetyNetAlertsEndOfMission.repository.PersonRepository;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.PersonService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@WebMvcTest(PersonControllerTest.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonControllerTest personControllerTest;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPersons() throws Exception {
        // Données de test
        Person Person1 = new Person(
                "Marie",
                "Moore",
                "1565 Main Road",
                "Culver",
                "97451",
                "841-874-6521",
                "MarieMoore@email.com");
        Person Person2  = new Person(
                "Olivier",
                "Serra",
                "1550 Main Road",
                "Culver",
                "97451",
                "841-874-6542",
                "OlivierSerra@email.com");
        List<Person> personList = Arrays.asList(Person1, Person2);

        // Définir le comportement du service mocké
        when(personService.findAll()).thenReturn(personList);

    // Effectuer la requête et vérifier le statut de la réponse
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                // Vérifier la structure de la réponse JSON, ajustez-la en fonction de votre implémentation
                .andExpect(jsonPath("$[0].firstName").value("Marie"))
                .andExpect(jsonPath("$[1].firstName").value("Olivier"));

        // Vérifier que la méthode du service a été appelée une fois
        verify(personService, times(1)).findAll();
    }


@Test
public void testAddPerson() throws Exception {
    // Données de test
    String jsonInput = "{ \"firstName\":\"Marie\", \"lastName\":\"Moore\", \"address\":\"1565 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6521\", \"email\":\"MarieMoore@email.com\" }";
    
    Person inputPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");
    Person savedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");

    // Définir le comportement du service mocké
    when(personService.save(inputPerson)).thenReturn(savedPerson);

    // Effectuer la requête POST avec le JSON complexe
    mockMvc.perform(MockMvcRequestBuilders.post("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonInput))
            .andExpect(MockMvcResultMatchers.status().isOk())
            // Vérifier la structure de la réponse JSON, ajustez-la en fonction de votre implémentation
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Marie"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Moore"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1565 Culver St"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Culver"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("97451"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("841-874-6521"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("MarieMoore@email.com"));

    // Vérifier que la méthode du service a été appelée une fois avec les données attendues
    verify(personService, times(1)).save(inputPerson);
}

@Test
    public void testUpdatePerson() throws Exception {
        // Données de test
        String jsonInput = "{ \"firstName\":\"Marie\", \"lastName\":\"Moore\", \"address\":\"1565 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6521\", \"email\":\"MarieMoore@email.com\" }";
        Person personToUpdate = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");
        Person updatedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");

        // Définir le comportement du service mocké
        when(personService.update("Marie", "Moore", personToUpdate)).thenReturn(updatedPerson);

        // Effectuer la requête PUT avec le JSON complexe
        mockMvc.perform(MockMvcRequestBuilders.put("/person/Marie/Moore")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Vérifier la structure de la réponse JSON, ajustez-la en fonction de votre implémentation
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Marie"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Moore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1565 Culver St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Culver"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("97451"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("841-874-6521"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("MarieMoore@email.com"));

        // Vérifier que la méthode du service a été appelée une fois avec les données attendues
        verify(personService, times(1)).update("Marie", "Moore", personToUpdate);
    }

@Test
    public void testDeletePerson() throws Exception {
        // Données de test
        String jsonInput = "{ \"firstName\":\"Marie\", \"lastName\":\"Moore\", \"address\":\"1565 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6521\", \"email\":\"MarieMoore@email.com\" }";
        Person inputPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");
        Person deletedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");

        // Définir le comportement du service mocké
        when(personService.delete("Marie", "Moore")).thenReturn(deletedPerson);

// Effectuer la requête DELETE avec le JSON complexe
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/Marie/Moore")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Vérifier la structure de la réponse JSON, ajustez-la en fonction de votre implémentation
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Marie"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Moore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1565 Culver St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Culver"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("97451"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("841-874-6521"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("MarieMoore@email.com"));

        // Vérifier que la méthode du service a été appelée une fois avec les données attendues
        verify(personService, times(1)).delete("Marie", "Moore");
    }

}
 