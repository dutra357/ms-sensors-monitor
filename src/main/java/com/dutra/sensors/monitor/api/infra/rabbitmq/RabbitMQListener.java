package com.dutra.sensors.monitor.api.infra.rabbitmq;

import com.dutra.sensors.monitor.api.infra.config.RabbitConfig;
import com.dutra.sensors.monitor.api.model.LogDataModel;
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

    public RabbitMQListener(TemperatureMonitoringService service) {
        this.service = service;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMessage(@Payload LogDataModel  message, @Headers Map<String, Object> headers) {

        TSID sensorId = message.getSensorId();
        Double temperature = message.getValue();

        logger.info("Received message from sensor {} with new temperature {}", sensorId, temperature);
        logger.info("Message headers: {}", headers);

        service.processTemperatureReading(message);
    }
}
