package br.com.banco.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;

@Service
@Transactional
public class ContaService {

	@Autowired
	private ContaRepository repository;

	public List<Conta> findall() {
		return this.repository.findAll();
	}

	public Conta findbyid(long id) {
		return this.repository.findById(id).orElseThrow();
	}

}
