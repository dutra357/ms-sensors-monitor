package com.dutra.sensors.monitor.api.model;

import com.dutra.sensors.monitor.domain.model.SensorAlert;
import io.hypersistence.tsid.TSID;

public class AlertOutPut {

    private TSID id;
    private Double maxTemperature;
    private Double minTemperature;

    public AlertOutPut(TSID id, Double maxTemperature, Double minTemperature) {
        this.id = id;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public AlertOutPut(SensorAlert sensorAlert) {
        this.id = sensorAlert.getId().getTsid();
        this.maxTemperature = sensorAlert.getMaxTemperature();
        this.minTemperature = sensorAlert.getMinTemperature();
    }

    public TSID getId() {
        return id;
    }

    public void setId(TSID id) {
        this.id = id;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Override
    public String toString() {
        return "AlertOutPut{" +
                "id=" + id +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                '}';
    }
}
