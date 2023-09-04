/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.shared;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MetCoastalWarningArea {
    @JsonProperty("coastalWarningAreaCode")
    public String coastalWarningAreaCode;

    public MetCoastalWarningArea withCoastalWarningAreaCode(String coastalWarningAreaCode) {
        this.coastalWarningAreaCode = coastalWarningAreaCode;
        return this;
    }
    
    @JsonProperty("metAreaCode")
    public Integer metAreaCode;

    public MetCoastalWarningArea withMetAreaCode(Integer metAreaCode) {
        this.metAreaCode = metAreaCode;
        return this;
    }
    
    /**
     * Subject indicator associated with a Coastal Warning
     *             
     * 
     */
    @JsonProperty("subjectIndicator")
    public SubjectIndicator subjectIndicator;

    public MetCoastalWarningArea withSubjectIndicator(SubjectIndicator subjectIndicator) {
        this.subjectIndicator = subjectIndicator;
        return this;
    }
    
    public MetCoastalWarningArea(@JsonProperty("coastalWarningAreaCode") String coastalWarningAreaCode, @JsonProperty("metAreaCode") Integer metAreaCode, @JsonProperty("subjectIndicator") SubjectIndicator subjectIndicator) {
        this.coastalWarningAreaCode = coastalWarningAreaCode;
        this.metAreaCode = metAreaCode;
        this.subjectIndicator = subjectIndicator;
  }
}