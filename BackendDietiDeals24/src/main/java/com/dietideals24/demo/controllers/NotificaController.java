package com.dietideals24.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dietideals24.demo.models.dto.NotificaDTO;
import com.dietideals24.demo.service.NotificaService;

@RestController
public class NotificaController {

	@Autowired
	@Qualifier("NotificaService")
	private NotificaService notificaService;
	private final String error_string = "Il parametro 'id_utente è null!\n";
	
	@GetMapping("/notifica/mostraTutte")
	public ResponseEntity<List<NotificaDTO>> mostraNotifiche(@RequestParam Integer id_utente) {
		if (id_utente == null)
			throw new IllegalArgumentException("Errore Mostra Notifiche: " + error_string);
		List<NotificaDTO> notificheDTO = notificaService.getNotifiche(id_utente);
		if (notificheDTO == null || notificheDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(notificheDTO);
	}
	
	@GetMapping("/notifica/mostraLette")
	public ResponseEntity<List<NotificaDTO>> mostraNotificheLette(@RequestParam Integer id_utente) {
		if (id_utente == null)
			throw new IllegalArgumentException("Errore Mostra Notifiche Lette: " + error_string);
		List<NotificaDTO> notificheDTO = notificaService.getNotificheLette(id_utente);
		if (notificheDTO == null || notificheDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(notificheDTO);
	}
	
	@GetMapping("/notifica/mostraNonLette")
	public ResponseEntity<List<NotificaDTO>> mostraNotificheNonLette(@RequestParam Integer id_utente) {
		if (id_utente == null)
			throw new IllegalArgumentException("Errore Mostra Notifiche Non Lette: " + error_string);
		List<NotificaDTO> notificheDTO = notificaService.getNotificheNonLette(id_utente);
		if (notificheDTO == null || notificheDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(notificheDTO);
	}
	
	@PutMapping("/notifica/segna")
	public void marcaLetta(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Lettura Notifica: " + error_string);
		notificaService.setNotificaAsLetta(id);
	}
	
	@PutMapping("notifica/segnaTutte")
	public void marcaTutte(@RequestParam Integer id_utente) {
		if (id_utente == null)
			throw new IllegalArgumentException("Errore Marcatura Notifiche: " + error_string);
		notificaService.setAllNotificheAsLette(id_utente);
	}
	
	@PostMapping("/notifica/rimuovi")
	public void rimuovi(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Rimozione Notifica: " + error_string);
		notificaService.rimuoviNotifica(id);
	}
	
	@PostMapping("/notifica/svuota")
	public void svuota(@RequestParam Integer id_utente) {
		if (id_utente == null)
			throw new IllegalArgumentException("Errore Azzeramento Notifiche: " + error_string);
		notificaService.rimuoviNotifiche(id_utente);
	}
	
	@PostMapping("/notifica/rimuoviLette")
	public void rimuoviLette(@RequestParam Integer id_utente) {
		if (id_utente == null)
			throw new IllegalArgumentException("Errore Rimozione Notifiche Lette: " + error_string);
		notificaService.rimuoviNotificheLette(id_utente);
	}
}	
