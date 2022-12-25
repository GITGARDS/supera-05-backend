package br.com.banco.utils;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageImpl;

import br.com.banco.controllers.TransferenciaRetorno;
import br.com.banco.entities.Transferencia;

public class TransferenciaUtils {
	
	
	public static Transferencia transferencia_novo(long id, String nome) {
		Transferencia resp = new Transferencia();
		resp.setConta_id(ContaUtils.conta_novo());
		resp.setData_transferencia(LocalDate.now());
		resp.setId(id);
		resp.setNome_operador_transacao(nome);
		resp.setTipo("CREDITO");
		resp.setValor(1.00);
		return resp;
	}
	
	public static Transferencia transferencia_validade() {
		Transferencia resp = new Transferencia();
		resp.setConta_id(ContaUtils.conta_novo());
		resp.setData_transferencia(LocalDate.now());
		resp.setId(98l);
		resp.setNome_operador_transacao("Teste 98l");
		resp.setTipo("CREDITO");
		resp.setValor(1.00);
		return resp;		
	}
	
//	
	
	public static TransferenciaRetorno transferenciaRetorno_validade() {
		TransferenciaRetorno resp = new TransferenciaRetorno();
		PageImpl<Transferencia> pageImpl = new PageImpl<>(
				List.of(TransferenciaUtils.transferencia_novo(1l, "nome 1")));		
		resp.setPage(pageImpl);
		resp.setSaldoTotal(100);
		resp.setSaldoNoPeriodo(10);		
		return resp;		
	}
	
	

}
