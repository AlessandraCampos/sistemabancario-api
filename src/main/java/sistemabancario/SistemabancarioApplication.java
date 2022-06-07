package sistemabancario;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@EnableKafka
@SpringBootApplication
public class SistemabancarioApplication {
	
	@Autowired
	 private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(SistemabancarioApplication.class, args);
	}
	
	@PostConstruct
	  public void setUp() {
	  objectMapper.registerModule(new JavaTimeModule());
	  }

}
