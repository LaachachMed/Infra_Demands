package com.orange.entities;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER_TABLE")
public class User {
 @Id 
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id_session;
 
 @OneToMany(targetEntity = Demande.class)
 private Set<Demande> demandes = new TreeSet<>();
 private String login;
 private String mdp_session;
 private String nom_complet;
 @OneToOne(targetEntity = Role.class)
 private Role type;
 @OneToMany(cascade=CascadeType.ALL,targetEntity = Motif.class)
 @JsonIgnore
 private Set<Motif> motifs= new TreeSet<>();
 private String mail;

 public User() {
	super();
	// TODO Auto-generated constructor stub
}




public User(Set<Demande> demandes, String login, String mdp_session, String nom_complet, Role type, Set<Motif> motifs,
		String mail) {
	super();
	this.demandes = demandes;
	this.login = login;
	this.mdp_session = mdp_session;
	this.nom_complet = nom_complet;
	this.type = type;
	this.motifs = motifs;
	this.mail = mail;
}




public Long getId_session() {
	return id_session;
}

public void setId_session(Long id_session) {
	this.id_session = id_session;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getMdp_session() {
	return mdp_session;
}

public void setMdp_session(String mdp_session) {
	this.mdp_session = mdp_session;
}



public String getNom_complet() {
	return nom_complet;
}


public void setNom_complet(String nom_complet) {
	this.nom_complet = nom_complet;
}


public Role getType() {
	return type;
}

public void setType(Role type) {
	this.type = type;
}

public Set<Demande> getDemandes() {
	return demandes;
}

public void setDemandes(Set<Demande> demandes) {
	this.demandes = demandes;
}

public Set<Motif> getMotifs() {
	return motifs;
}

public void setMotifs(Set<Motif> motifs) {
	this.motifs = motifs;
}




public String getMail() {
	return mail;
}


public void setMail(String mail) {
	this.mail = mail;
}


 
}
