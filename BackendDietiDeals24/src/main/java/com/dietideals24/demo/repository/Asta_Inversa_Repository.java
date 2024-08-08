package com.dietideals24.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dietideals24.demo.enums.StatoAsta;
import com.dietideals24.demo.models.AstaInversa;
import jakarta.transaction.Transactional;

@Repository
public interface Asta_Inversa_Repository extends CrudRepository<AstaInversa, Integer>{
	
	@Transactional
    @Modifying
    @Query(value = "INSERT INTO asta_inversa (id, prezzo, scadenza) VALUES (:idAsta, :prezzo, :scadenza)", nativeQuery = true)
    void insertAstaInversa(@Param("id_asta") int idAsta, @Param("prezzo") float prezzo, @Param("scadenza") String scadenza);

	@Transactional
	@Modifying
	@Query("DELETE from Asta_Inversa a WHERE a.id = :id")
	void eliminaAstaInversa(@Param("id") int id);
	
	@Query("SELECT a FROM Asta_Inversa a WHERE a.id = :id")
	AstaInversa getAstaInversa(@Param("id") int id);
	
	@Query("SELECT ai FROM Asta_Inversa ai JOIN Asta a ON ai.id = a.id WHERE a.stato = :stato")
    List<AstaInversa> cercaAsteInverse(StatoAsta stato);
}
