package br.com.banco.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

@Service
@Transactional
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;
	
	@Autowired
	private ContaService contaService;

	public Transferencia novo(Transferencia t) {
		return this.repository.save(t);
	}

	public Transferencia editar(Transferencia t) {
		Transferencia resp = this.repository.findById(t.getId()).orElse(null);
		if (resp != null) {
			resp = this.repository.save(t);
		}
		return resp;

	}

	public Transferencia excluir(Transferencia t) {
		Transferencia resp = this.repository.findById(t.getId()).orElse(null);
		if (resp != null) {
			this.repository.delete(t);
		}
		return resp;

	}

	public Page<Transferencia> findall(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public List<Transferencia> findall(Specification<Transferencia> spec) {
		return this.repository.findAll(spec);
	}

	public Page<Transferencia> findall(Specification<Transferencia> spec, Pageable pageable) {
		return this.repository.findAll(spec, pageable);
	}
//	public Page<Transferencia> findall(long conta_id, Pageable pageable) {
//		return this.repository.findall(conta_id, pageable);
//	}
//	
//	public List<Transferencia> findall(long conta_id, Specification<Transferencia> spec) {
//		return this.repository.findall(conta_id, spec);
//	}
//	
//	public Page<Transferencia> findall(long conta_id, Specification<Transferencia> spec, Pageable pageable) {
//		return this.repository.findall(conta_id, spec, pageable);
//	}

	public Double findsaldonoperiodo(Specification<Transferencia> spec) {
		return this.repository.findsaldonoperiodo(this.findall(spec));
	}

	public Double findsaldototal(Long d) {
		return this.repository.findsaldototal(this.contaService.findbyid(d));
	}

	public Transferencia findbyid(Long id) {
		return this.repository.findById(id).orElseThrow();
	}

}
