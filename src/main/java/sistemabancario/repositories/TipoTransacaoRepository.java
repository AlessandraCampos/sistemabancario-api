package sistemabancario.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import sistemabancario.entities.TipoTransacao;

public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao,Long> {

}
