package com.dutra.sensors.monitor.domain.service;

import com.dutra.sensors.monitor.api.model.LogDataModel;
import com.dutra.sensors.monitor.domain.model.SensorId;
import com.dutra.sensors.monitor.domain.repository.SensorAlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SensorAlertService {

    private static final Logger logger = LoggerFactory.getLogger(SensorAlertService.class);

    private final SensorAlertRepository sensorAlertRepository;

    public SensorAlertService(SensorAlertRepository sensorAlertRepository) {
        this.sensorAlertRepository = sensorAlertRepository;
    }

    public void handleAlert(LogDataModel logDataModel) {
        sensorAlertRepository.findById(new SensorId(logDataModel.getSensorId()))
                .ifPresentOrElse(
                        sensorAlert -> {

                            if (sensorAlert.getMaxTemperature() != null
                                    && logDataModel.getValue().compareTo(sensorAlert.getMaxTemperature()) >= 0) {
                                logger.info("Alert MAX Temp.: SensorID: {}, Temp: {}",
                                        logDataModel.getSensorId(), logDataModel.getValue());

                            } else if (sensorAlert.getMinTemperature() != null
                                    && logDataModel.getValue().compareTo(sensorAlert.getMinTemperature()) <= 0) {
                                logger.info("Alert MIN Temp.: SensorID: {}, Temp: {}",
                                        logDataModel.getSensorId(), logDataModel.getValue());

                            } else {
                                logIgnoreAlert(logDataModel);
                            }
                        },
                        () -> logIgnoreAlert(logDataModel));

    }

    private void logIgnoreAlert(LogDataModel logDataModel) {

        logger.info("Alert IGNORED: SensorID: {}, with temp: {}",
                logDataModel.getSensorId(), logDataModel.getValue());
    }
}
