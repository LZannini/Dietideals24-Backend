package com.dietideals24.demo.serviceimplements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dietideals24.demo.enums.StatoOfferta;
import com.dietideals24.demo.models.Offerta;
import com.dietideals24.demo.models.dto.OffertaDTO;
import com.dietideals24.demo.repository.OffertaRepository;
import com.dietideals24.demo.service.OffertaService;

@Service("OffertaService")
public class OffertaServiceImplements implements OffertaService {
	
	@Autowired
	private OffertaRepository offertaRepository;

	@Override
	public void creaOfferta(OffertaDTO offertaDTO) {
		Offerta offerta = new Offerta();
		offerta.setIdUtente(offertaDTO.getIdUtente());
		offerta.setIdAsta(offertaDTO.getIdAsta());
		offerta.setValore(offertaDTO.getValore());
		offerta.setData(offertaDTO.getData());
		offerta.setStato(offertaDTO.getStato());
		offertaRepository.save(offerta);
	}
	
	@Override
	public void rimuoviOfferta(int id) {
		offertaRepository.eliminaOfferta(id);
	}
	
	@Override
	public OffertaDTO getOffertaMinima(int idAsta) {
		Offerta offertaMin = offertaRepository.trovaOffertaMinima(idAsta);
		if (offertaMin == null)
			return null;
		OffertaDTO offertaMinDTO = creaOffertaDTO(offertaMin);
		return offertaMinDTO;
	}

	@Override
	public List<OffertaDTO> getOfferteUtente(int idUtente) {
		List<Offerta> offerte = offertaRepository.trovaOfferteUtente(idUtente);

		List<OffertaDTO> listaOfferteDTO = new ArrayList<>();
        for (Offerta o : offerte) {
            OffertaDTO offertaDTO = creaOffertaDTO(o);
            listaOfferteDTO.add(offertaDTO);
        }
        return listaOfferteDTO;
	}

	@Override
	public List<OffertaDTO> getOfferteOrdinate(int idAsta) {
		List<Offerta> offerte = offertaRepository.trovaOfferteOrdinate(idAsta);

		List<OffertaDTO> listaOfferteDTO = new ArrayList<>();
        for (Offerta o : offerte) {
            OffertaDTO offertaDTO = creaOffertaDTO(o);
            listaOfferteDTO.add(offertaDTO);
        }
        return listaOfferteDTO;
	}
	
	@Override
	public void setOffertaAccettata(int idOfferta) {
		offertaRepository.updateStatoOfferta(idOfferta, StatoOfferta.ACCETTATA);
	}
	
	@Override
	public void setOffertaRifiutata(int idOfferta) {
		offertaRepository.updateStatoOfferta(idOfferta, StatoOfferta.RIFIUTATA);
	}
	
	private OffertaDTO creaOffertaDTO(Offerta offerta) {
		OffertaDTO offertaDTO = new OffertaDTO();
		offertaDTO.setId(offerta.getId());
        offertaDTO.setIdAsta(offerta.getIdAsta());
        offertaDTO.setIdUtente(offerta.getIdUtente());
        offertaDTO.setValore(offerta.getValore());
        offertaDTO.setData(offerta.getData());
        offertaDTO.setOfferente(offerta.getOfferente());
        offertaDTO.setStato(offerta.getStato());
        return offertaDTO;
	}
}
