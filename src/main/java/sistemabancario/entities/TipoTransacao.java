package sistemabancario.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipotransacao")
public class TipoTransacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipotransacao")
	private String nometransacao;

	public TipoTransacao(Long id) {
		this.id = id;
	}

	public TipoTransacao() {

	}

	public Long getId() {
		return id;
	}

	public String getNometransacao() {
		return nometransacao;
	}

	public void setNometransacao(String nometransacao) {
		this.nometransacao = nometransacao;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
