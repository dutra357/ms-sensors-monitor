package com.dutra.sensors.monitor.domain.model;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SensorId implements Serializable {

    private TSID tsid;

    protected SensorId() {}

    public SensorId(TSID tsid) {
        Objects.requireNonNull(tsid);
        this.tsid = tsid;
    }

    public SensorId(Long tsid) {
        Objects.requireNonNull(tsid);
        this.tsid = TSID.from(tsid);
    }

    public SensorId(String tsid) {
        Objects.requireNonNull(tsid);
        this.tsid = TSID.from(tsid);
    }

    public TSID getTsid() {
        return tsid;
    }

    public void setTsid(TSID tsid) {
        this.tsid = tsid;
    }

    @Override
    public String toString() {
        return tsid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SensorId sensorId = (SensorId) o;
        return Objects.equals(tsid, sensorId.tsid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tsid);
    }
}
