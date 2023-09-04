/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;


public class GetMsiResponse {
    
    public String contentType;

    public GetMsiResponse withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public au.gov.amsa.egc.egc_client.models.shared.Error error;

    public GetMsiResponse withError(au.gov.amsa.egc.egc_client.models.shared.Error error) {
        this.error = error;
        return this;
    }
    
    /**
     * OK
     */
    
    public java.util.Map<String, Object> msi;

    public GetMsiResponse withMsi(java.util.Map<String, Object> msi) {
        this.msi = msi;
        return this;
    }
    
    
    public Integer statusCode;

    public GetMsiResponse withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    
    public HttpResponse<byte[]> rawResponse;

    public GetMsiResponse withRawResponse(HttpResponse<byte[]> rawResponse) {
        this.rawResponse = rawResponse;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public String getMsi400TextWildcardString;

    public GetMsiResponse withGetMsi400TextWildcardString(String getMsi400TextWildcardString) {
        this.getMsi400TextWildcardString = getMsi400TextWildcardString;
        return this;
    }
    
    /**
     * Unauthorized (must authenticate)
     */
    
    public String getMsi401TextWildcardString;

    public GetMsiResponse withGetMsi401TextWildcardString(String getMsi401TextWildcardString) {
        this.getMsi401TextWildcardString = getMsi401TextWildcardString;
        return this;
    }
    
    /**
     * Not allowed
     */
    
    public String getMsi403TextWildcardString;

    public GetMsiResponse withGetMsi403TextWildcardString(String getMsi403TextWildcardString) {
        this.getMsi403TextWildcardString = getMsi403TextWildcardString;
        return this;
    }
    
    /**
     * Resource Not Found
     */
    
    public String getMsi404TextWildcardString;

    public GetMsiResponse withGetMsi404TextWildcardString(String getMsi404TextWildcardString) {
        this.getMsi404TextWildcardString = getMsi404TextWildcardString;
        return this;
    }
    
    /**
     * Unexpected error on the server
     */
    
    public String getMsi500TextWildcardString;

    public GetMsiResponse withGetMsi500TextWildcardString(String getMsi500TextWildcardString) {
        this.getMsi500TextWildcardString = getMsi500TextWildcardString;
        return this;
    }
    
    public GetMsiResponse(@JsonProperty("ContentType") String contentType, @JsonProperty("StatusCode") Integer statusCode) {
        this.contentType = contentType;
        this.statusCode = statusCode;
  }
}