package sistemabancario.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@CPF
	@NotBlank(message="O campo CPF é de preenchimento Obrigatório")
	@Column(unique = true, nullable = false)
	private String cpf;
	
	@Column(name="contato")
	private String contato;
	
	
	
   
	@JoinColumn(name="numconta",unique = true ,nullable=true)
	private int numconta;
	
	public Usuario(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public int getNumconta() {
		return numconta;
	}

	public void setNumconta(int numconta) {
		this.numconta = numconta;
	}

	public Usuario() {
		
	} 
	
	public Usuario(String nome, String cpf, String contato, int numconta) {
		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
		this.numconta = numconta;
	}

	public Usuario(String nome, String cpf, String contato) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
	}
	
	


	
	

}
