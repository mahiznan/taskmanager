package com.ctc.itlease.taskmanager.payload;

public class ApiResponse {
    private Boolean succcess;
    private String message;

    public ApiResponse(Boolean succcess, String message) {
        this.succcess = succcess;
        this.message = message;
    }

    public Boolean getSucccess() {
        return succcess;
    }

    public void setSucccess(Boolean succcess) {
        this.succcess = succcess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}