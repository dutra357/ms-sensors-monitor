package com.dutra.sensors.monitor.domain.repository;

import com.dutra.sensors.monitor.domain.model.SensorId;
import com.dutra.sensors.monitor.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId>{
}
