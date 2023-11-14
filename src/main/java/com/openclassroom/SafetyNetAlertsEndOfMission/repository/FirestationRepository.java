package com.openclassroom.SafetyNetAlertsEndOfMission.repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.openclassroom.SafetyNetAlertsEndOfMission.model.Firestation;

import java.util.List;

@Component
@Repository
public class FirestationRepository {
/*
 * this is the jsonReader to inject info in the firestation list 
 */
    public List<Firestation> firestations;
    private JsonReader jsonDataReader; 

    public FirestationRepository(JsonReader jsonDataReader) throws Exception {
        this.jsonDataReader = jsonDataReader;
        this.firestations = this.jsonDataReader.getFirestationsData();
    }
/*
 * this is used to have all the firestion
 */
    public List<Firestation> findAll() {
        return this.firestations;
    }

/* 
 * This is used to find one firestation from the list of firestation
 */    
    //Delete function can operate thanks to this method
    public Firestation firestation(String address, String station) {  
        for (Firestation firestation : firestations) {
        if (firestation.getAddress().equals(address) && firestation.getStation().equals(station)) {
            return firestation; 
        }
    }
    return null; 
    }
/*
 *  update function can operate with this method
 */
    public Firestation FindByAddressAndStationNumber(String address, String station) {  
        for (Firestation firestation : firestations) {
        if (firestation.getAddress().equals(address) && firestation.getStation().equals(station)) {
            return firestation; 
        }
    }
    return null; 
    }

/*
 * this is used to save one foirestation in the list of firestatiuon
 */
    public Firestation saveFirestation(Firestation firestation) {
        for (Firestation existingFirestation : firestations) {
            if (existingFirestation.getAddress().equals(firestation.getAddress()) && existingFirestation.getStation().equals(firestation.getStation())) {
                return existingFirestation;
            }
        }
        firestations.add(firestation); 
        return firestation;
    }

       
    /*
    * This is used to update info for one firestation in the Firestation list 
    */
    public Firestation updateFirestation(String address, String string, Firestation FirestationToUpdate) {
        Firestation firestationFound = FindByAddressAndStationNumber(address, string);

        if (firestationFound != null) {
            Firestation updatedFirestation = firestationFound.get();

            updatedFirestation.setStation(FirestationToUpdate.getStation());
            
            return saveFirestation(updatedFirestation);
        }
        return null;
    }
/*
 * This is used to delete a firestation from the firestation list 
 */
    public Firestation deleteFirestation(String address, String station) {
        Firestation deletedfirestation = null;
        Firestation firestationToDelete = firestation(address, station);
        for (Firestation p : this.firestations) {
        if (p.getAddress().equals(address) && p.getStation().equals(station)) {
            firestations.remove(p);
            return deletedfirestation = firestationToDelete; 
        }
    }
    return deletedfirestation;
    }
}
