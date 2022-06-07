package sistemabancario.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemabancario.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	Optional<Conta> findBynumconta(int numeroConta);


}
