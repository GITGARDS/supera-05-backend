package br.com.banco.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.utils.ContaUtils;

@ExtendWith(SpringExtension.class)
class ContaServiceTest {

	@Mock
	private ContaRepository repository;

	@InjectMocks
	private ContaService service;

	@Before(value = "")
	void setUp() {

	}

	@Test
	void testFindall() {

		List<Conta> lista = new ArrayList<>();
		lista.add(ContaUtils.conta_novo());

		when(this.repository.findAll()).thenReturn(lista);

		List<Conta> expected = this.service.findall();
		assertEquals(expected, lista);
		verify(this.repository).findAll();

	}

	@Test
	void testFindbyid() {
		Conta conta = ContaUtils.conta_novo();
		when(this.repository.findById(conta.getId_conta())).thenReturn(Optional.of(conta));
		Conta expected = this.service.findbyid(conta.getId_conta());
		assertThat(expected).isSameAs(conta);
		verify(this.repository).findById(conta.getId_conta());
	}

	@Test
	void testFindbyid_nao_existe() {
		Conta conta = ContaUtils.conta_novo();
		when(this.repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		this.service.findbyid(conta.getId_conta());

	}

}
