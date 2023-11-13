package com.openclassroom.SafetyNetAlertsEndOfMission.repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
//import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;
import java.util.List;
import java.util.Optional;

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

    /* find one Person
    * 
    */    
    //fait fonctionner la fonction delete de type person
    public Person person(String firstName, String lastName) {  
        for (Person person : persons) {
        if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
            return person; 
        }
    }
    return null; 
    }

    //fait fonctionner la fonction update de type optionnal
    public Person FindByNameFirstAndLastName(String firstName, String lastName) {  
        for (Person person : persons) {
        if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
            return person; 
        }
    }
    return null; 
    }

    /*
    *Save
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
    * update
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


/*************************************** Delete ******************************** */
 
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
