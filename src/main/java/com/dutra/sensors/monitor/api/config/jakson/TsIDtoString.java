package com.dutra.sensors.monitor.api.config.jakson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.hypersistence.tsid.TSID;

import java.io.IOException;

public class TsIDtoString extends JsonSerializer<TSID> {

    @Override
    public void serialize(TSID tsid, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeString(tsid.toString());
    }
}
