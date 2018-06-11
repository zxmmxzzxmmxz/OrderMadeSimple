package com.sfu.cmpt470.pojo;

public class SessionToken {
    private String _token;
    private String _userName;

    private SessionToken(){
        //if there is no token this shouldn't exist
    }

    public SessionToken(String token){
        _token = token;
    }

    public String getToken() {
        return _token;
    }

    public void setToken(String token) {
        this._token = _token;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        this._userName = _userName;
    }
}
