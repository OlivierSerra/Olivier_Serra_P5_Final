package com.openclassroom.SafetyNetAlertsEndOfMission.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import java.util.List;

@Component
@Repository
public class MedicalRecordRepository {

    public List<MedicalRecord> medicalRecords;
    private JsonReader jsonDataReader; 

    public MedicalRecordRepository(JsonReader jsonDataReader) throws Exception {
        this.jsonDataReader = jsonDataReader;
        this.medicalRecords = this.jsonDataReader.getMedicalRecordsData();
    }

    public List<MedicalRecord> findAll() {
        return this.medicalRecords;
    }

/* 
 * This is used to Find one medicalRecord
 */    
    //Delete function can operate with this method 
    public MedicalRecord medicalRecord(String firstName, String lastName) {  
        for (MedicalRecord medicalRecord : medicalRecords) {
        if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
            return medicalRecord; 
        }
    }
    return null; 
    }

    //update function can operate with this method 
    public MedicalRecord FindByAdressAndlastNameNumber(String firstName, String lastName) {  
        for (MedicalRecord medicalRecord : medicalRecords) {
        if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
            return medicalRecord; 
        }
    }
    return null; 
    }

    /*
    *This is used to save one medicalRecord in the medicalRecord List
    */
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        for (MedicalRecord existingmedicalRecord : medicalRecords) {
            if (existingmedicalRecord.getFirstName().equals(medicalRecord.getFirstName()) && existingmedicalRecord.getLastName().equals(medicalRecord.getLastName())) {
                return existingmedicalRecord;
            }
        }
        medicalRecords.add(medicalRecord); 
        return medicalRecord;
    }

    /*
    * this is used to update datas from MedicalRecord for one person
    */
    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) {
        MedicalRecord medicalRecordFound = FindByAdressAndlastNameNumber(firstName, lastName);

        if (medicalRecordFound != null) {
            MedicalRecord updatedmedicalRecord = medicalRecordFound.get();

            updatedmedicalRecord.setBirthdate(medicalRecordToUpdate.getBirthdate());
            updatedmedicalRecord.setMedications(medicalRecordToUpdate.getMedications());
            updatedmedicalRecord.setAllergies(medicalRecordToUpdate.getAllergies());

            return saveMedicalRecord(updatedmedicalRecord);
        }
        return null;
    }
/*
 * This is used to delete Medical files from Medical files 
 */
 
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord deletedmedicalRecord = null;
        MedicalRecord medicalRecordToDelete = medicalRecord(firstName, lastName);
        for (MedicalRecord p : this.medicalRecords) {
        if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
            medicalRecords.remove(p);
            return deletedmedicalRecord = medicalRecordToDelete; 
        }
    }
    return deletedmedicalRecord;
    }

}
