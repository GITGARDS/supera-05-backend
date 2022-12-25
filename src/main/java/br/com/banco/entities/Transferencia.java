package br.com.banco.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate data_transferencia;

	@Column(nullable = false)
	private Double valor;

	@Column(nullable = false)
	private String tipo;

	@Column(nullable = false)
	private String nome_operador_transacao;

	@ManyToOne
	@JoinColumn(name = "conta_id", nullable = false)
	private Conta conta_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getData_transferencia() {
		return data_transferencia;
	}

	public void setData_transferencia(LocalDate data_transferencia) {
		this.data_transferencia = data_transferencia;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome_operador_transacao() {
		return nome_operador_transacao;
	}

	public void setNome_operador_transacao(String nome_operador_transacao) {
		this.nome_operador_transacao = nome_operador_transacao;
	}

	public Conta getConta_id() {
		return conta_id;
	}

	public void setConta_id(Conta conta_id) {
		this.conta_id = conta_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format(
				"Transferencia [id=%s, data_transferencia=%s, valor=%.f2, tipo=%s, nome_operador_transacao=%s, conta_id=%s]",
				id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id);
	}

}
