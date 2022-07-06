package com.sssystems.activemqdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sssystems.activemqdemo.messge.SampleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
public class ActivemqController {
    private static final Logger logger =
            LoggerFactory.getLogger(ActivemqController.class);
    private final JmsTemplate template;
    private final String queueName;
    private final int messagesPerRequest;
  public ActivemqController (
    final JmsTemplate template,
    //final KafkaConsumerListener listener,
    @Value("${tpd.queue-name}") final String queueName,
    @Value("${tpd.messages-per-request}") final int messagesPerRequest) {
        this.template = template;
        //this.listener = listener;
        this.queueName = queueName;
        this.messagesPerRequest = messagesPerRequest;
    }
    @GetMapping("/sendMessage")
    public String hello() throws Exception {
        IntStream.range(0, messagesPerRequest)
                .forEach(i -> {
                    SampleMessage message = new SampleMessage("Test message-"+i, i);
                    try{
                      String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(message);
                      this.template.send(queueName,
                              messageCreator -> {
                                  TextMessage msg = messageCreator.createTextMessage();
                                  msg.setText(jsonObj);
                                  return msg;
                              });
                    }catch(JsonProcessingException e) { }
        });
        //listener.getLatch().await(60, TimeUnit.SECONDS);
        //logger.info("All messages received");
        return "All messages send!";
    }
}
