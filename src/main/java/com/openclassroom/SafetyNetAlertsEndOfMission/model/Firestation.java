package com.openclassroom.SafetyNetAlertsEndOfMission.model;

import lombok.*;

/**
 * Firestation class creates objects containing a common propertie
 * with Person objects (address)
 * A Firestation can have multiple addresses
 */
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Firestation {
    private String address;
    private String station;
    public Firestation get() {
        return null;
    }
}
