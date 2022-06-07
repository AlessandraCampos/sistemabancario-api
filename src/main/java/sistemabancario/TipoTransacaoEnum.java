package sistemabancario;

public enum TipoTransacaoEnum {
	
	SAQUE(1), DEPOSITO(2), TRANSFERENCIA(3);
	
	
	private int tipo;
	
	TipoTransacaoEnum(int tipo) {
		tipo = tipo;
	}
	public int getTipo() {
		return tipo;
	}
	public int setTipo(int tipo) {
		return this.tipo = tipo;
	}

}
