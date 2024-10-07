package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessage(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("kafka_demo_new", message);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Message sent "+  message +"with offset"+result.getRecordMetadata().offset());
            }else{
                System.out.println("Unable to set messgae "+ ex.getMessage());
            }
        });
    }
}
