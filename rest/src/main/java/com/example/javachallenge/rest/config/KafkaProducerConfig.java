package com.example.javachallenge.rest.config;

import com.example.javachallenge.model.CalculatorDTO;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
// import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${kafka.bootstrap-servers}")
	  private String bootstrapServers;
	  
    @Value("${kafka.topic.request-topic}")
	  String requestTopic;
	
    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;
	  
	  @Value("${kafka.consumergroup}")
	  private String consumerGroup;

    // @Bean
    // public ProducerFactory<String, String> producerFactory() {
    //     Map<String, Object> properties = kafkaProperties.buildProducerProperties(null);
    //     return new DefaultKafkaProducerFactory<>(properties);
    // }

    // @Bean
    // public KafkaTemplate<String, String> kafkaTemplate() {
    //     return new KafkaTemplate<>(producerFactory());
    // }

    @Bean
	public Map<String, Object> producerConfigs() {
	  Map<String, Object> props = new HashMap<>();
	  // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	  return props;
	}
	
	@Bean
	public Map<String, Object> consumerConfigs() {
	  Map<String, Object> props = new HashMap<>();
	  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
	  props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
	  return props;
	}

	@Bean
	public ProducerFactory<String,CalculatorDTO> producerFactory() {
	  return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	@Bean
	public KafkaTemplate<String, CalculatorDTO> kafkaTemplate() {
	  return new KafkaTemplate<>(producerFactory());
	}

    @Bean
    public ReplyingKafkaTemplate<String, CalculatorDTO, CalculatorDTO> replyKafkaTemplate(ProducerFactory<String, CalculatorDTO> pf, KafkaMessageListenerContainer<String, CalculatorDTO> container){
        return new ReplyingKafkaTemplate<>(pf, container);
    }

    @Bean
    public KafkaMessageListenerContainer<String, CalculatorDTO> replyContainer(ConsumerFactory<String, CalculatorDTO> cf) {
          ContainerProperties containerProperties = new ContainerProperties(requestReplyTopic);
          return new KafkaMessageListenerContainer<>(cf, containerProperties);
      }
    
    @Bean
    public ConsumerFactory<String, CalculatorDTO> consumerFactory() {
      return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),new JsonDeserializer<>(CalculatorDTO.class));
    }
    
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CalculatorDTO>> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, CalculatorDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());
      factory.setReplyTemplate(kafkaTemplate());
      return factory;
    }

    @Bean
    public NewTopic calculatorRequestTopicBuilder() {
        return TopicBuilder
            .name(requestTopic)
            .partitions(1)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic calculatorRequestReplyTopicBuilder() {
        return TopicBuilder
            .name(requestReplyTopic)
            .partitions(1)
            .replicas(1)
            .build();
    }
}