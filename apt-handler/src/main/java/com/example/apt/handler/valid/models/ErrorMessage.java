package com.example.apt.handler.valid.models;

/**
 * @Author myou
 * @Date 2020/6/28  3:57 下午
 */
public class ErrorMessage {
    public ErrorMessage(String errorPropertyName, String errorMessage) {
        this.errorPropertyName = errorPropertyName;
        this.errorMessage = errorMessage;
    }

    private String errorPropertyName;

    public String getErrorPropertyName() {
        return errorPropertyName;
    }

    public void setErrorPropertyName(String errorPropertyName) {
        this.errorPropertyName = errorPropertyName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;
}
