package sistemabancario.controllers;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemabancario.config.KafkaProducer;
import sistemabancario.dtos.TransacoesDTo;
import sistemabancario.dtos.TransferenciaDto;
import sistemabancario.entities.Transacoes;
import sistemabancario.exceptions.EntityNotFoundException;
import sistemabancario.services.TransacoesService;

@CrossOrigin()
@RestController
@RequestMapping()
public class TransacoesController {

	private TransacoesService transacoesService;

	public TransacoesController(TransacoesService transacoesService) {
		this.transacoesService = transacoesService;
		
	}

	@PostMapping("/transacoes")
	public ResponseEntity<TransacoesDTo> novaTransacao(@RequestBody Transacoes transacoes) throws Exception {

		Transacoes transacao = transacoesService.novaTransacao(transacoes);
		TransacoesDTo novaTransacaoDTO = TransacoesDTo.converterToDto(transacao);
		return ResponseEntity.ok(novaTransacaoDTO);

	}

	@PostMapping("/transferencias")
	public ResponseEntity<TransferenciaDto> transferencia(@RequestBody TransferenciaDto transferenciaDto)
			throws Exception {

		TransferenciaDto tranferencia  = transacoesService.calculaTransferencia(transferenciaDto);
		
		
		
		return ResponseEntity.ok(tranferencia);

	}

//	@GetMapping("/transacoes/{nconta}")
//	public ResponseEntity<Page<TransacoesDTo>> buscarTransferencia(@PageableDefault Pageable pageable,
//			@PathVariable int nconta) throws EntityNotFoundException {
//
//		return ResponseEntity
//	//			.ok(transacoesService.buscarTodasTransacoes(nconta,pageable).map(TransacoesDTo::converterToDto));
//	}

}
