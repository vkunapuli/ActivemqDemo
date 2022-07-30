package com.sssystems.activemqdemo.controller;

import com.sssystems.activemqdemo.messge.SampleMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;


@RestController
public class ActivemqController {
    private static final Logger logger =
            LoggerFactory.getLogger(ActivemqController.class);
    private final String queueName;
    private final String topicName;
    private final int messagesPerRequest;

    @Value("${spring.activemq.broker-url}")
    String BROKER_URL;
  public ActivemqController (
    @Value("${tpd.queue-name}") final String queueName,
    @Value("${tpd.topic-name}") final String topicName,
    @Value("${tpd.messages-per-request}") final int messagesPerRequest) {
        this.queueName = queueName;
        this.topicName = topicName;
        this.messagesPerRequest = messagesPerRequest;
    }
    @GetMapping("/sendMessage")
    public String hello(@RequestParam(defaultValue = "vkunapuli@gmail.com") String email,
                        @RequestParam(defaultValue = "hello world") String message,
                        @RequestParam(defaultValue = "topic") String type) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Destination destination;
        System.out.println("type:" + type);
        Connection connection = connectionFactory.createConnection("username", "password");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        if("topic".equalsIgnoreCase(type))
            destination=  session.createTopic(topicName);
        else
            destination = session.createQueue(queueName);
        MessageProducer producer  = session.createProducer(destination);
        SampleMessage sampleMessage= new SampleMessage(message, 100, email);
        ObjectMessage objMessage = session.createObjectMessage();
        objMessage.setObject( sampleMessage);
        objMessage.setStringProperty("JMSXGroupID", "service1");
        producer.send(objMessage);
        return "sent message";
    }
}
