package br.com.banco.controllers;

import org.springframework.data.domain.Page;

import br.com.banco.entities.Transferencia;

public class TransferenciaRetorno {

	private double saldoTotal;
	private double saldoNoPeriodo;
	private Page<Transferencia> page;

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public double getSaldoNoPeriodo() {
		return saldoNoPeriodo;
	}

	public void setSaldoNoPeriodo(double saldoNoPeriodo) {
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
		return String.format("RetornoTransferenciaDto [saldoTotal=%s, saldoNoPeriodo=%s, page=%s]", saldoTotal,
				saldoNoPeriodo, page);
	}

}
