/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.shared;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NavArea {
    @JsonProperty("navAreaCode")
    public Integer navAreaCode;

    public NavArea withNavAreaCode(Integer navAreaCode) {
        this.navAreaCode = navAreaCode;
        return this;
    }
    
    public NavArea(@JsonProperty("navAreaCode") Integer navAreaCode) {
        this.navAreaCode = navAreaCode;
  }
}
