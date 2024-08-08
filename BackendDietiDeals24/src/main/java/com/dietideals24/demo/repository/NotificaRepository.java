package com.dietideals24.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dietideals24.demo.models.Notifica;

import jakarta.transaction.Transactional;

public interface NotificaRepository extends CrudRepository<Notifica, Integer> {

	@Query("SELECT n FROM Notifica n WHERE n.id_utente = :idUtente ORDER BY n.data")
	List<Notifica> trovaNotifiche(@Param("id_utente") int idUtente);
	
	@Query("SELECT n FROM Notifica n WHERE n.id_utente = :idUtente AND n.letta = true ORDER BY n.data")
	List<Notifica> trovaNotificheLette(@Param("id_utente") int idUtente);
	
	@Query("SELECT n FROM Notifica n WHERE n.id_utente = :idUtente AND n.letta = false ORDER BY n.data")
	List<Notifica> trovaNotificheNonLette(@Param("id_utente")int idUtente);
	
	@Transactional 
	@Modifying
	@Query("UPDATE Notifica n SET n.letta = true WHERE n.id = :id")
	void segnaNotificaComeLetta(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Notifica n SET n.letta = true WHERE n.id_utente = :idUtente")
	void segnaAllNotificheComeLette(@Param("id_utente") int idUtente);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Notifica n WHERE n.id = :id")
	void eliminaNotifica(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Notifica n WHERE n.id_utente = :idUtente")
	void eliminaNotifiche(@Param("id_utente") int idUtente);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Notifica n WHERE n.id_utente = :idUtente AND n.letta = true")
	void eliminaNotificheLette(@Param("id_utente") int idUtente);
}
