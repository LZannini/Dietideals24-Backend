package com.dietideals24.demo.service;

import java.util.List;

import com.dietideals24.demo.enums.Categoria;
import com.dietideals24.demo.models.dto.AstaDTO;
import com.dietideals24.demo.models.dto.AstaInversaDTO;
import com.dietideals24.demo.models.dto.AstaRibassoDTO;
import com.dietideals24.demo.models.dto.AstaSilenziosaDTO;

public interface AstaService {
	
	void creaAstaInversa(AstaInversaDTO astaDTO);
	void creaAstaAlRibasso(AstaRibassoDTO astaDTO);
	void creaAstaSilenziosa(AstaSilenziosaDTO astaDTO);
	void rimuoviAsta(int id);
	AstaDTO trovaAsta(int id);
	List<AstaDTO> trovaTutte();
	List<AstaDTO> trovaAsteUtente(int idCreatore);
	List<AstaDTO> trovaAsteOfferteUtente(int idUtente);
	List<AstaDTO> trovaAstePerParolaChiave(String chiave);
	List<AstaDTO> trovaAstePerCategoria(Categoria categoria);
	List<AstaDTO> trovaAstePerParolaChiaveAndCategoria(String chiave, Categoria categoria);
	AstaInversaDTO trovaAstaInversa(Integer id);
	AstaRibassoDTO trovaAstaRibasso(Integer id);
	AstaSilenziosaDTO trovaAstaSilenziosa(Integer id);
}
