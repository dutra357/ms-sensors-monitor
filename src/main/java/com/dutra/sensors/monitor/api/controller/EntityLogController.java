package com.dutra.sensors.monitor.api.controller;

import com.dutra.sensors.monitor.api.model.LogOutPut;
import com.dutra.sensors.monitor.domain.model.EntityLog;
import com.dutra.sensors.monitor.domain.model.SensorId;
import com.dutra.sensors.monitor.domain.repository.EntityLogRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
public class EntityLogController {

    private final EntityLogRepository repository;

    public EntityLogController(EntityLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<LogOutPut> search(@PathVariable TSID sensorId, @PageableDefault Pageable pageable) {
        Page<EntityLog> entityLog = repository.findBySensorId(new SensorId(sensorId), pageable);
        return entityLog.map(LogOutPut::new);
    }
}
