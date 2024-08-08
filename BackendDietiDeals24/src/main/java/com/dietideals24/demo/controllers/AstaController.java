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
import com.dietideals24.demo.models.dto.Asta_InversaDTO;
import com.dietideals24.demo.models.dto.Asta_RibassoDTO;
import com.dietideals24.demo.models.dto.Asta_SilenziosaDTO;
import com.dietideals24.demo.service.AstaService;

@RestController
public class AstaController {
	
	@Autowired
    @Qualifier("AstaService")
    private AstaService astaService;
	
	@PostMapping("/asta/creaAstaInversa")
	public ResponseEntity<AstaDTO> crea(@RequestBody Asta_InversaDTO asta) {
		astaService.creaAstaInversa(asta);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/asta/creaAstaAlRibasso")
	public ResponseEntity<AstaDTO> crea(@RequestBody Asta_RibassoDTO asta) {
		astaService.creaAstaAlRibasso(asta);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/asta/creaAstaSilenziosa")
	public ResponseEntity<AstaDTO> crea(@RequestBody Asta_SilenziosaDTO asta) {
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
		AstaDTO astaDto = astaService.trovaAsta(id);
		if (astaDto == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(astaDto);
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
		List<AstaDTO> listaAsteDto = astaService.trovaAsteUtente(idCreatore);
		if (listaAsteDto == null || listaAsteDto.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDto);
	}
	
	@GetMapping("/asta/cercaPerChiave")
	public ResponseEntity<List<AstaDTO>> cercaPerParolaChiave(@RequestParam String chiave) {
		if (chiave == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per parola chiave): Campo 'chiave' nullo!\n");
		List<AstaDTO> listaAsteDto = astaService.trovaAstePerParolaChiave(chiave);
		if (listaAsteDto == null || listaAsteDto.isEmpty())
			return ResponseEntity.notFound().build();
					
		return ResponseEntity.ok(listaAsteDto);
	}
	
	@GetMapping("/asta/cercaPerCategoria")
	public ResponseEntity<List<AstaDTO>> cercaPerCategoria(@RequestParam Categoria categoria) {
		if (categoria == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per categoria): Campo 'categoria' nullo!\n");
		List<AstaDTO> listaAsteDto = astaService.trovaAstePerCategoria(categoria);
		if (listaAsteDto == null || listaAsteDto.isEmpty())
			return ResponseEntity.notFound().build();
					
		return ResponseEntity.ok(listaAsteDto);
	}
	
	@GetMapping("/asta/cercaPerChiaveAndCategoria")
	public ResponseEntity<List<AstaDTO>> cercaPerParolaChiaveAndCategoria(@RequestParam String chiave, @RequestParam Categoria categoria) {
		if (chiave == null || categoria == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per chiave e categoria): Campi nulli!\n");
		List<AstaDTO> listaAsteDto = astaService.trovaAstePerParolaChiaveAndCategoria(chiave, categoria);
		if (listaAsteDto == null || listaAsteDto.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDto);
	}
	
	@GetMapping("/asta/cercaPerOfferteUtente")
	public ResponseEntity<List<AstaDTO>> cercaPerOfferteUtente(@RequestParam Integer idUtente){
		if (idUtente == null)
			throw new IllegalArgumentException("Errore Ricerca Asta (per utente): Campo 'id_utente' nullo!\n");
		List<AstaDTO> listaAsteDto = astaService.trovaAsteOfferteUtente(idUtente);
		if (listaAsteDto == null || listaAsteDto.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listaAsteDto);
	}
	
	@GetMapping("/asta/dettagliAstaInversa")
    public ResponseEntity<Asta_InversaDTO> dettagliAstaInversa(@RequestParam Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Errore Ricerca Asta Inversa: Campo 'id' nullo!\n");
        
        Asta_InversaDTO astaInversaDto = astaService.trovaAstaInversa(id);
        if (astaInversaDto == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(astaInversaDto);
    }

    @GetMapping("/asta/dettagliAstaRibasso")
    public ResponseEntity<Asta_RibassoDTO> dettagliAstaRibasso(@RequestParam Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Errore Ricerca Asta Al Ribasso: Campo 'id' nullo!\n");
        
        Asta_RibassoDTO astaRibassoDto = astaService.trovaAstaRibasso(id);
        if (astaRibassoDto == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(astaRibassoDto);
    }

    @GetMapping("/asta/dettagliAstaSilenziosa")
    public ResponseEntity<Asta_SilenziosaDTO> dettagliAstaSilenziosa(@RequestParam Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Errore Ricerca Asta Silenziosa: Campo 'id' nullo!\n");
        
        Asta_SilenziosaDTO astaSilenziosaDto = astaService.trovaAstaSilenziosa(id);
        if (astaSilenziosaDto == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(astaSilenziosaDto);
    }
 }
