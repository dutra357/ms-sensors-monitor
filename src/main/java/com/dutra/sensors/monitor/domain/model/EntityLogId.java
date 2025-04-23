package com.dutra.sensors.monitor.domain.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class EntityLogId implements Serializable {

    private UUID id;

    protected EntityLogId() {}

    public EntityLogId(UUID id) {
        this.id = id;
    }

    public EntityLogId(String id) {
        this.id = UUID.fromString(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EntityLogId {" +
                "id = " + id +
                '}';
    }
}
