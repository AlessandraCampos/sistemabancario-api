package sistemabancario.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;

@JacksonStdImpl
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Entity
@Table(name = "tb_historicotransacoes")
public class Transacoes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty()
	@Column(name = "nconta")
	private int conta;

	public int getNconta() {
		return conta;
	}

	public void setNconta(int conta) {
		this.conta = conta;
	}

	@JsonProperty()
	@Column(name = "tipotransacao_id")
	private int transacaoId;

	@Column(name = "valor")
	private float valor = (float) 0.00;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "data")
	private LocalDateTime data;

	public Transacoes(int transacaoId, int nconta, float valor) {
		this.conta = nconta;
		this.transacaoId = transacaoId;
		this.valor = valor;
	}

	public Transacoes(int transacaoId, int conta, float valor, LocalDateTime data) {
		this.conta = conta;
		this.transacaoId = transacaoId;
		this.valor = valor;
		this.data = data;
	}

	public Transacoes() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipotransacaoId() {
		return transacaoId;
	}

	public void setTipotransacao_id(int transacaoId) {
		this.transacaoId = transacaoId;
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

	@Override
	public String toString() {
		return "Transacoes [id=" + id + ", nconta=" + conta + ", tipotransacao_id=" + transacaoId + ", valor="
				+ valor + ", data=" + data + "]";
	}

}