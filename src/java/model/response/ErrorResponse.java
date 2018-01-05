/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.response;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Dima
 */
public class ErrorResponse {
    @Expose(serialize = true , deserialize = true)
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
