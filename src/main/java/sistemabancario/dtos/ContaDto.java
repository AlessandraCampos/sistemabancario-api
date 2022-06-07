package sistemabancario.dtos;

public class ContaDto {

	private int agencia;

	private Long tipoConta;

	private int numconta;

	private String nomeTipoConta;
	
	private float saldo;

	public ContaDto(int numconta, Long tipoConta,int agencia,String nomeTipoConta,float saldo) {
		this.numconta = numconta;
		this.tipoConta = tipoConta;
		this.agencia = agencia;
		this.nomeTipoConta = nomeTipoConta;
		this.saldo =saldo;
		
		
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public ContaDto(int numconta, Long tipoConta,int agencia,String nomeTipoConta) {
		this.numconta = numconta;
		this.tipoConta = tipoConta;
		this.agencia = agencia;
		this.nomeTipoConta = nomeTipoConta;
	}

	public ContaDto() {
		// TODO Auto-generated constructor stub
	}

	public String getNomeTipoConta() {
		return nomeTipoConta;
	}

	public void String(String nomeTipoConta) {
		this.nomeTipoConta = nomeTipoConta;
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

	public int getNumconta() {
		return numconta;
	}

	public void setNumconta(int numconta) {
		this.numconta = numconta;
	}

}
