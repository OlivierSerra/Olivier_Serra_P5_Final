package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;
import java.util.List;


@Component
@Repository
public class PersonRepository {

    public List<Person> persons;
    private JsonReader jsonDataReader; 

    public PersonRepository(JsonReader jsonDataReader) throws Exception {
        this.jsonDataReader = jsonDataReader;
        this.persons = this.jsonDataReader.getPersonsData();
    }
    
    public List<Person> findAll() {
        return this.persons;
    }

/* 
 * These are used to find one Person in the list 
 */    
    // delete function can operate with this function
    public Person person(String firstName, String lastName) {  
        for (Person person : persons) {
        if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
            return person; 
        }
    }
    return null; 
    }

    //update function can operate with this function
    public Person FindByNameFirstAndLastName(String firstName, String lastName) {  
        for (Person person : persons) {
        if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
            return person; 
        }
    }
    return null; 
    }

/*
 * This is used to save a person in the list
 */
    public Person save(Person person) {
        for (Person existingPerson : persons) {
            if (existingPerson.getFirstName().equals(person.getFirstName()) && existingPerson.getLastName().equals(person.getLastName())) {
                return existingPerson;
            }
        }
        persons.add(person); 
        return person;
    }

       
    /*
    * This is used to update a person in the list
    */
    public Person updatePerson(String firstName, String lastName, Person PersonToUpdate) {
        Person personFound = FindByNameFirstAndLastName(firstName, lastName);

        if (personFound != null) {
            Person updatedPerson = personFound.get();

            updatedPerson.setAddress(PersonToUpdate.getAddress());
            updatedPerson.setCity(PersonToUpdate.getCity());
            updatedPerson.setZip(PersonToUpdate.getZip());
            updatedPerson.setPhone(PersonToUpdate.getPhone());
            updatedPerson.setEmail(PersonToUpdate.getEmail());

            return save(updatedPerson);
        }

        return null;
    }


/*
 * This is used to delete one person from the person list 
 */
 
    public Person deletePerson(String firstName, String lastName) {
        Person deletedPerson = null;
        Person PersonToDelete = person(firstName, lastName);
        for (Person p : this.persons) {
        if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
            persons.remove(p);
            return deletedPerson = PersonToDelete; 
        }
    }
    return deletedPerson;
    }

}
