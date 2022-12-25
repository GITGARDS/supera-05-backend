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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import br.com.banco.utils.TransferenciaUtils;

@ExtendWith(SpringExtension.class)
class TransferenciaControllerTest {

	@InjectMocks
	private TransferenciaController controller;

	@Mock
	private TransferenciaService service;

	@BeforeEach
	void setup() {

		TransferenciaRetorno retorno = TransferenciaUtils.transferenciaRetorno_validade();
		PageRequest page = PageRequest.of(0, 5);
		Specification<Transferencia> specification = null;

		BDDMockito.when(this.service.novo(ArgumentMatchers.any(Transferencia.class)))
				.thenReturn(TransferenciaUtils.transferencia_novo(98l, "Nome novo"));

//		BDDMockito.doNothing().when(this.service).editar(ArgumentMatchers.any(Transferencia.class));
//		BDDMockito.doNothing().when(this.service).excluir(ArgumentMatchers.any(Transferencia.class));

		BDDMockito.when(this.service.findall(page)).thenReturn(retorno.getPage());
		BDDMockito.when(this.service.findall(specification))
				.thenReturn(List.of(TransferenciaUtils.transferencia_novo(1l, "nome1 1")));
		BDDMockito.when(this.service.findall(specification, page)).thenReturn(retorno.getPage());

		BDDMockito.when(this.service.findbyid(ArgumentMatchers.anyLong()))
				.thenReturn(TransferenciaUtils.transferencia_novo(98l, "Teste novo"));

	}

	@Test
	void testNovo() {
		Transferencia transferencia = this.controller.novo(TransferenciaUtils.transferencia_novo(98l, "nome 98l"))
				.getBody();
		Assertions.assertThat(transferencia).isNotNull().isEqualTo(TransferenciaUtils.transferencia_validade());
	}

	@Test
	void testEditar() {
		Assertions.assertThatCode(() -> this.controller.editar(TransferenciaUtils.transferencia_novo(2l, "Alterar")))
				.doesNotThrowAnyException();
		ResponseEntity<Void> entity = this.controller.editar(TransferenciaUtils.transferencia_novo(2l, "Alterar"));
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void testExcluir() {
		Assertions.assertThatCode(() -> this.controller.excluir(TransferenciaUtils.transferencia_novo(2l, "Excluir")))
				.doesNotThrowAnyException();
		ResponseEntity<Void> entity = this.controller.excluir(TransferenciaUtils.transferencia_novo(2l, "Excluir"));
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void testFindall() {
		TransferenciaRetorno retorno = TransferenciaUtils.transferenciaRetorno_validade();
		Page<Transferencia> pageImpl = retorno.getPage();
		Page<Transferencia> expectedName = retorno.getPage();
		Assertions.assertThat(pageImpl).isNotNull();
		Assertions.assertThat(pageImpl.toList()).isNotEmpty().hasSize(1);
		Assertions.assertThat(pageImpl.toList().get(0).getNome_operador_transacao())
				.isEqualTo(expectedName.toList().get(0).getNome_operador_transacao());

	}

	@Test
	void testFindbyid() {
		Long expectedId = TransferenciaUtils.transferencia_validade().getId();
		Transferencia transferencia = this.controller.findbyid(1l).getBody();
		Assertions.assertThat(transferencia).isNotNull();
		Assertions.assertThat(transferencia.getId()).isNotNull().isEqualTo(expectedId);
	}

}
