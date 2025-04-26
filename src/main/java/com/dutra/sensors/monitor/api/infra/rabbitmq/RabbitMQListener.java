package com.dutra.sensors.monitor.api.infra.rabbitmq;

import com.dutra.sensors.monitor.api.infra.config.RabbitConfig;
import com.dutra.sensors.monitor.api.model.LogDataModel;
import com.dutra.sensors.monitor.domain.service.SensorAlertService;
import com.dutra.sensors.monitor.domain.service.TemperatureMonitoringService;
import io.hypersistence.tsid.TSID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    private final TemperatureMonitoringService service;
    private final SensorAlertService sensorAlertService;

    public RabbitMQListener(TemperatureMonitoringService service, SensorAlertService sensorAlertService) {
        this.service = service;
        this.sensorAlertService = sensorAlertService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_PROCESS_TEMPERATURE, concurrency = "2-3")
    public void handleMessageTemperature(@Payload LogDataModel  message, @Headers Map<String, Object> headers) {

        TSID sensorId = message.getSensorId();
        Double temperature = message.getValue();

        if (temperature == 10) {
            throw new RuntimeException("Test exception");
        }

        service.processTemperatureReading(message);
        logger.info("Received message from sensor {} with new temperature {}", sensorId, temperature);
        logger.info("Message headers: {}", headers);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_ALERTING, concurrency = "2-3")
    public void handleAlerting(@Payload LogDataModel  message) {

        TSID sensorId = message.getSensorId();
        Double temperature = message.getValue();

        sensorAlertService.handleAlert(message);
        logger.info("Received ALERT from sensor {} with new temperature {}", sensorId, temperature);

    }
}
