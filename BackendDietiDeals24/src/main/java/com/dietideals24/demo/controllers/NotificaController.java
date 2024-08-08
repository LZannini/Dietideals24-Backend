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
	private static final String errorString = "Il parametro 'id_utente è null!\n";
	
	@GetMapping("/notifica/mostraTutte")
	public ResponseEntity<List<NotificaDTO>> mostraNotifiche(@RequestParam Integer idUtente) {
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Mostra Notifiche: " + errorString);
		List<NotificaDTO> notificheDTO = notificaService.getNotifiche(idUtente);
		if (notificheDTO == null || notificheDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(notificheDTO);
	}
	
	@GetMapping("/notifica/mostraLette")
	public ResponseEntity<List<NotificaDTO>> mostraNotificheLette(@RequestParam Integer idUtente) {
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Mostra Notifiche Lette: " + errorString);
		List<NotificaDTO> notificheDTO = notificaService.getNotificheLette(idUtente);
		if (notificheDTO == null || notificheDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(notificheDTO);
	}
	
	@GetMapping("/notifica/mostraNonLette")
	public ResponseEntity<List<NotificaDTO>> mostraNotificheNonLette(@RequestParam Integer idUtente) {
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Mostra Notifiche Non Lette: " + errorString);
		List<NotificaDTO> notificheDTO = notificaService.getNotificheNonLette(idUtente);
		if (notificheDTO == null || notificheDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(notificheDTO);
	}
	
	@PutMapping("/notifica/segna")
	public void marcaLetta(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Lettura Notifica: " + errorString);
		notificaService.setNotificaAsLetta(id);
	}
	
	@PutMapping("notifica/segnaTutte")
	public void marcaTutte(@RequestParam Integer idUtente) {
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Marcatura Notifiche: " + errorString);
		notificaService.setAllNotificheAsLette(idUtente);
	}
	
	@PostMapping("/notifica/rimuovi")
	public void rimuovi(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Rimozione Notifica: " + errorString);
		notificaService.rimuoviNotifica(id);
	}
	
	@PostMapping("/notifica/svuota")
	public void svuota(@RequestParam Integer idUtente) {
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Azzeramento Notifiche: " + errorString);
		notificaService.rimuoviNotifiche(idUtente);
	}
	
	@PostMapping("/notifica/rimuoviLette")
	public void rimuoviLette(@RequestParam Integer idUtente) {
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Rimozione Notifiche Lette: " + errorString);
		notificaService.rimuoviNotificheLette(idUtente);
	}
}	
