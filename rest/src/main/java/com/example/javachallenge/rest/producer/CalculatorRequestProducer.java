package com.example.javachallenge.rest.producer;

import com.example.javachallenge.model.CalculatorRequestDTO;
import com.example.javachallenge.model.CalculatorReplyDTO;

import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;

@Service
public class CalculatorRequestProducer {

    @Value("${kafka.topic.request-topic}")
	String requestTopic;
	
	@Value("${kafka.topic.requestreply-topic}")
	String requestReplyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, CalculatorRequestDTO, CalculatorReplyDTO> kafkaTemplate;

    public CalculatorReplyDTO requestCalculation(CalculatorRequestDTO request) {
        try {
            // Create producer record.
            var record = new ProducerRecord<String, CalculatorRequestDTO>(requestTopic, request);

            // Set reply topic in header.
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

            // Post in kafka topic.
            var sendAndReceive = kafkaTemplate.sendAndReceive(record);

            System.out.println("Calculation sent to processing...");
            
            // confirm if producer produced successfully
            var sendResult = sendAndReceive.getSendFuture().get();

            //print all headers
            sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

            // get consumer record
            var consumerRecord = sendAndReceive.get();
            // return consumer value
            return consumerRecord.value();		

        } catch (Exception e) {
            System.out.println("Error while sending message " + e.getMessage());
            return null;
        }
    }
}