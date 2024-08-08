package com.dietideals24.demo.models;

import com.dietideals24.demo.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "asta_ribasso")
@PrimaryKeyJoinColumn(name = "id")
public class AstaRibasso extends Asta {
	
	private Float prezzo;
	private String timer;
	private String timerIniziale;
	private Float decremento;
	private Float minimo;
	
	public AstaRibasso(int idCreatore, String nome, String descrizione, Categoria categoria, byte[] foto, float prezzo, String timer, float decremento, float minimo) {
		super(idCreatore, nome, descrizione, categoria, foto);
		this.prezzo = prezzo;
		this.timer = timer;
		this.decremento = decremento;
		this.minimo = minimo;
	}
	
	public AstaRibasso() {}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}
	
	public String getTimerIniziale() {
		return timerIniziale;
	}

	public void setTimerIniziale(String timerIniziale) {
		this.timerIniziale = timerIniziale;
	}

	public float getDecremento() {
		return decremento;
	}

	public void setDecremento(float decremento) {
		this.decremento = decremento;
	}

	public float getMinimo() {
		return minimo;
	}

	public void setMinimo(float minimo) {
		this.minimo = minimo;
	}

}