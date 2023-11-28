package com.openclassroom.SafetyNetAlertsEndOfMission.controller;

import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.PersonService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
        // arrange
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

        //act
        when(personService.findAll()).thenReturn(personList);

        //assert
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                // Vérifier la structure de la réponse JSON, ajustez-la en fonction de votre implémentation
                .andExpect(jsonPath("$[0].firstName").value("Marie"))
                .andExpect(jsonPath("$[1].firstName").value("Olivier"));

        verify(personService, times(1)).findAll();
    }


@Test
public void testAddPerson() throws Exception {
    //arrange
    String jsonInput = "{ \"firstName\":\"Marie\", \"lastName\":\"Moore\", \"address\":\"1565 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6521\", \"email\":\"MarieMoore@email.com\" }";
    
    Person inputPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");
    Person savedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");

    //act
    when(personService.save(inputPerson)).thenReturn(savedPerson);

    //assert 
    mockMvc.perform(MockMvcRequestBuilders.post("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonInput))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Marie"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Moore"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1565 Culver St"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Culver"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("97451"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("841-874-6521"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("MarieMoore@email.com"));

    verify(personService, times(1)).save(inputPerson);
}

@Test
    public void testUpdatePerson() throws Exception {
        //arrange
        String jsonInput = "{ \"firstName\":\"Marie\", \"lastName\":\"Moore\", \"address\":\"1565 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6521\", \"email\":\"MarieMoore@email.com\" }";
        Person personToUpdate = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");
        Person updatedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");

        //act
        when(personService.update("Marie", "Moore", personToUpdate)).thenReturn(updatedPerson);

        //assert
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

        verify(personService, times(1)).update("Marie", "Moore", personToUpdate);
    }

@Test
    public void testDeletePerson() throws Exception {
        //arrange
        String jsonInput = "{ \"firstName\":\"Marie\", \"lastName\":\"Moore\", \"address\":\"1565 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6521\", \"email\":\"MarieMoore@email.com\" }";
        Person inputPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");
        Person deletedPerson = new Person("Marie", "Moore", "1565 Culver St", "Culver", "97451", "841-874-6521", "MarieMoore@email.com");

        //act
        when(personService.delete("Marie", "Moore")).thenReturn(deletedPerson);

        //assert
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

        verify(personService, times(1)).delete("Marie", "Moore");
    }

}
 