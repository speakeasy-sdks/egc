/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import au.gov.amsa.egc.egc_client.utils.SpeakeasyMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;


public class GetMsiSecurity {
    @SpeakeasyMetadata("security:scheme=true,type=http,subtype=bearer,name=Authorization")
    public String bearer;

    public GetMsiSecurity withBearer(String bearer) {
        this.bearer = bearer;
        return this;
    }
    
    public GetMsiSecurity(@JsonProperty("bearer") String bearer) {
        this.bearer = bearer;
  }
}
