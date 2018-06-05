package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("error")
    private String _error;

    public Error(String error){
        _error = error;
    }

    public void setError(String error){
        _error = error;
    }

    public String getError(){
        return _error;
    }
}
