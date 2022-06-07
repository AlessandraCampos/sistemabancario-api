package sistemabancario.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sistemabancario.dtos.TransacoesDTo;
import sistemabancario.entities.Transacoes;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long>{
	
	//Optional<Transacoes> findBynconta(int nconta);
	
//	@Query("select u from Transacoes u where u.nconta = 1 ORDER BY data ASC")
//	public Page <Transacoes> findBynconta(int nconta,Pageable pageable);

	public void save(TransacoesDTo transacaoDTO);	
	
	

}
