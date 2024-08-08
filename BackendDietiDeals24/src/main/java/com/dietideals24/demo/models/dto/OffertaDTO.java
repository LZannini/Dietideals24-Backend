package com.dietideals24.demo.models.dto;

import java.io.Serializable;
import com.dietideals24.demo.enums.StatoOfferta;

@SuppressWarnings("serial")
public class OffertaDTO implements Serializable {
	
	private int id;
	private int idUtente;
	private int idAsta;
	private float valore;
	private String data;
	private String offerente;
	private StatoOfferta stato;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId_utente() {
		return idUtente;
	}

	public void setId_utente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getId_asta() {
		return idAsta;
	}

	public void setId_asta(int idAsta) {
		this.idAsta = idAsta;
	}

	public float getValore() {
		return valore;
	}

	public void setValore(float valore) {
		this.valore = valore;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOfferente() {
		return offerente;
	}

	public void setOfferente(String offerente) {
		this.offerente = offerente;
	}

	public StatoOfferta getStato() {
		return stato;
	}

	public void setStato(StatoOfferta stato) {
		this.stato = stato;
	}
}
