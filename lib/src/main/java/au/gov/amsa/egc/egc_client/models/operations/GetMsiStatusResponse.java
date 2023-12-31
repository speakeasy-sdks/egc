/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;


public class GetMsiStatusResponse {
    /**
     * Bad request
     */
    
    public String fourHundredTextWildcardRes;

    public GetMsiStatusResponse withFourHundredTextWildcardRes(String fourHundredTextWildcardRes) {
        this.fourHundredTextWildcardRes = fourHundredTextWildcardRes;
        return this;
    }
    
    /**
     * Unauthorized (must authenticate)
     */
    
    public String fourHundredAndOneTextWildcardRes;

    public GetMsiStatusResponse withFourHundredAndOneTextWildcardRes(String fourHundredAndOneTextWildcardRes) {
        this.fourHundredAndOneTextWildcardRes = fourHundredAndOneTextWildcardRes;
        return this;
    }
    
    /**
     * Not allowed
     */
    
    public String fourHundredAndThreeTextWildcardRes;

    public GetMsiStatusResponse withFourHundredAndThreeTextWildcardRes(String fourHundredAndThreeTextWildcardRes) {
        this.fourHundredAndThreeTextWildcardRes = fourHundredAndThreeTextWildcardRes;
        return this;
    }
    
    /**
     * Resource Not Found
     */
    
    public String fourHundredAndFourTextWildcardRes;

    public GetMsiStatusResponse withFourHundredAndFourTextWildcardRes(String fourHundredAndFourTextWildcardRes) {
        this.fourHundredAndFourTextWildcardRes = fourHundredAndFourTextWildcardRes;
        return this;
    }
    
    /**
     * Unexpected error on the server
     */
    
    public String fiveHundredTextWildcardRes;

    public GetMsiStatusResponse withFiveHundredTextWildcardRes(String fiveHundredTextWildcardRes) {
        this.fiveHundredTextWildcardRes = fiveHundredTextWildcardRes;
        return this;
    }
    
    /**
     * HTTP response content type for this operation
     */
    
    public String contentType;

    public GetMsiStatusResponse withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public au.gov.amsa.egc.egc_client.models.shared.ErrorInput error;

    public GetMsiStatusResponse withError(au.gov.amsa.egc.egc_client.models.shared.ErrorInput error) {
        this.error = error;
        return this;
    }
    
    /**
     * HTTP response status code for this operation
     */
    
    public Integer statusCode;

    public GetMsiStatusResponse withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    
    public HttpResponse<byte[]> rawResponse;

    public GetMsiStatusResponse withRawResponse(HttpResponse<byte[]> rawResponse) {
        this.rawResponse = rawResponse;
        return this;
    }
    
    /**
     * OK
     */
    
    public au.gov.amsa.egc.egc_client.models.shared.StatusExtended statusExtended;

    public GetMsiStatusResponse withStatusExtended(au.gov.amsa.egc.egc_client.models.shared.StatusExtended statusExtended) {
        this.statusExtended = statusExtended;
        return this;
    }
    
    public GetMsiStatusResponse(@JsonProperty("ContentType") String contentType, @JsonProperty("StatusCode") Integer statusCode, @JsonProperty("RawResponse") HttpResponse<byte[]> rawResponse) {
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.rawResponse = rawResponse;
  }
}
