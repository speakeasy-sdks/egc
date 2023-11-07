/* 
 * Code generated by Speakeasy (https://speakeasyapi.dev). DO NOT EDIT.
 */

package au.gov.amsa.egc.egc_client.models.shared;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorInput - an error message (response)
 */

public class ErrorInput {
    /**
     * an error message (response)
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("cause")
    public Error cause;

    public ErrorInput withCause(Error cause) {
        this.cause = cause;
        return this;
    }
    
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("errorMessage")
    public String errorMessage;

    public ErrorInput withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
    
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("errorType")
    public String errorType;

    public ErrorInput withErrorType(String errorType) {
        this.errorType = errorType;
        return this;
    }
    
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("stackTrace")
    public String stackTrace;

    public ErrorInput withStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }
    
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("statusCode")
    public Integer statusCode;

    public ErrorInput withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    
    public ErrorInput(){}
}
