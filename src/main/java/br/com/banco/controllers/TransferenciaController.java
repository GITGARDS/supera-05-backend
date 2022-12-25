package br.com.banco.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.ContaService;
import br.com.banco.services.TransferenciaService;
import br.com.banco.spec.TransferenciaSpec;

@RestController
@RequestMapping(value = "/api/transferencia")
@CrossOrigin(value = "*")
@Transactional
public class TransferenciaController {

	@Autowired
	private TransferenciaService service;
	
	@Autowired
	private ContaService contaService;

	@PostMapping(value = "/novo")
	public ResponseEntity<Transferencia> novo(@RequestBody Transferencia t) {
		Transferencia resp = new Transferencia();
		try {
			resp = this.service.novo(t);
			if (resp == null) {
				return new ResponseEntity<Transferencia>(resp, HttpStatus.FAILED_DEPENDENCY);
			}

		} catch (Exception e) {
			return new ResponseEntity<Transferencia>(resp, HttpStatus.FAILED_DEPENDENCY);

		}
		return new ResponseEntity<Transferencia>(resp, HttpStatus.OK);

	}

	@PutMapping(value = "/update")
	public ResponseEntity<Void> editar(@RequestBody Transferencia t) {
		Transferencia resp = new Transferencia();
		try {
			resp = this.service.editar(t);
			if (resp == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping(value = "/excluir")
	public ResponseEntity<Void> excluir(@RequestBody Transferencia t) {
		Transferencia resp = new Transferencia();
		try {
			resp = this.service.excluir(t);
			if (resp == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping(value = "/findall")
	public ResponseEntity<TransferenciaRetorno> findall(
			@RequestParam(required = false) Long conta,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String inicio, 
			@RequestParam(required = false) String fim,			
			@PageableDefault(size = 5, sort = "id") Pageable pageable) {		
		TransferenciaRetorno resp = new TransferenciaRetorno();
		resp.setSaldoTotal(this.service.findsaldototal(conta));
		resp.setSaldoNoPeriodo(this.service.findsaldonoperiodo(TransferenciaSpec.findallRp(this.contaService.findbyid(conta), nome, inicio, fim)));		
		resp.setPage(this.service.findall(TransferenciaSpec.findallRp(this.contaService.findbyid(conta),  nome, inicio, fim), pageable));
		return new ResponseEntity<TransferenciaRetorno>(resp, HttpStatus.OK);
	}

	@GetMapping(value = "/findbyid/{id}")
	public ResponseEntity<Transferencia> findbyid(@PathVariable Long id) {
		Transferencia resp = this.service.findbyid(id);
		if (resp == null) {
			return new ResponseEntity<Transferencia>(resp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Transferencia>(resp, HttpStatus.OK);
	}

}
