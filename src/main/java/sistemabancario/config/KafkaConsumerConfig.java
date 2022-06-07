package sistemabancario.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import sistemabancario.dtos.TransacoesDTo;
import sistemabancario.entities.Transacoes;

@Configuration
public class KafkaConsumerConfig {
	
	@Bean
	public ConsumerFactory<? super String, ? super TransacoesDTo> transfConsumerFactory(){
		
		Map<String, Object> props =new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"Transacoes");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LocalDateDeserializer.class);
		
		
		return new DefaultKafkaConsumerFactory<>(props);
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TransacoesDTo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(transfConsumerFactory());
		return factory;
	
	}

}
