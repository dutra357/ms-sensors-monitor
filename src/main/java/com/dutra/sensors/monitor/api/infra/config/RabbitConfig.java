package com.dutra.sensors.monitor.api.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_PROCESS_TEMPERATURE =  "temperature-monitoring.process-temperature.v1.q";
    public static final String DEAD_LETTER_QUEUE_PROCESS_TEMPERATURE =  "temperature-monitoring.process-temperature.v1.dlq";
    public static final String QUEUE_ALERTING =  "temperature-monitoring.alerting.v1.q";

    /**
     * Bean para converter o objeto de mensagem para JSON,
     * permitindo a desserialização correta do objeto.
     * Aplicável às mensagens recebidas pelo listener.
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queueProcessTemperature() {
        Map<String, Object> args = Map.of(
                "x-dead-letter-exchange", "",
                "x-dead-letter-routing-key", DEAD_LETTER_QUEUE_PROCESS_TEMPERATURE
        );

        return QueueBuilder.durable(QUEUE_PROCESS_TEMPERATURE)
                .withArguments(args)
                .build();
    }

    @Bean
    public Queue deadLetterQueueProcessTemperature() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_PROCESS_TEMPERATURE).build();
    }

    @Bean
    public Queue queueAlerting() {
        return QueueBuilder.durable(QUEUE_ALERTING).build();
    }

    @Bean
    public Binding bindingProcessTemperature() {
        return BindingBuilder.bind(queueProcessTemperature()).to(exchange());
    }

    @Bean
    public Binding bindingAlerting() {
        return BindingBuilder.bind(queueAlerting()).to(exchange());
    }

    /**
     * Bean removido para que o serviço que consome a fila
     * não acabe criando a exchange. Passa a ser apenas uma referência
     * à exchange para configuração do biding.
     */
    public FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange("temperature-processing.temperature-received.v1.e").build();
    }

}
