package com.dutra.sensors.monitor.api.config.jakson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.hypersistence.tsid.TSID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TsIDJaksonConfig {

    @Bean
    public Module tsIDModule() {

        SimpleModule module =  new SimpleModule();
        module.addSerializer(TSID.class, new TSIDToStringSerializer());
        module.addDeserializer(TSID.class, new TSIDToStringDeserializer());
        return module;
    }
}
