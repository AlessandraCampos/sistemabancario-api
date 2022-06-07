package sistemabancario.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemabancario.dtos.UsuarioDTO;
import sistemabancario.entities.Usuario;
import sistemabancario.exceptions.BancoException;
import sistemabancario.exceptions.DadosInconsistentesException;
import sistemabancario.services.ContaService;
import sistemabancario.services.UsuarioService;


@CrossOrigin()
@RestController
@RequestMapping()
public class UsuarioController {

	@Autowired
	private final UsuarioService usuarioService;

	private final ContaService contaService;

	public UsuarioController(UsuarioService usuarioService, ContaService contaService) {
		this.usuarioService = usuarioService;
		this.contaService = contaService;
	}

	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioDTO> novoUsuario(@Valid @RequestBody  Usuario usuario)
			throws DadosInconsistentesException, BancoException {
		if (usuario.getCpf() != null || usuario.getCpf() != "") {
				Boolean userCPF = usuarioService.buscarByCPF(usuario.getCpf());
				if (userCPF) {
				throw new BancoException("Este CPF já pertence a outro usuário");
			     } else {
						if (usuario.getNumconta() != 0) 
						{
							Boolean numConta = contaService.buscarNumeroConta(usuario.getNumconta());
							Boolean buscarByConta =	usuarioService.buscarByConta(usuario.getNumconta());
						
							if (numConta && buscarByConta){
								throw new BancoException("Esta Conta já pertence a outro usuário");
							}
						{		     
								
					     }
			      usuarioService.salvarUsuario(usuario);
				        }
			         }
		}else {
			throw new DadosInconsistentesException("Verifique os dados e tente novamente");
		}

		return ResponseEntity.ok(UsuarioDTO.ConverterTo(usuario));

	}

	@PutMapping("/usuarios")
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody Usuario usuario) throws Exception {

		usuarioService.salvarUsuario(usuario);

		return ResponseEntity.ok(UsuarioDTO.ConverterTo(usuario));

	}

	@GetMapping("/usuarios/{nome}")
	public ResponseEntity<UsuarioDTO> BuscarUsuario(@PathVariable String nome) throws Exception {
		Usuario user = usuarioService.findByNome(nome);
		return ResponseEntity.ok(UsuarioDTO.ConverterTo(user));

	}

}
