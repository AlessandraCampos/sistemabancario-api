package sistemabancario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemabancario.entities.Conta;
import sistemabancario.entities.Usuario;
import sistemabancario.exceptions.BancoException;
import sistemabancario.exceptions.EntityNotFoundException;
import sistemabancario.repositories.ContaRepository;
import sistemabancario.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private ContaService contaService;

	public UsuarioService(UsuarioRepository usuarioRepository, ContaService contaService) {
		this.usuarioRepository = usuarioRepository;
		this.contaService = contaService;
	}

	public Usuario salvarUsuario(Usuario usuario) throws EntityNotFoundException {
		Boolean encontrarUsuario = buscarByCPF(usuario.getCpf());
		if(encontrarUsuario) {
			
		}

		return usuarioRepository.save(usuario);

	}

	public Usuario findById(Long id) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
	}

	public Usuario findByCPF(String cpf) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findBycpf(cpf);
		return usuario.orElseThrow(() -> new EntityNotFoundException("CPF não encontrado"));

	}
	
	public Boolean buscarByCPF(String cpf) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findBycpf(cpf);
		if (usuario.isPresent()) {
			return true;
		}
		return false ;

	}

	public Usuario findByNome(String nome) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
		return usuario.orElseThrow(() -> new EntityNotFoundException("Nome não encontrado"));

	}
	
	public Usuario findByNumConta(int conta) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findBynumconta(conta);
		if(usuario.isEmpty()) {
			return usuario.orElse(null);
		}
		return usuario.get() ;
				

	}
	
	public Boolean buscarByConta(int conta) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findBynumconta(conta);
		if (usuario.isPresent()) {
			return true;
		}
		return false ;
	}

}
