package com.dutra.sensors.monitor.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

@Entity
public class SensorMonitoring {

    @Id
    @EmbeddedId
    private SensorId id;
    private Double lastTemperature;
    private OffsetDateTime updateAt;
    private Boolean enabled;

    public SensorMonitoring() {}
    public SensorMonitoring(SensorId id, Double lastTemperature,
                            OffsetDateTime updateAt, Boolean enabled) {
        this.id = id;
        this.lastTemperature = lastTemperature;
        this.updateAt = updateAt;
        this.enabled = enabled;
    }

    public SensorId getId() {
        return id;
    }

    public void setId(SensorId id) {
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
}
