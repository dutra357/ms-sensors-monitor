package com.dutra.sensors.monitor.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class SensorAlert {

    @EmbeddedId
    private SensorId sensorId;
    private Double maxTemperature;
    private Double minTemperature;

    public SensorAlert() {}

    public SensorAlert(SensorId id, Double maxTemperature, Double minTemperature) {
        this.sensorId = id;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public SensorId getId() {
        return sensorId;
    }

    public void setId(SensorId id) {
        this.sensorId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SensorAlert that = (SensorAlert) o;
        return Objects.equals(sensorId, that.sensorId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sensorId);
    }

    @Override
    public String toString() {
        return "SensorAlert{" +
                "id=" + sensorId +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                '}';
    }
}
