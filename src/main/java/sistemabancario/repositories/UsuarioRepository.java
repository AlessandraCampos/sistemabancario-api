package sistemabancario.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemabancario.entities.Conta;
import sistemabancario.entities.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findBycpf(String cpf);
	
	Optional<Usuario>findByNome(String nome);
	
	Optional<Usuario>findBynumconta(int conta);

}
