package br.com.banco.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.TransferenciaUtils;

@ExtendWith(SpringExtension.class)
class TransferenciaServiceTest {

	@Mock
	private TransferenciaRepository repository;

	@InjectMocks
	private TransferenciaService service;

	@Before(value = "")
	void setUp() {

	}

	@BeforeEach
	void setUpEach() {

	}

	@Test
	void testNovo() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(1l, "nome1");
		when(this.repository.save(ArgumentMatchers.any(Transferencia.class))).thenReturn(transferencia);
		Transferencia create = this.service.novo(transferencia);
		assertThat(create.getNome_operador_transacao()).isSameAs(transferencia.getNome_operador_transacao());
		verify(this.repository).save(transferencia);

	}

	@Test
	void testEditar() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(1l, "nome1");
		Transferencia novo = TransferenciaUtils.transferencia_novo(1l, "novo teste");
		when(this.repository.findById(transferencia.getId())).thenReturn(Optional.of(transferencia));

		this.service.editar(novo);

		verify(this.repository).save(novo);
		verify(this.repository).findById(transferencia.getId());
	}

	@Test
	void testEditar_nao_existe() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(90l, "novo teste");
		when(this.repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		this.service.editar(transferencia);
	}

	@Test
	void testExcluir() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(1l, "teste");
		Transferencia novo = TransferenciaUtils.transferencia_novo(1l, "novo teste");
		when(this.repository.findById(transferencia.getId())).thenReturn(Optional.of(transferencia));
		this.service.excluir(novo);
		verify(this.repository).delete(novo);
		;
		verify(this.repository).findById(transferencia.getId());
	}

	@Test
	void testExcluir_nao_existe() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(89l, "teste");
		when(this.repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		this.service.editar(transferencia);
	}

	@Test
	void testFindallPageable() {

		Page<Transferencia> page = mock(Page.class);
		Pageable pageable = PageRequest.of(0, 5);

		Specification<Transferencia> action = null;
		when(this.repository.findAll(pageable)).thenReturn(page);

		Page<Transferencia> expected = this.service.findall(pageable);
		verify(this.repository).findAll(pageable);

		assertEquals(expected, page);

		verify(this.repository).findAll(pageable);

	}

	@Test
	void testFindallSpecificationOfTransferencia() {

		List<Transferencia> lista = new ArrayList<>();
		lista.add(TransferenciaUtils.transferencia_novo(11, "novo"));

		Specification<Transferencia> action = null;

		when(this.repository.findAll(action)).thenReturn(lista);

		List<Transferencia> expected = this.service.findall(action);
		assertEquals(expected, lista);
		verify(this.repository).findAll(action);

	}

	@Test
	void testFindallSpecificationOfTransferenciaPageable() {

		Page<Transferencia> page = mock(Page.class);
		Pageable pageable = PageRequest.of(0, 5);

		Specification<Transferencia> action = null;
		when(this.repository.findAll(action, pageable)).thenReturn(page);

		Page<Transferencia> expected = this.service.findall(action, pageable);

		assertEquals(expected, page);

		verify(this.repository).findAll(action, pageable);
	}

	@Test
	void testFindsaldonoperiodo() {
		double valor = 0;
		Specification<Transferencia> action = null;

		List<Transferencia> lista = new ArrayList<Transferencia>();

		when(this.repository.findsaldonoperiodo(lista)).thenReturn(valor);

		double expected = this.service.findsaldonoperiodo(action);

		assertEquals(expected, valor);

		verify(this.repository).findsaldonoperiodo(lista);

	}

	@Test
	void testFindsaldototal() {
		double valor = 0;

		when(this.repository.findsaldototal()).thenReturn(valor);

		double expected = this.service.findsaldototal();

		assertEquals(expected, valor);

		verify(this.repository).findsaldototal();
	}

	@Test
	void testFindbyid() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(89l, "nome89");
		when(this.repository.findById(transferencia.getId())).thenReturn(Optional.of(transferencia));
		Transferencia expected = this.service.findbyid(transferencia.getId());
		assertThat(expected).isSameAs(transferencia);
		verify(this.repository).findById(transferencia.getId());
	}

	@Test
	void testFindbyid_nao_existe() {
		Transferencia transferencia = TransferenciaUtils.transferencia_novo(89l, "teste");
		when(this.repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		this.service.findbyid(transferencia.getId());

	}

}
