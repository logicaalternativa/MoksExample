package com.logicaalternativa.ejemplomock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="promotion")
public class Promotion {
	
	@Id
	@Column(name = "id_promotion", unique = true, nullable = false)
	private Integer idPromotion;

	@Column(name = "description", nullable = false)
	private String description;
	
	
	public Integer getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Promotion [idPromotion=");
		builder.append(idPromotion);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((idPromotion == null) ? 0 : idPromotion.hashCode());
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
		Promotion other = (Promotion) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idPromotion == null) {
			if (other.idPromotion != null)
				return false;
		} else if (!idPromotion.equals(other.idPromotion))
			return false;
		return true;
	}

	
}
