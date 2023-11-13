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
import com.openclassroom.SafetyNetAlertsEndOfMission.services.FirestationService;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;

@RestController
public class FirestationController {


    @Autowired
    private FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

/** This method is used to find the list with all the people *****/
    @GetMapping("/firestation") 
        public List<Firestation>firestations() {
            return firestationService.findAll();
        }
 
/*  
* This méthod is used to add one firestation in the list
*/
    @PostMapping("/firestation")
        public Firestation addfirestation(@RequestBody Firestation firestation) {
            return firestationService.save(firestation);
        }

/*
* This method is used to delete one firestation in the list
*/ 
    @DeleteMapping("/firestation/{address}/{station}")
    public Firestation deletefirestation(
            @PathVariable("address") String address,
            @PathVariable("station") String station,
            @RequestBody Firestation firestation) { 
    //logger.info("you deleted a firestation");  
    return firestationService.delete(address, station);
    }

/*
* This method is used to update info for one firestation in the list
*/      
@PutMapping("/firestation/{address}/{station}")
    public Firestation updatefirestation(
            @PathVariable("address") String address,
            @PathVariable("station") String station,
            @RequestBody Firestation FirestationToUpdate)  {
        return firestationService.update(address, station, FirestationToUpdate);        
    }
}






    