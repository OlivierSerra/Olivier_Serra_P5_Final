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
import com.openclassroom.SafetyNetAlertsEndOfMission.services.MedicalRecordService;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.MedicalRecord;

@RestController
public class MedicalRecordController {


    @Autowired
    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

/*
 * This method is used to put all the medicalRecord in the list 
 */

   @GetMapping("/medicalRecord") 
        public List<MedicalRecord>medicalRecords() {
            return medicalRecordService.findAll();
        }
 
/*  
* This méthod is used to add one medicalRecord on the list
*/
    @PostMapping("/medicalRecord")
        public MedicalRecord addmedicalRecord(@RequestBody MedicalRecord medicalRecord) {
            return medicalRecordService.save(medicalRecord);
        }

/*
* This method is used to delete one medicalRecord in the list
*/ 
    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public MedicalRecord deletemedicalRecord(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody MedicalRecord medicalRecord) { 
    //logger.info("you deleted a medicalRecord");  
    return medicalRecordService.delete(firstName, lastName);
    }

/*
* This method is used to update info for one medicalRecord in the list
*/      
@PutMapping("/medicalRecord/{firstName}/{lastName}")
    public MedicalRecord updatemedicalRecord(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody MedicalRecord medicalRecordToUpdate)  {
        return medicalRecordService.update(firstName, lastName, medicalRecordToUpdate);        
    }
}





    