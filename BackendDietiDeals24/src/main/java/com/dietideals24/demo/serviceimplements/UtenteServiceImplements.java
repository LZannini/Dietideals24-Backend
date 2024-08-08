package com.dietideals24.demo.serviceimplements;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dietideals24.demo.models.Utente;
import com.dietideals24.demo.models.dto.UtenteDTO;
import com.dietideals24.demo.repository.UtenteRepository;
import com.dietideals24.demo.service.UtenteService;

@Service("UtenteService")
public class UtenteServiceImplements implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UtenteServiceImplements(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	public UtenteDTO registraUtente(UtenteDTO utenteDTO) {
        utenteDTO.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
		utenteRepository.save(UtenteServiceImplements.creaUtente(utenteDTO));
			
        java.util.Optional<Utente> checkUtente = utenteRepository.findByEmailAndPassword(utenteDTO.getEmail(), utenteDTO.getPassword());
        if (checkUtente.isPresent()) {
        	Utente utente = checkUtente.get();
            utenteDTO.setId(utente.getId());
            
        }
        
        return utenteDTO;
	}
	
	@Override
	public UtenteDTO loginUtente(UtenteDTO utenteDTO) {
	    java.util.Optional<Utente> checkUtente = utenteRepository.findByEmail(utenteDTO.getEmail());
	    if (!checkUtente.isPresent() || !passwordEncoder.matches(utenteDTO.getPassword(), checkUtente.get().getPassword())) {
	        throw new IllegalArgumentException("Utente non trovato o password errata!");
	    } else {
	        Utente utente = checkUtente.get();
	        return creaUtenteDTO(utente);
	    }
	}
	

	@Override
	public UtenteDTO updateUtente(UtenteDTO utenteDTO) {
		utenteRepository.save(UtenteServiceImplements.aggiornaUtente(utenteDTO));
		
		return utenteDTO;
	}
	
	public UtenteDTO modificaPassword(int id, String password) {
		utenteRepository.updatePassword(password, id);
		
		java.util.Optional<Utente> utenteModificato = utenteRepository.findById(id);
		if(!utenteModificato.isPresent()) {
			throw new IllegalArgumentException("Utente non trovato!");
		} else {
			Utente utente = utenteModificato.get();
			return UtenteServiceImplements.creaUtenteDTO(utente);
		}
	}
	
	private static Utente aggiornaUtente(UtenteDTO utenteDTO) {
		Utente utente = new Utente();
		utente.setId(utenteDTO.getId());
		utente.setUsername(utenteDTO.getUsername());
		utente.setEmail(utenteDTO.getEmail());
		utente.setPassword(utenteDTO.getPassword());
		utente.setBiografia(utenteDTO.getBiografia());
		utente.setPaese(utenteDTO.getPaese());
		utente.setSitoweb(utenteDTO.getSitoweb());
		utente.setAvatar(utenteDTO.getAvatar());
		utente.setTipo(utenteDTO.getTipo());
		
		return utente;
	}
	
	@Override
	public UtenteDTO recuperaUtente(int id, String email) {
		java.util.Optional<Utente> checkUtente = utenteRepository.findByEmailAndId(email, id);
		Utente utente = null;
		UtenteDTO utenteDTO = null;
		
		if (checkUtente.isPresent())
			utente = checkUtente.get();
		
		if (utente != null) {
			utenteDTO = creaUtenteDTO(utente);
		}
		return utenteDTO;
	}
	
	@Override
	public UtenteDTO recuperaUtenteById(int id) {
		java.util.Optional<Utente> checkUtente = utenteRepository.findById(id);
		Utente utente = null;
		UtenteDTO utenteDTO = null;
		
		if (checkUtente.isPresent())
			utente = checkUtente.get();
		
		if (utente != null) {
			utenteDTO = creaUtenteDTO(utente);
		}
		return utenteDTO;
	}
	
	private static Utente creaUtente(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setUsername(utenteDTO.getUsername());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());
        utente.setTipo(utenteDTO.getTipo());

        return utente;
    }
	
	private static UtenteDTO creaUtenteDTO(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utente.getId());
        utenteDTO.setUsername(utente.getUsername());
        utenteDTO.setEmail(utente.getEmail());
        utenteDTO.setPassword(utente.getPassword());
        utenteDTO.setBiografia(utente.getBiografia());
        utenteDTO.setPaese(utente.getPaese());
        utenteDTO.setSitoweb(utente.getSitoweb());
        utenteDTO.setTipo(utente.getTipo());
        utenteDTO.setAvatar(utente.getAvatar());

        return utenteDTO;
    }

	@Override
	public Utente recuperaUtenteByEmail(String email) {
		Optional<Utente> u = utenteRepository.findByEmail(email);
		Utente utente = null;
		
		if(u.isPresent()) {
			utente = u.get();
		}
		
		return utente;
	}

	
}
