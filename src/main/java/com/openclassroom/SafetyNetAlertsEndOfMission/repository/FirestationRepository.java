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
    public List<Firestation> getFirestations() {
        return firestations;
}

/* 
 * This is used to find one firestation from the list of firestation
 */    
    public Firestation find(String address, String station) {  
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
    public Firestation updateFirestation(String address, String station, Firestation FirestationToUpdate) {
        Firestation firestationFound = find(address, station);
            if (firestationFound != null) {
                firestationFound.setStation(FirestationToUpdate.getStation());
                    return saveFirestation(firestationFound);
        }
        return null;
    }

    
/*
 * This is used to delete a firestation from the firestation list 
 */
    public Firestation deleteFirestation(String address, String station) {
        Firestation firestationToDelete = find(address, station);
            if (firestationToDelete != null) {
                firestations.remove(firestationToDelete);
                    return firestationToDelete;
        }
        return null;
    }
}
