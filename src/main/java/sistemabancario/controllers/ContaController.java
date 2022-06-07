package sistemabancario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemabancario.dtos.ContaDto;
import sistemabancario.entities.Conta;
import sistemabancario.exceptions.BancoException;
import sistemabancario.services.ContaService;

@CrossOrigin()
@RestController
@RequestMapping()
public class ContaController {

	private ContaService contaService;

	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

	@PostMapping("/contas")
	public ResponseEntity<ContaDto> novoUsuario(@RequestBody Conta conta) {
		String nomeTipo = "";
		Conta nConta = contaService.novaConta(conta);

		if (conta.getTipoConta() == 1) {
			nomeTipo = "Conta Poupança";
		} else {
			nomeTipo = "Conta Corrente";
		}
		ContaDto contaDto = new ContaDto(nConta.getNumconta(), nConta.getTipoConta(), nConta.getAgencia(), nomeTipo);
		return ResponseEntity.ok(contaDto);

	}
	
	@GetMapping("/contas/{num}")
	public ResponseEntity<ContaDto> buscarConta(@PathVariable int num) throws BancoException {
		Conta nConta = contaService.findByNumeroConta(num);
		String nomeTipo = "";
		if (nConta.getTipoConta() == 1) {
			nomeTipo = "Conta Poupança";
		} else {
			nomeTipo = "Conta Corrente";
		}
		ContaDto contaDto = new ContaDto(nConta.getNumconta(), nConta.getTipoConta(), nConta.getAgencia(), nomeTipo,nConta.getSaldo());
	 return ResponseEntity.ok(contaDto);
		
		
	}

}
