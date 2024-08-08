package com.dietideals24.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notifica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idUtente;
	private Integer idAsta;
	private String testo;
	private LocalDateTime data;
	private boolean letta;
	
	public Notifica(int id, int idUtente, String testo, LocalDateTime data, boolean letta) {
		this.id = id;
		this.idUtente = idUtente;
		this.testo = testo;
		this.data = data;
		this.letta = letta;
	}
		
	public Notifica() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int id_utente) {
		this.idUtente = id_utente;
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
