package com.dutra.sensors.monitor.domain.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class EntityLog {

    @EmbeddedId
    private EntityLogId id;

    @Embedded
    private SensorId sensorId;

    private OffsetDateTime registeredAt;

    @Column(name = "\"value\"")
    private Double value;

    protected EntityLog() {}
    public EntityLog(EntityLogId id, SensorId sensorId,
                     OffsetDateTime registeredAt, Double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.registeredAt = registeredAt;
        this.value = value;
    }

    public EntityLogId getId() {
        return id;
    }

    public void setId(EntityLogId id) {
        this.id = id;
    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public void setSensorId(SensorId sensorId) {
        this.sensorId = sensorId;
    }

    public OffsetDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityLog entityLog = (EntityLog) o;
        return Objects.equals(id, entityLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EntityLog{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", registeredAt=" + registeredAt +
                ", value=" + value +
                '}';
    }
}
