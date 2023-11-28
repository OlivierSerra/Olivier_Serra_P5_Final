package com.openclassroom.SafetyNetAlertsEndOfMission.services;

import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;
import com.openclassroom.SafetyNetAlertsEndOfMission.repository.MedicalRecordRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Data
@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService (MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.getMedicalRecords();
    }

/*
* add medicalRecord in the list
*/
    public MedicalRecord save(MedicalRecord medicalRecord ) {
        MedicalRecord addedmedicalRecord = medicalRecordRepository.saveMedicalRecord(medicalRecord); 
        return addedmedicalRecord;
    }

/*
* This is used to delete a medicalRecord from the list
*/
    public MedicalRecord delete(String firstName, String lastName) {
        return medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }

/*
* This is used to return a medicalRecord if criterias match  
*/       
    public MedicalRecord update(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) {
        return medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecordToUpdate);   
    }

/*
* This is used to return one medicalRecord if criterias match  
*/

    public MedicalRecord getMedicalRecord (String firstName, String lastName) {
        return medicalRecordRepository.find(firstName, lastName);
    }
}

