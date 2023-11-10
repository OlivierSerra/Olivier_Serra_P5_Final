package com.openclassroom.SafetyNetAlertsEndOfMission.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.openclassroom.SafetyNetAlertsEndOfMission.services.PersonService;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;

@RestController
public class PersonController {


    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

/** This method is used to find the list with all the people *****/
    @GetMapping("/person") 
        public List<Person>persons() {
            return personService.findAll();
        }
 
/*  
* This méthod is used to add one person in the list
*/
    @PostMapping("/person")
        public Person addPerson(@RequestBody Person person) {
            return personService.save(person);
        }

/*
* This method is used to delete one person in the list
*/ 
    @DeleteMapping("/person/{firstName}/{lastName}")
    public Person deletePerson(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody Person person) { 
    //logger.info("you deleted a person");  
    return personService.delete(firstName, lastName);
    }

/*
* This method is used to update info for one person in the list
*/      
@PutMapping("/person/{firstName}/{lastName}")
    public Person updatePerson(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody Person PersonToUpdate) {
        return personService.update(firstName, lastName, PersonToUpdate);        
    }
}






    