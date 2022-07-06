package com.sssystems.activemqdemo.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sssystems.activemqdemo.messge.SampleMessage;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ActivemqListener {
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);//must follow the configurer.configure(), because it will set the factory as default as connectionFactory
        factory.setConcurrency("1");
        factory.setSubscriptionShared(true);
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        String mqClientID = "serviceId";
        factory.setClientId(mqClientID);
        return factory;
    }
    @JmsListener(destination = "${tpd.queue-name}")
    @SendTo("myQueue2")
    public String receiveAndForwardMessageFromQueue(final Message jsonMessage) throws JMSException {
        String messageData;
        System.out.println("Received message " + jsonMessage);
        if(jsonMessage instanceof SampleMessage) {
            SampleMessage textMessage = (SampleMessage) jsonMessage;
            messageData = textMessage.toString();
            System.out.println("messageData:"+messageData);
        }
        else messageData = jsonMessage.toString();
        return messageData;
    }

    @JmsListener(destination = "${tpd.topic-name}")
    @SendTo("myNewTopic")
    public String receiveAndForwardMessageFromTopic(final Message jsonMessage) throws JMSException, JsonProcessingException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        if(jsonMessage instanceof SampleMessage) {
            SampleMessage textMessage = (SampleMessage)jsonMessage;
            messageData = textMessage.toString();
            System.out.println("messageData:"+messageData);
        }  else messageData = jsonMessage.toString();
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(messageData);
    }


    @JmsListener(destination = "${tpd.topic-name}")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message in 2nd topic " + jsonMessage);
        if(jsonMessage instanceof SampleMessage) {
            SampleMessage textMessage = (SampleMessage)jsonMessage;
            messageData = textMessage.toString();
            System.out.println("messageData in 2nd listener:"+messageData);
        }  else messageData = jsonMessage.toString();
    }

}
