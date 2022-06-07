package sistemabancario.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import sistemabancario.entities.Transacoes;

public class TransferenciaDto {
	private int id;
	private int transacaoId;
	private int usuarioOrigem;
	private int usuarioDestino;
	private float valor;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime data;

	public TransferenciaDto(int id, int transacaoId, int usuarioOrigem, int usuarioDestino, float valor,
			LocalDateTime data) {
		this.id = id;
		this.transacaoId = transacaoId;
		this.usuarioOrigem = usuarioOrigem;
		this.usuarioDestino = usuarioDestino;
		this.valor = valor;
		this.data = data;
	}

	public TransferenciaDto() {
		
	}

	@Override
	public String toString() {
		return "TransferenciaDto [id=" + id + ", transacaoId=" + transacaoId + ", usuarioOrigem=" + usuarioOrigem
				+ ", usuarioDestino=" + usuarioDestino + ", valor=" + valor + ", data=" + data + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTransacaoId() {
		return transacaoId;
	}

	public void setTransacaoId(int transacaoId) {
		this.transacaoId = transacaoId;
	}

	public int getUsuarioOrigem() {
		return usuarioOrigem;
	}

	public void setUsuarioOrigem(int usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}

	public int getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(int usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}


}