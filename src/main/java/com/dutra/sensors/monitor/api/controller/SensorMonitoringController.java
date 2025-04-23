package com.dutra.sensors.monitor.api.controller;

import com.dutra.sensors.monitor.api.model.OutPut;
import com.dutra.sensors.monitor.domain.model.SensorId;
import com.dutra.sensors.monitor.domain.model.SensorMonitoring;
import com.dutra.sensors.monitor.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;


@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
public class SensorMonitoringController {

    private final SensorMonitoringRepository repository;

    public SensorMonitoringController(SensorMonitoringRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public OutPut getDetails(@PathVariable TSID sensorId) {

        SensorMonitoring sensorById = repository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new OutPut(sensorById);
    }

    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eneable(@PathVariable TSID sensorId) {

        SensorMonitoring sensorById = repository.findById(new SensorId(sensorId))
                .orElseGet(() -> {
                    SensorMonitoring sensorMonitoring = new SensorMonitoring();
                    sensorMonitoring.setId(new SensorId(sensorId));
                    return sensorMonitoring;
                });

        if (Boolean.TRUE.equals(sensorById.getEnabled())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        sensorById.setEnabled(true);
        repository.save(sensorById);
    }

    @DeleteMapping("/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {

        SensorMonitoring sensorById = repository.findById(new SensorId(sensorId))
                .orElseGet(() -> {
                    SensorMonitoring sensorMonitoring = new SensorMonitoring();
                    sensorMonitoring.setId(new SensorId(sensorId));
                    return sensorMonitoring;
                });

        sensorById.setEnabled(false);
        repository.save(sensorById);
    }
}
