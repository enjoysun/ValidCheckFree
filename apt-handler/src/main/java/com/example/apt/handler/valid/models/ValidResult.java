package com.example.apt.handler.valid.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author myou
 * @Date 2020/6/28  3:57 下午
 */
public class ValidResult {
    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    private boolean isError;
    private List<ErrorMessage> errors;

    public void addError(String prop, String error) {
        if (null == errors) {
            errors = new ArrayList<>();
        }
        errors.add(new ErrorMessage(prop, error));
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }
}
