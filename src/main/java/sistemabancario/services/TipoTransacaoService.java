package sistemabancario.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import sistemabancario.entities.TipoTransacao;
import sistemabancario.exceptions.EntityNotFoundException;
import sistemabancario.repositories.TipoTransacaoRepository;


@Service
public class TipoTransacaoService {
	
	private TipoTransacaoRepository tipoTransacaoRepository;

	public TipoTransacaoService(TipoTransacaoRepository tipoTransacaoRepository) {
		this.tipoTransacaoRepository = tipoTransacaoRepository;
		
	}
	
	public TipoTransacao findById(Long id) throws EntityNotFoundException {
		
		Optional<TipoTransacao> transacaoId=  tipoTransacaoRepository.findById(id);
		return transacaoId.orElseThrow (()-> new EntityNotFoundException("Tipo de Transação não encontrado"));
	
	}

}
