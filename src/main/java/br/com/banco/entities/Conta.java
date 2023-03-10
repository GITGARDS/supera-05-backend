package br.com.banco.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_conta;

	@Column(nullable = false)
	private String nome_responsavel;

	public long getId_conta() {
		return id_conta;
	}

	public void setId_conta(long id_conta) {
		this.id_conta = id_conta;
	}

	public String getNome_responsavel() {
		return nome_responsavel;
	}

	public void setNome_responsavel(String nome_responsavel) {
		this.nome_responsavel = nome_responsavel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_conta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return id_conta == other.id_conta;
	}

	@Override
	public String toString() {
		return String.format("Conta [id_conta=%s, nome_responsavel=%s]", id_conta, nome_responsavel);
	}

}
