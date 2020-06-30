package com.example.demo;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.demo.rest.*;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
    	// to może jakoś da się wykorzystać?
    	// forApplication(new RestApplication());
    	
        register(Hello.class);
        register(Index.class);
        register(RPaczka.class);
        register(RPaczkaXml.class);
        register(RPaczkaJson.class);
        register(Tajne.class);
    }
}