package com.dutra.sensors.monitor.api.model;

import com.dutra.sensors.monitor.domain.model.SensorAlert;

public class AlertInput {

    private Double maxTemperature;
    private Double minTemperature;

    public AlertInput(Double maxTemperature, Double minTemperature) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
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
}
