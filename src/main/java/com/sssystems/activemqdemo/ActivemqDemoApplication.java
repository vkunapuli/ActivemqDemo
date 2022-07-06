package com.sssystems.activemqdemo;

//import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ActivemqDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ActivemqDemoApplication.class, args);
        JmsTemplate jms = ctx.getBean(JmsTemplate.class);
        //jms.convertAndSend("activemq-demo1", "test message");
        //jms.convertAndSend("test message");
    }
}
