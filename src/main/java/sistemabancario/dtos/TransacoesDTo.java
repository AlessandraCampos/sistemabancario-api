package sistemabancario.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import sistemabancario.entities.Transacoes;

public class TransacoesDTo {
	private int id;
	private int conta;
	private float valor;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime data;
	
	private int transacaoId;
	
	public TransacoesDTo(int conta, int tipoTransacao,float valor, LocalDateTime data) {
		this.conta = conta;
		this.transacaoId = tipoTransacao;
		this.valor = valor;
		this.data = data;
	}
	public TransacoesDTo(int id, int conta, float valor, LocalDateTime data, int transacaoId) {
		this.id = id;
		this.conta = conta;
		this.valor = valor;
		this.data = data;
		this.transacaoId = transacaoId;
	}
	public TransacoesDTo() {
	
	}
	public static TransacoesDTo converterToDto(Transacoes transacao ) {
		TransacoesDTo transacaoDTO = new TransacoesDTo();
		transacaoDTO.setConta(transacao.getNconta()); 
		transacaoDTO.setTransacaoId(transacao.getTipotransacaoId());
		transacaoDTO.setValor(transacao.getValor());
		transacaoDTO.setData(transacao.getData());
		return transacaoDTO;
	}
	
	public Transacoes converterToEntity(TransacoesDTo transacao ) {
		Transacoes novaTransacao = new Transacoes();
		novaTransacao.setId((long)transacao.getId());
		novaTransacao.setNconta(transacao.getConta());
		novaTransacao.setTipotransacao_id(transacao.getTransacaoId());
		novaTransacao.setValor(transacao.getValor());
		novaTransacao.setData(transacao.getData());
		return novaTransacao;
				
			
		}
	public int getTransacaoId() {
		return transacaoId;
	}
	public void setTransacaoId(int transacaoId) {
		this.transacaoId = transacaoId;
	}
	
	public LocalDateTime getData() {
		return data;
	}


	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TransacoesDTo [conta=" + conta + ", valor=" + valor + ", data=" + data + ", transacaoId=" + transacaoId
				+ "]";
	}


}
