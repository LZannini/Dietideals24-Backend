package com.dietideals24.demo.models;

import com.dietideals24.demo.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "asta_silenziosa")
@PrimaryKeyJoinColumn(name = "id")
public class AstaSilenziosa extends Asta {
	
	private String scadenza;
	
	public AstaSilenziosa(int idCreatore, String nome, String descrizione, Categoria categoria, byte[] foto, String scadenza) {
		super(idCreatore, nome, descrizione, categoria, foto);
		this.scadenza = scadenza;
	}
	
	public AstaSilenziosa() {}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

}
