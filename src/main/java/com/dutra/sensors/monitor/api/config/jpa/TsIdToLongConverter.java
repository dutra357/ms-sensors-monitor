package com.dutra.sensors.monitor.api.config.jpa;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TsIdToLongConverter implements AttributeConverter<TSID, Long> {

    @Override
    public Long convertToDatabaseColumn(TSID attribute) {
        return attribute == null ? null : attribute.toLong();
    }

    @Override
    public TSID convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new TSID(dbData);
    }
}
