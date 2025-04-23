package com.dutra.sensors.monitor.api.model;

import com.dutra.sensors.monitor.domain.model.EntityLog;
import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;
import java.util.UUID;

public class LogOutPut {

    private UUID id;
    private TSID sensorId;
    private OffsetDateTime registredAt;
    private Double value;

    public LogOutPut() {}
    public LogOutPut(UUID id, TSID sensorId,
                     OffsetDateTime registredAt, Double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.registredAt = registredAt;
        this.value = value;
    }

    public LogOutPut(EntityLog entityLog) {
        this.id = entityLog.getId().getId();
        this.sensorId = entityLog.getSensorId().getTsid();
        this.registredAt = entityLog.getRegisteredAt();
        this.value = entityLog.getValue();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TSID getSensorId() {
        return sensorId;
    }

    public void setSensorId(TSID sensorId) {
        this.sensorId = sensorId;
    }

    public OffsetDateTime getRegistredAt() {
        return registredAt;
    }

    public void setRegistredAt(OffsetDateTime registredAt) {
        this.registredAt = registredAt;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ModelOutPut{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", registredAt=" + registredAt +
                ", value=" + value +
                '}';
    }
}
