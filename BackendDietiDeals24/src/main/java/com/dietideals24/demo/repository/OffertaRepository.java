package com.dietideals24.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dietideals24.demo.enums.StatoOfferta;
import com.dietideals24.demo.models.Offerta;

import jakarta.transaction.Transactional;

@Repository
public interface OffertaRepository extends CrudRepository<Offerta, Integer>{
  
	 @Query("SELECT o FROM Offerta o WHERE o.id_utente = :idUtente")
	 List<Offerta> trovaOfferteUtente(@Param("id_utente") int idUtente);
	 
	 @Query("SELECT o FROM Offerta o WHERE o.id_asta = :idAsta AND o.stato = 0 ORDER BY o.valore DESC")
	 List<Offerta> trovaOfferteOrdinate(@Param("id_asta") int idAsta);
	 
	 @Query("SELECT o FROM Offerta o WHERE o.id_asta = :idAsta ORDER BY o.valore ASC LIMIT 1")
	 Offerta trovaOffertaMinima(@Param("id_asta") int idAsta);
	 
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM Offerta o WHERE o.id = :id")
	 void eliminaOfferta(@Param("id") int id);

	@Transactional 
	@Modifying
	@Query("UPDATE Offerta set stato = :stato WHERE id = :id")
	void updateStatoOfferta(@Param("id") int id, @Param("stato") StatoOfferta stato);
}
