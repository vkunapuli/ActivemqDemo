package com.sssystems.activemqdemo.listener;


import com.sssystems.activemqdemo.messge.SampleMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class ActivemqListener implements MessageListener {
    @Value("${spring.activemq.broker-url}")
      String BROKER_URL;
    private final String queueName;
    private final String topicName;
    ActivemqListener(@Value("${tpd.queue-name}") final String queueName,
                     @Value("${tpd.topic-name}") final String topicName) {
        this.queueName = queueName;
        this.topicName = topicName;

        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            factory.setTrustAllPackages(true);
            factory.setBrokerURL("tcp://localhost:61616");
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


            Destination topicDestination = session.createTopic(topicName);
            MessageConsumer topicConsumer = session.createConsumer(topicDestination);
            topicConsumer.setMessageListener(this);

            Destination queueDestination = session.createQueue(queueName);
            MessageConsumer queueConsumer = session.createConsumer(queueDestination);
            queueConsumer.setMessageListener(this);


        } catch (JMSException e) {
            System.out.println("error: "+ e.getStackTrace());
        }
    }
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage object = (ObjectMessage) message;
                SampleMessage sampleMessage = (SampleMessage) object.getObject();
                System.out.println(sampleMessage.toString());
            }
        }catch (Exception e) {
                System.out.println("error" + e.getMessage());
        }
    }
}
