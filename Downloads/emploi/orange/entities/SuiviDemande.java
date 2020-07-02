package com.orange.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SuiviDemande {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_suivi;
	
	//la demande qu'on va faire le suivi
	@ManyToOne(targetEntity = Demande.class)
	private Demande demander;
	
	@ManyToOne(targetEntity = User.class)
	@JsonIgnore
	private User user;
	private String dernier_modif;
	
	
	public SuiviDemande() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SuiviDemande(Demande demander, User user, String dernier_modif) {
		super();
		this.demander = demander;
		this.user = user;
		this.dernier_modif = dernier_modif;
	}
	
	public Demande getDemander() {
		return demander;
	}
	public void setDemander(Demande demander) {
		this.demander = demander;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDernier_modif() {
		return dernier_modif;
	}
	public void setDernier_modif(String dernier_modif) {
		this.dernier_modif = dernier_modif;
	}
	
}
