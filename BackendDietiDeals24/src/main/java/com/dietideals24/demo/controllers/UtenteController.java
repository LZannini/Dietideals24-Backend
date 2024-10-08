package com.dietideals24.demo.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dietideals24.demo.models.dto.UtenteDTO;
import com.dietideals24.demo.service.UtenteService;

@RestController
public class UtenteController {

    @Qualifier("utenteService")
    private final UtenteService utenteService;
	
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}
	
	@PostMapping("/utente/registra")
	public ResponseEntity<UtenteDTO> registra(@RequestBody UtenteDTO utenteDTO) {
		try {
	        UtenteDTO utenteRegistrato = utenteService.registraUtente(utenteDTO);
	        return ResponseEntity.ok(utenteRegistrato);
	    } catch (DataIntegrityViolationException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	    }
	}
	
	

	@GetMapping("/utente/recupera")
	public ResponseEntity<UtenteDTO> recupera(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Recupero Utente: Parametri non validi!");
		
		UtenteDTO utenteDTO = utenteService.recuperaUtenteById(id);
        return ResponseEntity.ok(utenteDTO);
	}
	
	@GetMapping("/utente/cerca")
	public ResponseEntity<UtenteDTO> cerca(@RequestParam Integer id, @RequestParam String email) {
		if (id == null || email == null)
			throw new IllegalArgumentException("Errore Ricerca: Parametri non validi!");
		
		UtenteDTO utenteDTO = utenteService.recuperaUtente(id, email);
        return ResponseEntity.ok(utenteDTO);
	}
	
	@PostMapping("/utente/aggiorna")
		public ResponseEntity<UtenteDTO> aggiorna(@RequestBody UtenteDTO utenteDTO) {
			try {
				UtenteDTO utenteModificato = utenteService.updateUtente(utenteDTO);
				return ResponseEntity.ok(utenteModificato);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build();
			}
		}
		
		@PostMapping("/utente/modPassword")
		public ResponseEntity<UtenteDTO> modificaPassword(@RequestBody UtenteDTO utenteDTO) {
			try {
				UtenteDTO utenteModificato = utenteService.modificaPassword(utenteDTO.getId(), utenteDTO.getPassword());
				return ResponseEntity.ok(utenteModificato);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build();
			}
		}
}
