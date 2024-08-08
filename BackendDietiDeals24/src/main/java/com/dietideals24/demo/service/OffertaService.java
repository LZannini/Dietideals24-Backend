package com.dietideals24.demo.service;

import java.util.List;

import com.dietideals24.demo.models.dto.OffertaDTO;

public interface OffertaService {
	
	void creaOfferta(OffertaDTO offertaDTO);
	void rimuoviOfferta(int id);
	OffertaDTO getOffertaMinima(int idAsta);
	List<OffertaDTO> getOfferteUtente(int idUtente);
	List<OffertaDTO> getOfferteOrdinate(int idAsta);
	void setOffertaAccettata(int idOfferta);
	void setOffertaRifiutata(int idOfferta);
}
