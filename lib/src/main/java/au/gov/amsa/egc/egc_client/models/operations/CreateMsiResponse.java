/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;


public class CreateMsiResponse {
    /**
     * The unique identifier of the newly created MSI
     */
    
    public String twoHundredAndOneApplicationJsonMsiId;

    public CreateMsiResponse withTwoHundredAndOneApplicationJsonMsiId(String twoHundredAndOneApplicationJsonMsiId) {
        this.twoHundredAndOneApplicationJsonMsiId = twoHundredAndOneApplicationJsonMsiId;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public String fourHundredTextWildcardRes;

    public CreateMsiResponse withFourHundredTextWildcardRes(String fourHundredTextWildcardRes) {
        this.fourHundredTextWildcardRes = fourHundredTextWildcardRes;
        return this;
    }
    
    /**
     * Unauthorized (must authenticate)
     */
    
    public String fourHundredAndOneTextWildcardRes;

    public CreateMsiResponse withFourHundredAndOneTextWildcardRes(String fourHundredAndOneTextWildcardRes) {
        this.fourHundredAndOneTextWildcardRes = fourHundredAndOneTextWildcardRes;
        return this;
    }
    
    /**
     * Not allowed
     */
    
    public String fourHundredAndThreeTextWildcardRes;

    public CreateMsiResponse withFourHundredAndThreeTextWildcardRes(String fourHundredAndThreeTextWildcardRes) {
        this.fourHundredAndThreeTextWildcardRes = fourHundredAndThreeTextWildcardRes;
        return this;
    }
    
    /**
     * Unexpected error on the server
     */
    
    public String fiveHundredTextWildcardRes;

    public CreateMsiResponse withFiveHundredTextWildcardRes(String fiveHundredTextWildcardRes) {
        this.fiveHundredTextWildcardRes = fiveHundredTextWildcardRes;
        return this;
    }
    
    /**
     * HTTP response content type for this operation
     */
    
    public String contentType;

    public CreateMsiResponse withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public au.gov.amsa.egc.egc_client.models.shared.ErrorInput error;

    public CreateMsiResponse withError(au.gov.amsa.egc.egc_client.models.shared.ErrorInput error) {
        this.error = error;
        return this;
    }
    
    /**
     * HTTP response status code for this operation
     */
    
    public Integer statusCode;

    public CreateMsiResponse withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    
    public HttpResponse<byte[]> rawResponse;

    public CreateMsiResponse withRawResponse(HttpResponse<byte[]> rawResponse) {
        this.rawResponse = rawResponse;
        return this;
    }
    
    public CreateMsiResponse(@JsonProperty("ContentType") String contentType, @JsonProperty("StatusCode") Integer statusCode, @JsonProperty("RawResponse") HttpResponse<byte[]> rawResponse) {
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.rawResponse = rawResponse;
  }
}
