package com.orange.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MOTIF")
public class Motif implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_motif;
	@ManyToOne(targetEntity = Demande.class)
	@JsonIgnore
	private Demande demander;
	@Temporal(TemporalType.DATE)
	private Date date_motif;
	@ManyToOne(targetEntity = User.class)
	@JsonIgnore
	private User user;
	private String motif;
	
	
	
	public Motif() {
		super(); 
	}

	public Motif(User user, Demande demande,String motif, Date date_motif) {
		super();
		this.user = user;
		this.demander = demande;
		this.motif = motif;
		this.date_motif = date_motif;
	}

	public Long getId_motif() {
		return id_motif;
	}

	public void setId_motif(Long id_motif) {
		this.id_motif = id_motif;
	}

	public User getMotifier() {
		return (User) user;
	}

	
	public Demande getDemander() {
		return (Demande) demander;
	}


	
	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Date getDate_motif() {
		return date_motif;
	}

	public void setDate_motif(Date date_motif) {
		this.date_motif = date_motif;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDemander(Demande demander) {
		this.demander = demander;
	}
	
	
}
