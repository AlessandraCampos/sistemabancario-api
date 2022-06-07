package sistemabancario.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_conta")
public class Conta {

	@Id
	@Column(name = "numconta")
	private int numconta;

	@Column(name = "agencia")
	private int agencia;

	@Column(name = "tipoconta_id")
	private Long tipoConta;

	@Column(name = "saldo")
	private float saldo = (float) 0.00;

	public Conta() {
	}

	public Conta(int numconta, int agencia, Long tipoConta) {
		this.numconta = numconta;
		this.agencia = agencia;
		this.tipoConta = tipoConta;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public Long getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Long tipoConta) {
		this.tipoConta = tipoConta;
	}

	public float getSaldo() {
		return saldo;
	}

	public Float setSaldo(float saldo) {
		return this.saldo = saldo;
	}

	public int getNumconta() {
		return numconta;
	}

	public int setNumconta(int numconta) {
		return this.numconta = numconta;
	}

}
