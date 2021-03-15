package br.edu.ifsc.canoinhas.poo2.NN.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genero {
	@Id
	private String nomegenero;
	private String palavrachave;

	public Genero(String nomegenero, String palavrachave) {
		super();
		this.nomegenero = nomegenero;
		this.palavrachave = palavrachave;
	}
	
	public Genero() {
		
	}

	public String getNomegenero() {
		return nomegenero;
	}

	public void setNomegenero(String nomegenero) {
		this.nomegenero = nomegenero;
	}

	public String getPalavrachave() {
		return palavrachave;
	}

	public void setPalavrachave(String palavrachave) {
		this.palavrachave = palavrachave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomegenero == null) ? 0 : nomegenero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		if (nomegenero == null) {
			if (other.nomegenero != null)
				return false;
		} else if (!nomegenero.equals(other.nomegenero))
			return false;
		return true;
	}

}
