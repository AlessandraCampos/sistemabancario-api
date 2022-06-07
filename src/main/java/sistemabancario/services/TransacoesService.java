package sistemabancario.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sistemabancario.TipoTransacaoEnum;
import sistemabancario.config.KafkaProducer;
import sistemabancario.dtos.TransacoesDTo;
import sistemabancario.dtos.TransferenciaDto;
import sistemabancario.entities.Conta;
import sistemabancario.entities.Transacoes;
import sistemabancario.entities.Usuario;
import sistemabancario.exceptions.BancoException;
import sistemabancario.exceptions.DadosInconsistentesException;
import sistemabancario.exceptions.EntityNotFoundException;
import sistemabancario.repositories.TransacoesRepository;

@Service
public class TransacoesService {

	private TransacoesRepository transacoesRepository;
	private ContaService contaService;
	private UsuarioService usuarioService;
	private KafkaProducer kafkaproducer;

	public TransacoesService(TransacoesRepository transacoesRepository, ContaService contaService,
			TipoTransacaoService tipoTransacaoService, UsuarioService usuarioService,
			KafkaProducer kafkaproducer) {
		this.transacoesRepository = transacoesRepository;
		this.contaService = contaService;
		this.usuarioService = usuarioService;
		this.kafkaproducer = kafkaproducer;
	}

	public Transacoes novaTransacao(Transacoes transacoes) throws Exception {
		if (transacoes.getTipotransacaoId() == 0 ||
				transacoes.getNconta() == 0 || 
				transacoes.getValor() ==0) {
			throw new DadosInconsistentesException("Insira o tipo de transação");
		}
		
		Conta contaUser = contaService.findByNumeroConta(transacoes.getNconta());
		Transacoes novaT = new Transacoes();
		if (transacoes.getTipotransacaoId() == TipoTransacaoEnum.SAQUE.setTipo(1)) {
			calculaSaque(contaUser,transacoes);
			novaT.setNconta(contaUser.getNumconta());
			novaT.setTipotransacao_id(1);
			novaT.setValor(transacoes.getValor());
			novaT.setData(LocalDateTime.now());
			

		}

		else if (transacoes.getTipotransacaoId()==TipoTransacaoEnum.DEPOSITO.setTipo(2)) {
			calculaDeposito(contaUser,transacoes);
			novaT.setNconta(contaUser.getNumconta());
			novaT.setTipotransacao_id(2);
			novaT.setValor(transacoes.getValor());
			novaT.setData(LocalDateTime.now());
			
			

		}
		TransacoesDTo novaTransacaoDTO = TransacoesDTo.converterToDto(novaT);
        if (transacoes.getData()== null) {
        	
		kafkaproducer.adicionarEventoTransacao("testejava", novaTransacaoDTO);
        	
		}
		return novaT;

	}

	public Conta calculaSaque(Conta contaUser, Transacoes transacoes) throws BancoException {
		if (contaUser.getSaldo() < transacoes.getValor()) {

			throw new BancoException("Não é possivel sacar valor maior que o saldo disponível ");
		} else {
			Float valorSaque = contaUser.getSaldo() - transacoes.getValor();
			contaUser.setSaldo(valorSaque);
	
		}
		return contaUser;

	}
	
	

	public Conta calculaDeposito(Conta contaUser, Transacoes transacoes) throws BancoException {
		if (contaUser == null) {
			throw new BancoException("Conta " + contaUser + " não encontrada");
		} else {

			Float valorDeposito = contaUser.getSaldo() + transacoes.getValor();
			 contaUser.setSaldo(valorDeposito);
			//transacoes.setData(LocalDateTime.now());

		}
		return contaUser;
	}

	public TransferenciaDto calculaTransferencia(TransferenciaDto transferenciaDto) throws Exception {

		Usuario dadosUsuario = usuarioService.findByNumConta(transferenciaDto.getUsuarioOrigem());
		Usuario dadosUsuarioDestino = usuarioService.findByNumConta(transferenciaDto.getUsuarioDestino());
		Conta contaUser = contaService.findByNumeroConta(dadosUsuario.getNumconta());
		Conta contaUser2 = contaService.findByNumeroConta(dadosUsuarioDestino.getNumconta());

		Transacoes transferenciaOrigem = new Transacoes();
		transferenciaOrigem.setNconta(contaUser.getNumconta());
		transferenciaOrigem.setTipotransacao_id(3);
		transferenciaOrigem.setValor(transferenciaDto.getValor());
		transferenciaOrigem.setData(LocalDateTime.now());

		Transacoes transferenciaDestino = new Transacoes();
		transferenciaDestino.setNconta(contaUser2.getNumconta());
		transferenciaDestino.setTipotransacao_id(3);
		transferenciaDestino.setValor(transferenciaDto.getValor());
		transferenciaDestino.setData(LocalDateTime.now());
	

	//	transacoesRepository.save(transOrigem);
		calculaSaque(contaUser, transferenciaOrigem);

		//transacoesRepository.save(transDestino);
		calculaDeposito(contaUser2, transferenciaDestino);

		TransferenciaDto novaTransferenciaDTO = new TransferenciaDto();
		novaTransferenciaDTO.setUsuarioOrigem(dadosUsuario.getNumconta());
		novaTransferenciaDTO.setUsuarioDestino(dadosUsuarioDestino.getNumconta());
		novaTransferenciaDTO.setValor(transferenciaDto.getValor());
		
		if (transferenciaDto.getData()== null) {
			
			novaTransferenciaDTO.setData(LocalDateTime.now());
			kafkaproducer.adicionarEventoTransferencia("testejava", novaTransferenciaDTO);
		}else {
			novaTransferenciaDTO.setData(LocalDateTime.now());
		}
			
			
		

		return novaTransferenciaDTO;

	}
	
	public TransferenciaDto registroTransferencia(TransferenciaDto transferenciaDto) throws Exception {
		
		
		return transferenciaDto;
		
	}
	

//	public Page<Transacoes> buscarTodasTransacoes(int nconta, Pageable pageable) throws EntityNotFoundException {
//
//		return (Page<Transacoes>) transacoesRepository.findBynconta(nconta, pageable);
//
//	}

}
