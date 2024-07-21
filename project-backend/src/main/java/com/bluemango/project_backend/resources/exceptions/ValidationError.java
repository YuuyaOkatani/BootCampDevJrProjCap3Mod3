package com.bluemango.project_backend.resources.exceptions;

import java.util.List;

import java.util.ArrayList;
// Irá herdar a classe StandardError
public class ValidationError extends StandardError {

    private List<String> errors = new ArrayList<>();

    public void addError(String error){
        this.errors.add(error);
    }

    public List<String> getErrors(){
        return this.errors;
    }

    
}
