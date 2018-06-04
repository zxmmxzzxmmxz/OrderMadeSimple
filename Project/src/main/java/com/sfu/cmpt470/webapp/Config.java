package com.sfu.cmpt470.webapp;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Administrator on 6/4/2018.
 */
public class Config extends ResourceConfig {
    public Config(){
        packages("com.sfu.cmpt470.webapp");
    }
}
