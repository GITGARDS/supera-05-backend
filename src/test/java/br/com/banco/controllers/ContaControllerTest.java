package br.com.banco.controllers;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;
import br.com.banco.utils.ContaUtils;

@ExtendWith(SpringExtension.class)
class ContaControllerTest {

	@InjectMocks
	private ContaController controller;

	@Mock
	private ContaService service;

	@BeforeEach
	void setup() {

		BDDMockito.when(this.service.findall()).thenReturn(ContaUtils.findall());
		BDDMockito.when(this.service.findbyid(ArgumentMatchers.anyLong())).thenReturn(ContaUtils.conta_novo());

	}

	@Test
	void testFindall() {
		List<Conta> lista = ContaUtils.findall();
		List<Conta> expectedName = this.service.findall();
		Assertions.assertThat(lista).isNotNull();
		Assertions.assertThat(lista.get(0).getNome_responsavel()).isEqualTo(expectedName.get(0).getNome_responsavel());

	}

	@Test
	void testFindbyid() {
		Long expectedId = ContaUtils.conta_validade().getId_conta();
		Conta conta = this.controller.findbyid(1l).getBody();
		Assertions.assertThat(conta).isNotNull();
		Assertions.assertThat(conta.getId_conta()).isNotNull().isEqualTo(expectedId);
	}

}
