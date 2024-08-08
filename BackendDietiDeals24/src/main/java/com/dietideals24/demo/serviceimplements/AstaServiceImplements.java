package com.dietideals24.demo.serviceimplements;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dietideals24.demo.enums.Categoria;
import com.dietideals24.demo.enums.StatoAsta;
import com.dietideals24.demo.models.Asta;
import com.dietideals24.demo.models.AstaInversa;
import com.dietideals24.demo.models.AstaRibasso;
import com.dietideals24.demo.models.AstaSilenziosa;
import com.dietideals24.demo.models.dto.AstaDTO;
import com.dietideals24.demo.models.dto.Asta_InversaDTO;
import com.dietideals24.demo.models.dto.Asta_RibassoDTO;
import com.dietideals24.demo.models.dto.Asta_SilenziosaDTO;
import com.dietideals24.demo.repository.AstaRepository;
import com.dietideals24.demo.repository.Asta_Inversa_Repository;
import com.dietideals24.demo.repository.Asta_Ribasso_Repository;
import com.dietideals24.demo.repository.Asta_Silenziosa_Repository;
import com.dietideals24.demo.service.AstaService;

import jakarta.transaction.Transactional;

@Service("AstaService")
public class AstaServiceImplements implements AstaService {
	
	@Autowired
	private AstaRepository astaRepository;
	@Autowired
	private Asta_Inversa_Repository astaInversaRepository;
	@Autowired
	private Asta_Ribasso_Repository astaAlRibassoRepository;
	@Autowired
	private Asta_Silenziosa_Repository astaSilenziosaRepository;

	@Override
	@Transactional
	public void creaAstaInversa(Asta_InversaDTO astaDTO) {
		Asta asta = new Asta();
		asta.setIdCreatore(astaDTO.getIdCreatore());
		asta.setNome(astaDTO.getNome());
		asta.setDescrizione(astaDTO.getDescrizione());
		asta.setCategoria(astaDTO.getCategoria());
		asta.setFoto(astaDTO.getFoto());
		astaRepository.save(asta);
		astaInversaRepository.insertAstaInversa(asta.getId(), astaDTO.getPrezzo(), astaDTO.getScadenza());
	}
	
	@Override
	@Transactional
	public void creaAstaAlRibasso(Asta_RibassoDTO astaDTO) {
		Asta asta = new Asta();
		asta.setIdCreatore(astaDTO.getIdCreatore());
		asta.setNome(astaDTO.getNome());
		asta.setDescrizione(astaDTO.getDescrizione());
		asta.setCategoria(astaDTO.getCategoria());
		asta.setFoto(astaDTO.getFoto());
		astaRepository.save(asta);
		astaAlRibassoRepository.insertAstaAlRibasso(asta.getId(), astaDTO.getPrezzo(), astaDTO.getTimer(), astaDTO.getDecremento(), astaDTO.getMinimo());
	}
	
	@Override
	@Transactional
	public void creaAstaSilenziosa(Asta_SilenziosaDTO astaDTO) {
		Asta asta = new Asta();
        asta.setIdCreatore(astaDTO.getIdCreatore());
        asta.setNome(astaDTO.getNome());
        asta.setDescrizione(astaDTO.getDescrizione());
        asta.setCategoria(astaDTO.getCategoria());
        asta.setFoto(astaDTO.getFoto());
        astaRepository.save(asta);
        astaSilenziosaRepository.insertAstaSilenziosa(asta.getId(), astaDTO.getScadenza());
	}
	
	@Override
	public void rimuoviAsta(int id) {
		AstaInversa astaI = astaInversaRepository.getAstaInversa(id);
		AstaRibasso astaR = astaAlRibassoRepository.getAstaAlRibasso(id);
		AstaSilenziosa astaS = astaSilenziosaRepository.getAstaSilenziosa(id);
		if (astaI != null) 
			astaInversaRepository.eliminaAstaInversa(id);	
		else if (astaR != null) 
			astaAlRibassoRepository.eliminaAstaAlRibasso(id);	
		else if (astaS != null) 
			astaSilenziosaRepository.eliminaAstaSilenziosa(id);
		astaRepository.eliminaAsta(id);
	}
	
