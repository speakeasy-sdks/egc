/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetMsisResponseBody - A list of MSIs and an optional continuation token (
 * to retrieve the next page of MSIs). If the list of
 * MSIs is empty there should be no continuation token. 
 * 
 */

public class GetMsisResponseBody {
    /**
     * Indicates to the server the starting point of the next page 
     * of results. The token is not expected to be anywhere near as
     * long as 4096 characters but good to put an upper bound on it. 
     * 
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("continuationToken")
    public String continuationToken;

    public GetMsisResponseBody withContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
        return this;
    }
    
    @JsonProperty("msis")
    public java.util.Map<String, java.lang.Object>[] msis;

    public GetMsisResponseBody withMsis(java.util.Map<String, java.lang.Object>[] msis) {
        this.msis = msis;
        return this;
    }
    
    public GetMsisResponseBody(@JsonProperty("msis") java.util.Map<String, java.lang.Object>[] msis) {
        this.msis = msis;
  }
}
