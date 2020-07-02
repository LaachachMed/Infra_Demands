package com.orange.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_service;
	private String nom_role;
	public Role(String nom_role) {
		super();
	
		this.nom_role = nom_role;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId_service() {
		return id_service;
	}
	public void setId_service(Long id_service) {
		this.id_service = id_service;
	}
	public String getNom_role() {
		return nom_role;
	}
	public void setNom_role(String nom_role) {
		this.nom_role = nom_role;
	}
	
}
