package com.dietideals24.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dietideals24.demo.enums.StatoAsta;
import com.dietideals24.demo.models.AstaRibasso;
import jakarta.transaction.Transactional;

@Repository
public interface AstaRibassoRepository extends CrudRepository<AstaRibasso, Integer>{
	
	@Transactional
    @Modifying
    @Query(value = "INSERT INTO asta_ribasso (id, prezzo, timer, decremento, minimo) VALUES (:idAsta, :prezzo, :timer, :decremento, :minimo)", nativeQuery = true)
    void insertAstaAlRibasso(@Param("id_asta") int idAsta, @Param("prezzo") float prezzo, @Param("timer") String timer, @Param("decremento") float decremento, @Param("minimo") float minimo);

	@Transactional
	@Modifying
	@Query("DELETE from Asta_Ribasso a WHERE a.id = :id")
	void eliminaAstaAlRibasso(@Param("id") int id);
	
	@Query("SELECT a FROM Asta_Ribasso a WHERE a.id = :id")
	AstaRibasso getAstaAlRibasso(@Param("id") int id);
	

    @Query("SELECT ar FROM Asta_Ribasso ar JOIN Asta a ON ar.id = a.id WHERE a.stato = :stato")
    List<AstaRibasso> cercaAsteRibasso(StatoAsta stato);
}
