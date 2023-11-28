package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Person;

import lombok.*;
import java.util.List;

/**
 * Content class is used by Object Mapper to read the json file containing
 * all the data for creating Lists of Objects Persons, Firestations and MedicalRecords.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Content {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;

}
