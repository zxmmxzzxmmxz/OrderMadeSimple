package com.sfu.cmpt470.Util;

import jersey.repackaged.com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VerificationExceptions extends Throwable{

    private ArrayList<Exception> _exceptions;

    public VerificationExceptions(){
        _exceptions = new ArrayList<>();
    }

    public VerificationExceptions add(Exception e){
        _exceptions.add(e);
        return this;
    }

    public VerificationExceptions add(String message){
        _exceptions.add(new Exception(message));
        return this;
    }

    public String toGson() {
        List<String> messages = _exceptions.stream().map(Throwable::getLocalizedMessage).collect(Collectors.toList());
        return "{error:["+ String.join(",",messages)+"]}";
    }

    public boolean isEmpty(){
        return _exceptions.isEmpty();
    }

    public ImmutableList<Exception> getExceptions(){
        return ImmutableList.copyOf(_exceptions);
    }

    @Override
    public String toString() {
        return _exceptions.toString();
    }
}
