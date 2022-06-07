package sistemabancario.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import sistemabancario.TipoTransacaoEnum;
import sistemabancario.dtos.TransacoesDTo;
import sistemabancario.dtos.TransferenciaDto;
import sistemabancario.entities.Conta;
import sistemabancario.entities.Transacoes;
import sistemabancario.repositories.TransacoesRepository;
import sistemabancario.services.ContaService;
import sistemabancario.services.TransacoesService;

@Slf4j
@Service
public class KafkaConsumer {

	@Autowired
	TransacoesRepository transacaoRepository;
	@Autowired
	TransacoesService transacoesService;

	@Autowired
	private ContaService contaService;

	@SuppressWarnings("null")
	@KafkaListener(topics = "testejava", groupId = "Transacoes")
	public void consume(ConsumerRecord<String, Transacoes> payload) throws Exception {
		
		log.info("Tópico: {}", "testejava");
		log.info("key: {}", payload.key());
		log.info("Headers: {}", payload.headers());
		log.info("Partion: {}", payload.partition());
		log.info("Order: {}", payload.value());
		
		Object strDados = payload.value();
		
		ObjectMapper mapper = new ObjectMapper();
		Object payloadObjeto = mapper.readValue(strDados.toString(), Object.class);
		Transacoes transacaoSave = new Transacoes();
		
		if(strDados.toString().contains("usuarioOrigem")){
			
			TransferenciaDto transferencia = mapper.convertValue(payloadObjeto, TransferenciaDto.class);
			
			transacaoSave.setTipotransacao_id(3);
			transacaoSave.setNconta(transferencia.getUsuarioOrigem());
			transacaoSave.setValor(transferencia.getValor());
			transacaoSave.setData(transferencia.getData());
			
			transacaoRepository.save(transacaoSave);
			Conta contaUsuarioOrigem  = contaService.findByNumeroConta(transferencia.getUsuarioOrigem());
			transacoesService.calculaSaque(contaUsuarioOrigem, transacaoSave);
			contaService.updateConta(contaUsuarioOrigem);
			
			Conta contaUsuarioDestino  = contaService.findByNumeroConta(transferencia.getUsuarioDestino());
			transacoesService.calculaDeposito(contaUsuarioDestino, transacaoSave);
			contaService.updateConta(contaUsuarioDestino);
			
			KafkaFinalMessage.KafkaFinalMessageTransferencia();
			
		}else if(strDados.toString().contains("conta")){
			TransacoesDTo transacao = mapper.convertValue(payloadObjeto, TransacoesDTo.class);
			
		
			try {
				
				if(transacao.getTransacaoId()  == TipoTransacaoEnum.SAQUE.setTipo(1)) {
					Conta contaUsuario  = contaService.findByNumeroConta(transacao.getConta());
					transacoesService.calculaSaque(contaUsuario, transacao.converterToEntity(transacao));
					contaService.updateConta(contaUsuario);
					KafkaFinalMessage.KafkaFinalMessageSaque();
					
				}else if (transacao.getTransacaoId()  == TipoTransacaoEnum.DEPOSITO.setTipo(2)) {
					Conta contaUsuario  = contaService.findByNumeroConta(transacao.getConta());
					transacoesService.calculaDeposito(contaUsuario, transacao.converterToEntity(transacao));
					contaService.updateConta(contaUsuario);
					KafkaFinalMessage.KafkaFinalMessageDeposito();
					
				}
				transacaoRepository.save(transacao.converterToEntity(transacao));
				
 
		} catch (Exception e) {

			e.printStackTrace();
		}
			
		}



}

}
