package sistemabancario.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import sistemabancario.dtos.TransferenciaDto;

@Service
public class KafkaProducer {
	
	private final KafkaTemplate<Object, Object> template;

	public KafkaProducer(KafkaTemplate<Object, Object> template) {
		this.template = template;
	}
	
	public<Transacoes> void adicionarEventoTransacao(String testejava,Transacoes transacao) {
		template.send(testejava,transacao);
	}
	@SuppressWarnings("hiding")
	public<TransferenciaDto> void adicionarEventoTransferencia(String testejava,TransferenciaDto transferencia) {
		template.send(testejava,transferencia);
	}
	@SuppressWarnings("hiding")
	public<Object> void adicionarEventoSucesso(String teste,String mensagem) {
		template.send(teste,mensagem);
	}

}
