package br.com.banco.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.banco.entities.Conta;

public class ContaUtils {
	
	public static Conta conta_novo() {
		Conta resp = new Conta();
		resp.setId_conta(1l);
		resp.setNome_responsavel("Conta");
		return resp;
	}		
	
	public static Conta conta_validade() {
		Conta resp = new Conta();
		resp.setId_conta(98l);
		resp.setNome_responsavel("Conta 98");
		return resp;		
	}	

	public static List<Conta> findall() {
		List<Conta> resp = new ArrayList<Conta>(List.of(ContaUtils.conta_novo()));				
		return resp;		
	}
	
}
