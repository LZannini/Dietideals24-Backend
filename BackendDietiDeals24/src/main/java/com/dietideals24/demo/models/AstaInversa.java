package com.dietideals24.demo.models;

import com.dietideals24.demo.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "asta_inversa")
@PrimaryKeyJoinColumn(name = "id")
public class AstaInversa extends Asta {
	
	private float prezzo;
	private Float offertaMinore;
	private String scadenza;
	
	public AstaInversa(int idCreatore, String nome, String descrizione, Categoria categoria, byte[] foto, float prezzo, String scadenza) {
		super(idCreatore, nome, descrizione, categoria, foto);
		this.prezzo = prezzo;
		this.scadenza = scadenza; 
	}
	
	public AstaInversa() {
		
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public Float getOffertaMinore() {
		return offertaMinore;
	}

	public void setOffertaMinore(Float offertaMinore) {
		this.offertaMinore = offertaMinore;
	}
}
