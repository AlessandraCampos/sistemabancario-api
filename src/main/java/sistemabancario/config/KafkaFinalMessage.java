package sistemabancario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaFinalMessage {
	
	@Autowired
	static KafkaProducer kafkaproducer;

	public KafkaFinalMessage(KafkaProducer kafkaproducer) {
		this.kafkaproducer = kafkaproducer;
	}
	
	 static void KafkaFinalMessageSaque() {
		kafkaproducer.adicionarEventoSucesso("teste", "Saque Efetuado");
	}
	 static void KafkaFinalMessageDeposito() {
			kafkaproducer.adicionarEventoSucesso("teste", "Deposito Efetuado");
	}
	 static void KafkaFinalMessageTransferencia() {
			kafkaproducer.adicionarEventoSucesso("teste", "Transferencia Efetuada");
	}
}


