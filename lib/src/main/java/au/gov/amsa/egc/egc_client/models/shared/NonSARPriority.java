/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NonSARPriority {
    SAFETY("SAFETY"),
    URGENCY("URGENCY");

    @JsonValue
    public final String value;

    private NonSARPriority(String value) {
        this.value = value;
    }
}