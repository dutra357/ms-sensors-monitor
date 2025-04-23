package com.dutra.sensors.monitor.api.controller;

import com.dutra.sensors.monitor.api.model.AlertInput;
import com.dutra.sensors.monitor.api.model.AlertOutPut;
import com.dutra.sensors.monitor.domain.model.SensorAlert;
import com.dutra.sensors.monitor.domain.model.SensorId;
import com.dutra.sensors.monitor.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
public class SensorAlertController {

    public final SensorAlertRepository repository;

    public SensorAlertController(SensorAlertRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public AlertOutPut search(@PathVariable TSID sensorId) {

        SensorAlert sensor = repository.findById(new SensorId(sensorId)).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return new AlertOutPut(sensor);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AlertOutPut update(@PathVariable TSID sensorId, @RequestBody AlertInput alertInput) {

        SensorAlert sensor = repository.findById(new SensorId(sensorId))
                .orElseGet(() -> new SensorAlert(new SensorId(sensorId), alertInput.getMinTemperature(),
                alertInput.getMaxTemperature()));

        sensor.setMinTemperature(alertInput.getMinTemperature());
        sensor.setMaxTemperature(alertInput.getMaxTemperature());
        repository.save(sensor);

        return new AlertOutPut(sensor);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {

        SensorAlert sensor = repository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensor.setMaxTemperature(null);
        sensor.setMinTemperature(null);
        repository.save(sensor);
    }
}
