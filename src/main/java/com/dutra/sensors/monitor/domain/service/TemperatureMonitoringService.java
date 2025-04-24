package com.dutra.sensors.monitor.domain.service;

import com.dutra.sensors.monitor.api.model.LogDataModel;
import com.dutra.sensors.monitor.domain.model.EntityLog;
import com.dutra.sensors.monitor.domain.model.EntityLogId;
import com.dutra.sensors.monitor.domain.model.SensorId;
import com.dutra.sensors.monitor.domain.model.SensorMonitoring;
import com.dutra.sensors.monitor.domain.repository.EntityLogRepository;
import com.dutra.sensors.monitor.domain.repository.SensorMonitoringRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TemperatureMonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureMonitoringService.class);

    private final SensorMonitoringRepository sensorMonitoringRepository;
    private final EntityLogRepository entityLogRepository;

    public TemperatureMonitoringService(SensorMonitoringRepository sensorMonitoringRepository, EntityLogRepository entityLogRepository) {
        this.sensorMonitoringRepository = sensorMonitoringRepository;
        this.entityLogRepository = entityLogRepository;
    }

    @Transactional
    public void processTemperatureReading(LogDataModel logDataModel) {

        sensorMonitoringRepository.findById(new SensorId(logDataModel.getSensorId()))
                .ifPresentOrElse(sensor -> handleSensorMonitor(logDataModel, sensor),
                        () -> logIgnoredTemperature(logDataModel));
    }

    private void handleSensorMonitor(LogDataModel logDataModel, SensorMonitoring sensor) {

            if (Boolean.TRUE.equals(sensor.getEnabled())) {
                sensor.setLastTemperature(logDataModel.getValue());
                sensorMonitoringRepository.save(sensor);

                EntityLog entityLog = new EntityLog(
                        new EntityLogId(logDataModel.getId()),
                        new SensorId(logDataModel.getSensorId()),
                        logDataModel.getRegistredAt(),
                        logDataModel.getValue()
                );
                entityLogRepository.save(entityLog);
                logger.info("Saved entity log for sensor {} with value {}", logDataModel.getSensorId(), logDataModel.getValue());

            } else {
                logIgnoredTemperature(logDataModel);
            }
    }

    private void logIgnoredTemperature(LogDataModel logDataModel) {
        logger.info("Ignoring temperature reading from sensor {} with value {}",
                logDataModel.getSensorId(), logDataModel.getValue());
    }
}
