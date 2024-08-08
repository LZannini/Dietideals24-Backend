package com.dietideals24.demo.serviceimplements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dietideals24.demo.models.Notifica;
import com.dietideals24.demo.models.dto.NotificaDTO;
import com.dietideals24.demo.repository.NotificaRepository;
import com.dietideals24.demo.service.NotificaService;

@Service("notificaService")
public class NotificaServiceImplements implements NotificaService {
	
	private final NotificaRepository notificaRepository;
	
	public NotificaServiceImplements(NotificaRepository notificaRepository) {
		this.notificaRepository = notificaRepository;
	}

	@Override
	public List<NotificaDTO> getNotifiche(int idUtente) {
		List<Notifica> notifiche = notificaRepository.trovaNotifiche(idUtente);
		return riempiListaNotifiche(notifiche);	
	}

	@Override
	public List<NotificaDTO> getNotificheLette(int idUtente) {
		List<Notifica> notifiche = notificaRepository.trovaNotificheLette(idUtente);
		return riempiListaNotifiche(notifiche);	
	}

	@Override
	public List<NotificaDTO> getNotificheNonLette(int idUtente) {
		List<Notifica> notifiche = notificaRepository.trovaNotificheNonLette(idUtente);
		return riempiListaNotifiche(notifiche);		
	}
	
	@Override
	public void setAllNotificheAsLette(int idUtente) {
		notificaRepository.segnaAllNotificheComeLette(idUtente);
	}
	
	@Override
	public void setNotificaAsLetta(int id) {
		notificaRepository.segnaNotificaComeLetta(id);
	}
	
	@Override
	public void rimuoviNotifica(int id) {
		notificaRepository.eliminaNotifica(id);
	}

	@Override
	public void rimuoviNotifiche(int idUtente) {
		notificaRepository.eliminaNotifiche(idUtente);
	}

	@Override
	public void rimuoviNotificheLette(int idUtente) {
		notificaRepository.eliminaNotificheLette(idUtente);
	}
	
	private List<NotificaDTO> riempiListaNotifiche(List<Notifica> notifiche) {
		List<NotificaDTO> notificheDTO = new ArrayList<>();
		for (Notifica n : notifiche) {
			NotificaDTO notificaDTO = creaNotificaDTO(n);
			notificheDTO.add(notificaDTO);
		}
		return notificheDTO;
	}
	
	private NotificaDTO creaNotificaDTO(Notifica notifica) {
		NotificaDTO notificaDTO = new NotificaDTO();
		notificaDTO.setId(notifica.getId());
		notificaDTO.setIdUtente(notifica.getIdUtente());
		if(notifica.getIdAsta() != null) {
			notificaDTO.setIdAsta(notifica.getIdAsta());
		} else {
			notificaDTO.setIdAsta(0);
		}
		notificaDTO.setTesto(notifica.getTesto());
		notificaDTO.setData(notifica.getData());
		notificaDTO.setLetta(notifica.isLetta());
		return notificaDTO;
	}
	
}
