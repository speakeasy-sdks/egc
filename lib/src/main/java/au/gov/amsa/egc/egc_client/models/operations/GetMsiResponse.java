/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;


public class GetMsiResponse {
    /**
     * OK
     */
    
    public java.util.Map<String, Object> twoHundredApplicationJsonMsi;

    public GetMsiResponse withTwoHundredApplicationJsonMsi(java.util.Map<String, Object> twoHundredApplicationJsonMsi) {
        this.twoHundredApplicationJsonMsi = twoHundredApplicationJsonMsi;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public String fourHundredTextWildcardRes;

    public GetMsiResponse withFourHundredTextWildcardRes(String fourHundredTextWildcardRes) {
        this.fourHundredTextWildcardRes = fourHundredTextWildcardRes;
        return this;
    }
    
    /**
     * Unauthorized (must authenticate)
     */
    
    public String fourHundredAndOneTextWildcardRes;

    public GetMsiResponse withFourHundredAndOneTextWildcardRes(String fourHundredAndOneTextWildcardRes) {
        this.fourHundredAndOneTextWildcardRes = fourHundredAndOneTextWildcardRes;
        return this;
    }
    
    /**
     * Not allowed
     */
    
    public String fourHundredAndThreeTextWildcardRes;

    public GetMsiResponse withFourHundredAndThreeTextWildcardRes(String fourHundredAndThreeTextWildcardRes) {
        this.fourHundredAndThreeTextWildcardRes = fourHundredAndThreeTextWildcardRes;
        return this;
    }
    
    /**
     * Resource Not Found
     */
    
    public String fourHundredAndFourTextWildcardRes;

    public GetMsiResponse withFourHundredAndFourTextWildcardRes(String fourHundredAndFourTextWildcardRes) {
        this.fourHundredAndFourTextWildcardRes = fourHundredAndFourTextWildcardRes;
        return this;
    }
    
    /**
     * Unexpected error on the server
     */
    
    public String fiveHundredTextWildcardRes;

    public GetMsiResponse withFiveHundredTextWildcardRes(String fiveHundredTextWildcardRes) {
        this.fiveHundredTextWildcardRes = fiveHundredTextWildcardRes;
        return this;
    }
    
    /**
     * HTTP response content type for this operation
     */
    
    public String contentType;

    public GetMsiResponse withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public au.gov.amsa.egc.egc_client.models.shared.ErrorInput error;

    public GetMsiResponse withError(au.gov.amsa.egc.egc_client.models.shared.ErrorInput error) {
        this.error = error;
        return this;
    }
    
    /**
     * HTTP response status code for this operation
     */
    
    public Integer statusCode;

    public GetMsiResponse withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    
    public HttpResponse<byte[]> rawResponse;

    public GetMsiResponse withRawResponse(HttpResponse<byte[]> rawResponse) {
        this.rawResponse = rawResponse;
        return this;
    }
    
    public GetMsiResponse(@JsonProperty("ContentType") String contentType, @JsonProperty("StatusCode") Integer statusCode) {
        this.contentType = contentType;
        this.statusCode = statusCode;
  }
}
