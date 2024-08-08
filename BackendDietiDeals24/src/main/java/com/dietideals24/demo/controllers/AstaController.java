package com.dietideals24.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dietideals24.demo.enums.Categoria;
import com.dietideals24.demo.models.dto.AstaDTO;
import com.dietideals24.demo.models.dto.AstaInversaDTO;
import com.dietideals24.demo.models.dto.AstaRibassoDTO;
import com.dietideals24.demo.models.dto.AstaSilenziosaDTO;
import com.dietideals24.demo.service.AstaService;

@RestController
public class AstaController {
	
	@Autowired
    @Qualifier("astaService")
    private AstaService astaService;
	
	@PostMapping("/asta/creaAstaInversa")
	public ResponseEntity<AstaDTO> crea(@RequestBody AstaInversaDTO asta) {
		astaService.creaAstaInversa(asta);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/asta/creaAstaAlRibasso")
	public ResponseEntity<AstaDTO> crea(@RequestBody AstaRibassoDTO asta) {
		astaService.creaAstaAlRibasso(asta);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/asta/creaAstaSilenziosa")
	public ResponseEntity<AstaDTO> crea(@RequestBody AstaSilenziosaDTO asta) {
		astaService.creaAstaSilenziosa(asta);
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/asta/rimuovi")
	public void rimuovi(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Rimozione Asta: Campo 'id' nullo!\n");
		astaService.rimuoviAsta(id);
	}
	
	@GetMapping("/asta/recupera")
	public ResponseEntity<AstaDTO> recupera(@RequestParam Integer id) {
		if (id == null)
			throw new IllegalArgumentException("Errore Recupera Asta: Campo 'id nullo!\n");
		AstaDTO astaDTO = astaService.trovaAsta(id);
		if (astaDTO == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(astaDTO);
	}
	
	@GetMapping("/asta/cercaTutte")
	public ResponseEntity<List<AstaDTO>> findAllAuctions() {
		List<AstaDTO> listaAsteDto = astaService.trovaTutte();
		if (listaAsteDto == null || listaAsteDto.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDto);
	}
	
	@GetMapping("/asta/cercaPerUtente")
	public ResponseEntity<List<AstaDTO>> cercaPerUtente(@RequestParam Integer idCreatore) {
		if (idCreatore == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per utente): Campo 'id_creatore' nullo!\n");
		List<AstaDTO> listaAsteDTO = astaService.trovaAsteUtente(idCreatore);
		if (listaAsteDTO == null || listaAsteDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDTO);
	}
	
	@GetMapping("/asta/cercaPerChiave")
	public ResponseEntity<List<AstaDTO>> cercaPerParolaChiave(@RequestParam String chiave) {
		if (chiave == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per parola chiave): Campo 'chiave' nullo!\n");
		List<AstaDTO> listaAsteDTO = astaService.trovaAstePerParolaChiave(chiave);
		if (listaAsteDTO == null || listaAsteDTO.isEmpty())
			return ResponseEntity.notFound().build();
					
		return ResponseEntity.ok(listaAsteDTO);
	}
	
	@GetMapping("/asta/cercaPerCategoria")
	public ResponseEntity<List<AstaDTO>> cercaPerCategoria(@RequestParam Categoria categoria) {
		if (categoria == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per categoria): Campo 'categoria' nullo!\n");
		List<AstaDTO> listaAsteDTO = astaService.trovaAstePerCategoria(categoria);
		if (listaAsteDTO == null || listaAsteDTO.isEmpty())
			return ResponseEntity.notFound().build();
					
		return ResponseEntity.ok(listaAsteDTO);
	}
	
	@GetMapping("/asta/cercaPerChiaveAndCategoria")
	public ResponseEntity<List<AstaDTO>> cercaPerParolaChiaveAndCategoria(@RequestParam String chiave, @RequestParam Categoria categoria) {
		if (chiave == null || categoria == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per chiave e categoria): Campi nulli!\n");
		List<AstaDTO> listaAsteDTO = astaService.trovaAstePerParolaChiaveAndCategoria(chiave, categoria);
		if (listaAsteDTO == null || listaAsteDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDTO);
	}
	
	@GetMapping("/asta/cercaPerOfferteUtente")
	public ResponseEntity<List<AstaDTO>> cercaPerOfferteUtente(@RequestParam Integer idUtente){
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per utente): Campo 'id_utente' nullo!\n");
		List<AstaDTO> listaAsteDTO = astaService.trovaAsteOfferteUtente(idUtente);
		if (listaAsteDTO == null || listaAsteDTO.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDTO);
	}
	
	@GetMapping("/asta/dettagliAstaInversa")
    public ResponseEntity<AstaInversaDTO> dettagliAstaInversa(@RequestParam Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Errore Ricerca Asta Inversa: Campo 'id' nullo!\n");
        
        AstaInversaDTO astaInversaDTO = astaService.trovaAstaInversa(id);
        if (astaInversaDTO == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(astaInversaDTO);
    }

    @GetMapping("/asta/dettagliAstaRibasso")
    public ResponseEntity<AstaRibassoDTO> dettagliAstaRibasso(@RequestParam Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Errore Ricerca Asta Al Ribasso: Campo 'id' nullo!\n");
        
        AstaRibassoDTO astaRibassoDTO = astaService.trovaAstaRibasso(id);
        if (astaRibassoDTO == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(astaRibassoDTO);
    }

    @GetMapping("/asta/dettagliAstaSilenziosa")
    public ResponseEntity<AstaSilenziosaDTO> dettagliAstaSilenziosa(@RequestParam Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Errore Ricerca Asta Silenziosa: Campo 'id' nullo!\n");
        
        AstaSilenziosaDTO astaSilenziosaDTO = astaService.trovaAstaSilenziosa(id);
        if (astaSilenziosaDTO == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(astaSilenziosaDTO);
    }
 }
