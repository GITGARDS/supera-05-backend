package br.com.banco.controllers;

import org.springframework.data.domain.Page;

import br.com.banco.entities.Transferencia;

public class TransferenciaRetorno {

	private Double saldoTotal;
	private Double saldoNoPeriodo;
	private Page<Transferencia> page;

	public Double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public Double getSaldoNoPeriodo() {
		return saldoNoPeriodo;
	}

	public void setSaldoNoPeriodo(Double saldoNoPeriodo) {
		this.saldoNoPeriodo = saldoNoPeriodo;
	}

	public Page<Transferencia> getPage() {
		return page;
	}

	public void setPage(Page<Transferencia> page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return String.format("TransferenciaRetorno [saldoTotal=%s, saldoNoPeriodo=%s, page=%s]", saldoTotal,
				saldoNoPeriodo, page);
	}

}
