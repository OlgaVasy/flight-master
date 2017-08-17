package com.cooksys.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookedFlight")
public class BookedFlight {
		
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Flight flight;	
	
	@ManyToOne
	private UserAcc client;
	
	private Boolean isDeleted = false;

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		BookedFlight other = (BookedFlight) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public UserAcc getClient() {
		return client;
	}

	public void setClient(UserAcc client) {
		this.client = client;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
	
	
	
	
