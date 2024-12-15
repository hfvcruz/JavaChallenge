package com.example.javachallenge.rest.producer;

import com.example.javachallenge.model.CalculatorDTO;
import java.util.concurrent.ExecutionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Service
public class CalculatorRequestProducer {

    @Value("${kafka.topic.request-topic}")
	String requestTopic;
	
	@Value("${kafka.topic.requestreply-topic}")
	String requestReplyTopic;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReplyingKafkaTemplate<String, CalculatorDTO, CalculatorDTO> kafkaTemplate;

    public CalculatorDTO sendMessage(CalculatorDTO request) {
        try {
            // Create producer record.
            var record = new ProducerRecord<String, CalculatorDTO>(requestTopic, request);

            // Set reply topic in header.
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

            // Post in kafka topic.
            RequestReplyFuture<String, CalculatorDTO, CalculatorDTO> sendAndReceive = kafkaTemplate.sendAndReceive(record);

            System.out.println("Calculation sent to processing...");
            System.out.println("A=" + request.getInputA().toString() + 
                               " B=" + request.getInputB().toString() + 
                               " OperationType=" + request.getOperationType().toString());

            // confirm if producer produced successfully
            SendResult<String, CalculatorDTO> sendResult = sendAndReceive.getSendFuture().get();

            //print all headers
            sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

            // get consumer record
            ConsumerRecord<String, CalculatorDTO> consumerRecord = sendAndReceive.get();
            // return consumer value
            return consumerRecord.value();		

            // String conteudo = objectMapper.writeValueAsString(request);
            // kafkaTemplate.send(topicCalculatorRequest, conteudo);
            // return "Calculation sent to processing";

        } catch (Exception e) {
            System.out.println("Error while sending message " + e.getMessage());
            return null;
        }
    }
}