	@Override
	public AstaDTO trovaAsta(int id) {
		Asta asta = astaRepository.getAsta(id);
		AstaDTO astaDTO = null;
		if (asta != null) { 
			astaDTO = creaAstaDTO(asta);
			if(asta instanceof AstaRibasso)
				astaDTO.setTipo("RIBASSO");
			else if(asta instanceof AstaSilenziosa)
				astaDTO.setTipo("SILENZIOSA");
			else if (asta instanceof AstaInversa)
				astaDTO.setTipo("INVERSA");
			}
		
		return astaDTO;
	}
	
	@Override
	public List<AstaDTO> trovaTutte() {
		List<AstaDTO> asteTrovate = new ArrayList<>();
		List<Asta> checkAste = astaRepository.cercaTutte(StatoAsta.ATTIVA);
		asteTrovate = riempiListaAste(checkAste, asteTrovate);
		return asteTrovate;
	}
	
	@Override
	public List<AstaDTO> trovaAsteUtente(int idCreatore) {
		List<AstaDTO> asteTrovate = new ArrayList<>();
		List<Asta> checkAste = astaRepository.filtraPerUtente(idCreatore);
		asteTrovate = riempiListaAste(checkAste, asteTrovate);
		return asteTrovate;
	}
	
	public List<AstaDTO> trovaAsteOfferteUtente(int idUtente) {
		List<AstaDTO> asteTrovate = new ArrayList<>();
		List<Asta> checkAste = astaRepository.filtraPerOfferteUtente(idUtente);
		asteTrovate = riempiListaAste(checkAste, asteTrovate);
		return asteTrovate;
	}

	@Override
	public List<AstaDTO> trovaAstePerParolaChiave(String chiave) {
		List<AstaDTO> asteTrovate = new ArrayList<>();
		List<Asta> checkAste = astaRepository.filtraPerParolaChiave(chiave, StatoAsta.ATTIVA);
		asteTrovate = riempiListaAste(checkAste, asteTrovate);
		return asteTrovate;
	}

	@Override
	public List<AstaDTO> trovaAstePerCategoria(Categoria categoria) {
		List<AstaDTO> asteTrovate = new ArrayList<>();
		List<Asta> checkAste = astaRepository.filtraPerCategoria(categoria, StatoAsta.ATTIVA);
		asteTrovate = riempiListaAste(checkAste, asteTrovate);
		return asteTrovate;
	}

	@Override
	public List<AstaDTO> trovaAstePerParolaChiaveAndCategoria(String chiave, Categoria categoria) {
		List<AstaDTO> asteTrovate = new ArrayList<>();
		List<Asta> checkAste = astaRepository.filtraPerCategoriaAndParoleChiave(chiave, categoria, StatoAsta.ATTIVA);
		asteTrovate = riempiListaAste(checkAste, asteTrovate);
		return asteTrovate;
	}
	
	private AstaDTO creaAstaDTO(Asta asta) {
		AstaDTO astaDTO = new AstaDTO();
		astaDTO.setNome(asta.getNome());
		astaDTO.setId(asta.getId());
		astaDTO.setIdCreatore(asta.getIdCreatore());
		astaDTO.setCategoria(asta.getCategoria());
		astaDTO.setDescrizione(asta.getDescrizione());
		astaDTO.setFoto(asta.getFoto());
		astaDTO.setStato(asta.getStato());
		astaDTO.setVincitore(asta.getVincitore());
		return astaDTO;
	}
	
	public Asta_InversaDTO trovaAstaInversa(Integer id) {
        AstaInversa astaInversa = astaInversaRepository.getAstaInversa(id);
        if (astaInversa == null) {
            return null;
        }
        return creaAstaInversaDTO(astaInversa);
    }

    public Asta_RibassoDTO trovaAstaRibasso(Integer id) {
        AstaRibasso astaRibasso = astaAlRibassoRepository.getAstaAlRibasso(id);
        if (astaRibasso == null) {
            return null;
        }
        return creaAstaAlRibassoDTO(astaRibasso);
    }

    public Asta_SilenziosaDTO trovaAstaSilenziosa(Integer id) {
        AstaSilenziosa astaSilenziosa = astaSilenziosaRepository.getAstaSilenziosa(id);
        if (astaSilenziosa == null) {
            return null;
        }
        return creaAstaSilenziosaDTO(astaSilenziosa);
    }
    
