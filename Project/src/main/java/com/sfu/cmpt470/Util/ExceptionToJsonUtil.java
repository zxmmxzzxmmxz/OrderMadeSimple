package com.sfu.cmpt470.Util;

import java.util.List;
import java.util.stream.Collectors;

public class ExceptionToJsonUtil {
    public static String exceptionToJson(List<Exception> exceptions){
        return "{error:["+String.join(",",exceptions.stream().map(Throwable::getLocalizedMessage).collect(Collectors.toList()))+"]}";
    }

}
