package com.dutra.sensors.monitor.api.model;

import com.dutra.sensors.monitor.domain.model.SensorMonitoring;
import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;

public class OutPut {

    private TSID id;
    private Double lastTemperature;
    private OffsetDateTime updateAt;
    private Boolean enabled;

    public OutPut(TSID id, Double lastTemperature,
                  OffsetDateTime updateAt, Boolean enabled) {
        this.id = id;
        this.lastTemperature = lastTemperature;
        this.updateAt = updateAt;
        this.enabled = enabled;
    }

    public OutPut(SensorMonitoring sensorMonitoring) {
        this.id = sensorMonitoring.getId().getTsid();
        this.lastTemperature = sensorMonitoring.getLastTemperature();
        this.updateAt = sensorMonitoring.getUpdateAt();
        this.enabled = sensorMonitoring.getEnabled();
    }

    public TSID getId() {
        return id;
    }

    public void setId(TSID id) {
        this.id = id;
    }

    public Double getLastTemperature() {
        return lastTemperature;
    }

    public void setLastTemperature(Double lastTemperature) {
        this.lastTemperature = lastTemperature;
    }

    public OffsetDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(OffsetDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "OutPut{" +
                "id=" + id +
                ", lastTemperature=" + lastTemperature +
                ", updateAt=" + updateAt +
                ", enabled=" + enabled +
                '}';
    }
}
