package com.openclassroom.SafetyNetAlertsEndOfMission.services;

import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;
import com.openclassroom.SafetyNetAlertsEndOfMission.repository.FirestationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Data
@Service
public class FirestationService {
    @Autowired
    private FirestationRepository firestationRepository;

    public FirestationService (FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

public List<Firestation> findAll() {
    return firestationRepository.findAll();
}

/*
* add person in the list
*/
public Firestation save(Firestation firestation ) {
    Firestation addedFirestation = firestationRepository.saveFirestation(firestation); 
    return addedFirestation;
    }

/*
* used to delete a person
*/
public Firestation delete(String address, String station) {
    return firestationRepository.deleteFirestation(address, station);
    }

/*
* return person if criterias match  
*/       
public Firestation update(String address, String station, Firestation firestationToUpdate) {
    return firestationRepository.updateFirestation(address, station, firestationToUpdate);   
    }

public Firestation getFirestation (String address, String station) {
    return firestationRepository.firestation(address, station);
}

}

