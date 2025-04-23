package com.dutra.sensors.monitor.domain.repository;

import com.dutra.sensors.monitor.domain.model.SensorAlert;
import com.dutra.sensors.monitor.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId>{

    SensorAlert findBySensorId(SensorId sensorId);
}
