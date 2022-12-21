package com.stackroute.emailservice.entity;

public class EmailResponse {
    private String message;
    private Boolean status;

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public Boolean getStatus() {

        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public EmailResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public EmailResponse() {
    }
}