    private Asta_InversaDTO creaAstaInversaDTO(AstaInversa asta) {
		Asta_InversaDTO astaDTO = new Asta_InversaDTO();
		astaDTO.setPrezzo(asta.getPrezzo());
		if(asta.getOffertaMinore() != null) {
			astaDTO.setOffertaMinore(asta.getOffertaMinore());
		}
		astaDTO.setScadenza(asta.getScadenza());
		return astaDTO;
	}
    
    private Asta_RibassoDTO creaAstaAlRibassoDTO(AstaRibasso asta) {
		Asta_RibassoDTO astaDTO = new Asta_RibassoDTO();
		astaDTO.setPrezzo(asta.getPrezzo());
		astaDTO.setMinimo(asta.getMinimo());
		astaDTO.setDecremento(asta.getDecremento());
		astaDTO.setTimer(asta.getTimer());
		return astaDTO;
	}
    
    private Asta_SilenziosaDTO creaAstaSilenziosaDTO(AstaSilenziosa asta) {
		Asta_SilenziosaDTO astaDTO = new Asta_SilenziosaDTO();
		astaDTO.setScadenza(asta.getScadenza());
		return astaDTO;
	}
    
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void scadenzaAsteInverse() {
    	List<AstaInversa> asteAttive = astaInversaRepository.cercaAsteInverse(StatoAsta.ATTIVA);
    	
    	for(AstaInversa asta : asteAttive) {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    		LocalDateTime scadenzaTime = LocalDateTime.parse(asta.getScadenza(), formatter);
    		
    		if(!scadenzaTime.isAfter(LocalDateTime.now())) {
    			asta.setStato(StatoAsta.FALLITA);
    			astaInversaRepository.save(asta);
    		}
    	}
    }
    
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void scadenzaAsteSilenziose() {
    	List<AstaSilenziosa> asteAttive = astaSilenziosaRepository.cercaAsteSilenziose(StatoAsta.ATTIVA);
    	
    	for(AstaSilenziosa asta : asteAttive) {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    		LocalDateTime scadenzaTime = LocalDateTime.parse(asta.getScadenza(), formatter);
    		
    		if(!scadenzaTime.isAfter(LocalDateTime.now())) {
    			asta.setStato(StatoAsta.FALLITA);
    			astaSilenziosaRepository.save(asta);
    		}
    	}
    }
    
    @Scheduled(fixedRate = 1000)
    @Transactional
    public void timerAsteRibasso() {
    	List<AstaRibasso> asteAttive = astaAlRibassoRepository.cercaAsteRibasso(StatoAsta.ATTIVA);
    	    	
    	for(AstaRibasso asta : asteAttive) {    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime timer = LocalTime.parse(asta.getTimer(), formatter);
            
            timer = timer.minusSeconds(1);
            
            if(timer.equals(LocalTime.MIDNIGHT)) {
            	decrementaPrezzo(asta);
            	timer = LocalTime.parse(asta.getTimerIniziale(), formatter);
            }
            
            asta.setTimer(timer.format(formatter));
            astaAlRibassoRepository.save(asta);
    	}
    }
    
    private void decrementaPrezzo(AstaRibasso asta) {
        float nuovoPrezzo = asta.getPrezzo() - asta.getDecremento();
        if (nuovoPrezzo >= asta.getMinimo()) {
            asta.setPrezzo(nuovoPrezzo);
        } else {
            asta.setStato(StatoAsta.FALLITA);
        }    
        astaAlRibassoRepository.save(asta);
    }
    
    private List<AstaDTO> riempiListaAste(List<Asta> checkAste, List<AstaDTO> asteTrovate) {
    	if (!checkAste.isEmpty()) {
			for (Asta a : checkAste) {
				AstaDTO astaDTO = creaAstaDTO(a);
				if(a instanceof AstaRibasso) {
					astaDTO.setTipo("RIBASSO");
				} else if (a instanceof AstaSilenziosa) {
					astaDTO.setTipo("SILENZIOSA");
				} else if (a instanceof AstaInversa) {
					astaDTO.setTipo("INVERSA");
				}
				asteTrovate.add(astaDTO);
			}
		}
    	return asteTrovate;
    }
}
