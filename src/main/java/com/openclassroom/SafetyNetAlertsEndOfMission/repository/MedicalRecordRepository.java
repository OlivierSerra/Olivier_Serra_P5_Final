package com.openclassroom.SafetyNetAlertsEndOfMission.repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import java.util.List;
import java.util.Optional;

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

    /* find one medicalRecord
    * 
    */    
    //fait fonctionner la fonction delete de type medicalRecord
    public MedicalRecord medicalRecord(String firstName, String lastName) {  
        for (MedicalRecord medicalRecord : medicalRecords) {
        if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
            return medicalRecord; 
        }
    }
    return null; 
    }

    //fait fonctionner la fonction update de type optionnal
    public MedicalRecord FindByAdressAndlastNameNumber(String firstName, String lastName) {  
        for (MedicalRecord medicalRecord : medicalRecords) {
        if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
            return medicalRecord; 
        }
    }
    return null; 
    }

    /*
    *Save
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
    * update
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
/*************************************** Delete ******************************** */
 
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
