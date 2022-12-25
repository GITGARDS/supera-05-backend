package br.com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.entities.Transferencia;

@Repository
public interface TransferenciaRepository
		extends PagingAndSortingRepository<Transferencia, Long>
				, JpaSpecificationExecutor<Transferencia>


{
	
	@Query(value = "SELECT SUM(obj.valor) AS total FROM Transferencia obj")
	double findsaldototal();
	
	
	@Query(value = "SELECT SUM(obj.valor) AS total FROM Transferencia obj "
			+ "WHERE obj IN :lista")
	double findsaldonoperiodo(@Param(value = "lista") List<Transferencia> lista);

}
