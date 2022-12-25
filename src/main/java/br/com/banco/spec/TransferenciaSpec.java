package br.com.banco.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.banco.entities.Transferencia;

public class TransferenciaSpec {

	public static Specification<Transferencia> findallRp(String nome, String inicio, String fim) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (nome != null) {
				predicates.add(
						cb.like(cb.lower(root.<String>get("nome_operador_transacao")), "%" + nome.toLowerCase() + "%"));
			}

			if (inicio != null && fim != null) {
				predicates
						.add(cb.between(root.get("data_transferencia"), LocalDate.parse(inicio), LocalDate.parse(fim)));
			}

			query.orderBy(cb.desc(root.get("nome_operador_transacao")), cb.asc(root.get("id")));

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

}
