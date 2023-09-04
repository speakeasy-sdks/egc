/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;


public class SendMsiResponse {
    
    public String contentType;

    public SendMsiResponse withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public au.gov.amsa.egc.egc_client.models.shared.Error error;

    public SendMsiResponse withError(au.gov.amsa.egc.egc_client.models.shared.Error error) {
        this.error = error;
        return this;
    }
    
    
    public Integer statusCode;

    public SendMsiResponse withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    
    public HttpResponse<byte[]> rawResponse;

    public SendMsiResponse withRawResponse(HttpResponse<byte[]> rawResponse) {
        this.rawResponse = rawResponse;
        return this;
    }
    
    /**
     * Bad request
     */
    
    public String sendMsi400TextWildcardString;

    public SendMsiResponse withSendMsi400TextWildcardString(String sendMsi400TextWildcardString) {
        this.sendMsi400TextWildcardString = sendMsi400TextWildcardString;
        return this;
    }
    
    /**
     * Unauthorized (must authenticate)
     */
    
    public String sendMsi401TextWildcardString;

    public SendMsiResponse withSendMsi401TextWildcardString(String sendMsi401TextWildcardString) {
        this.sendMsi401TextWildcardString = sendMsi401TextWildcardString;
        return this;
    }
    
    /**
     * Not allowed
     */
    
    public String sendMsi403TextWildcardString;

    public SendMsiResponse withSendMsi403TextWildcardString(String sendMsi403TextWildcardString) {
        this.sendMsi403TextWildcardString = sendMsi403TextWildcardString;
        return this;
    }
    
    /**
     * Resource Not Found
     */
    
    public String sendMsi404TextWildcardString;

    public SendMsiResponse withSendMsi404TextWildcardString(String sendMsi404TextWildcardString) {
        this.sendMsi404TextWildcardString = sendMsi404TextWildcardString;
        return this;
    }
    
    /**
     * Unexpected error on the server
     */
    
    public String sendMsi500TextWildcardString;

    public SendMsiResponse withSendMsi500TextWildcardString(String sendMsi500TextWildcardString) {
        this.sendMsi500TextWildcardString = sendMsi500TextWildcardString;
        return this;
    }
    
    public SendMsiResponse(@JsonProperty("ContentType") String contentType, @JsonProperty("StatusCode") Integer statusCode) {
        this.contentType = contentType;
        this.statusCode = statusCode;
  }
}