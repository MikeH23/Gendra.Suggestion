package com.gendra.suggestion.exception;

/*import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;*/

public class Response {
    public Long responseStatus;
    public String responseError;
    public Object result;

    public Response() {
    }


    public Response(Long responseStatus, Object result) {
        this.responseStatus = responseStatus;
        this.result = result;
        this.responseError = "OK";
    }

    public Response(Long responseStatus, Object result, String Mensaje) {
        this.responseStatus = responseStatus;
        this.result = result;
        this.responseError = Mensaje;
    }



    public Long getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Long responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseError() {
        return responseError;
    }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }*/
}
