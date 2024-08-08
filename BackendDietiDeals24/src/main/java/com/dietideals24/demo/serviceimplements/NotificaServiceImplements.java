package com.dietideals24.demo.serviceimplements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dietideals24.demo.models.Notifica;
import com.dietideals24.demo.models.dto.NotificaDTO;
import com.dietideals24.demo.repository.NotificaRepository;
import com.dietideals24.demo.service.NotificaService;

@Service("NotificaService")
public class NotificaServiceImplements implements NotificaService {
	
	@Autowired
	private NotificaRepository notificaRepository;

	@Override
	public List<NotificaDTO> getNotifiche(int idUtente) {
		List<Notifica> notifiche = notificaRepository.trovaNotifiche(idUtente);

		List<NotificaDTO> notificheDTO = new ArrayList<>();
		for (Notifica n : notifiche) {
			NotificaDTO notificaDTO = creaNotificaDTO(n);
			notificheDTO.add(notificaDTO);
		}
		return notificheDTO;
	}

	@Override
	public List<NotificaDTO> getNotificheLette(int idUtente) {
		List<Notifica> notifiche = notificaRepository.trovaNotificheLette(idUtente);

		List<NotificaDTO> notificheDTO = new ArrayList<>();
		for (Notifica n : notifiche) {
			NotificaDTO notificaDTO = creaNotificaDTO(n);
			notificheDTO.add(notificaDTO);
		}
		return notificheDTO;
	}

	@Override
	public List<NotificaDTO> getNotificheNonLette(int idUtente) {
		List<Notifica> notifiche = notificaRepository.trovaNotificheNonLette(idUtente);

		List<NotificaDTO> notificheDTO = new ArrayList<>();
		for (Notifica n : notifiche) {
			NotificaDTO notificaDTO = creaNotificaDTO(n);
			notificheDTO.add(notificaDTO);
		}
		return notificheDTO;
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
	
	private NotificaDTO creaNotificaDTO(Notifica notifica) {
		NotificaDTO notificaDTO = new NotificaDTO();
		notificaDTO.setId(notifica.getId());
		notificaDTO.setId_utente(notifica.getId_utente());
		if(notifica.getId_asta() != null) {
			notificaDTO.setId_asta(notifica.getId_asta());
		} else {
			notificaDTO.setId_asta(0);
		}
		notificaDTO.setTesto(notifica.getTesto());
		notificaDTO.setData(notifica.getData());
		notificaDTO.setLetta(notifica.isLetta());
		return notificaDTO;
	}
}
