package br.com.banco.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;

@RestController
@RequestMapping(value = "/api/conta")
@CrossOrigin(value = "*")
@Transactional
public class ContaController {

	@Autowired
	private ContaService service;

	@GetMapping(value = "/findall")
	public ResponseEntity<List<Conta>> findall() {
		List<Conta> resp = this.service.findall();
		return new ResponseEntity<List<Conta>>(resp, HttpStatus.OK);
	}

	@GetMapping(value = "/findbyid/{id}")
	public ResponseEntity<Conta> findbyid(@PathVariable long id) {
		Conta resp = this.service.findbyid(id);
		if (resp == null) {
			return new ResponseEntity<Conta>(resp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conta>(resp, HttpStatus.OK);
	}

}
