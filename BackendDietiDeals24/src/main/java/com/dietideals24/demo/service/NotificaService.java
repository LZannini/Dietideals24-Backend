package com.dietideals24.demo.service;

import java.util.List;

import com.dietideals24.demo.models.dto.NotificaDTO;

public interface NotificaService {
	
	List<NotificaDTO> getNotifiche(int idUtente);
	List<NotificaDTO> getNotificheLette(int idUtente);
	List<NotificaDTO> getNotificheNonLette(int idUtente);
	void setNotificaAsLetta(int id);
	void setAllNotificheAsLette(int idUtente);
	void rimuoviNotifica(int id);
	void rimuoviNotifiche(int idUtente);
	void rimuoviNotificheLette(int idUtente);
}
