package com.dietideals24.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dietideals24.demo.enums.StatoAsta;
import com.dietideals24.demo.models.AstaSilenziosa;

import jakarta.transaction.Transactional;

@Repository
public interface AstaSilenziosaRepository extends CrudRepository<AstaSilenziosa, Integer>{

	@Transactional
    @Modifying
    @Query(value = "INSERT INTO asta_silenziosa (id, scadenza) VALUES (:idAsta, :scadenza)", nativeQuery = true)
    void insertAstaSilenziosa(@Param("id_asta") int idAsta, @Param("scadenza") String scadenza);
	
	@Transactional
	@Modifying
	@Query("DELETE from Asta_Silenziosa a WHERE a.id = :id")
	void eliminaAstaSilenziosa(@Param("id") int id);
	
	@Query("SELECT a FROM Asta_Silenziosa a WHERE a.id = :id")
	AstaSilenziosa getAstaSilenziosa(@Param("id") int id);
	
	@Query("SELECT as FROM Asta_Silenziosa as JOIN Asta a ON as.id = a.id WHERE a.stato = :stato")
    List<AstaSilenziosa> cercaAsteSilenziose(StatoAsta stato);
}
