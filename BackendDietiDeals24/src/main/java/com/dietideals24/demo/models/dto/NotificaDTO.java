package com.dietideals24.demo.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class NotificaDTO implements Serializable {
	
	private int id;
	private int idUtente;
	private Integer idAsta;
	private String testo;
	private LocalDateTime data;
	private boolean letta;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public boolean isLetta() {
		return letta;
	}
	
	public void setLetta(boolean letta) {
		this.letta = letta;
	}

	public Integer getIdAsta() {
		return idAsta;
	}

	public void setIdAsta(Integer idAsta) {
		this.idAsta = idAsta;
	}
}
