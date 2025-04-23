package com.dutra.sensors.monitor.domain.repository;

import com.dutra.sensors.monitor.domain.model.EntityLog;
import com.dutra.sensors.monitor.domain.model.EntityLogId;
import com.dutra.sensors.monitor.domain.model.SensorId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntityLogRepository extends JpaRepository<EntityLog, EntityLogId>{
    Page<EntityLog> findBySensorId(SensorId sensorId, Pageable pageable);
}
