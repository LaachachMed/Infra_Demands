package com.orange.entities;


import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DEMANDE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Demande{

 @Id 
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id_demande;
 
 @ManyToOne(targetEntity = User.class)
 private User user;
 //private Long id_session;
 @OneToMany(cascade=CascadeType.ALL,targetEntity= VMachine.class)
 @JsonIgnore
 private Set<VMachine> vmachine = new TreeSet<>();
 private String nom_projet;
 private String object_projet;
 @OneToMany(cascade=CascadeType.ALL,targetEntity= Files.class)
 @JsonIgnore
 private Set <Files> files = new TreeSet<>();
 private int etat; //etat du demande
 @OneToMany(cascade=CascadeType.ALL,targetEntity = Motif.class)
 @JsonIgnore
 private Set<Motif> motif = new TreeSet<>();
 private String porteur_pro;
 private String per_client;
 private String date_demande;
 private int etat2; // avancement du demande
 private String user_etape;
public Demande() {
	super();
	// TODO Auto-generated constructor stub
}


public Demande(User user, Set<VMachine> vmachine, String nom_projet, String object_projet, Set<Files> files, int etat,
		Set<Motif> motif, String porteur_pro, String per_client, String date_demande, int etat2, String user_etape) {
	super();
	this.user = user;
	this.vmachine = vmachine;
	this.nom_projet = nom_projet;
	this.object_projet = object_projet;
	this.files = files;
	this.etat = etat;
	this.motif = motif;
	this.porteur_pro = porteur_pro;
	this.per_client = per_client;
	this.date_demande = date_demande;
	this.etat2 = etat2;
	this.user_etape = user_etape;
}


public Long getId_demande() {
	return id_demande;
}
public void setId_demande(Long id_demande) {
	this.id_demande = id_demande;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Set<VMachine> getVmachine() {
	return vmachine;
}
public void setVmachine(Set<VMachine> vmachine) {
	this.vmachine = vmachine;
}
public String getNom_projet() {
	return nom_projet;
}
public void setNom_projet(String nom_projet) {
	this.nom_projet = nom_projet;
}
public String getObject_projet() {
	return object_projet;
}
public void setObject_projet(String object_projet) {
	this.object_projet = object_projet;
}
public Set<Files> getFiles() {
	return files;
}
public void setFiles(Set<Files> files) {
	this.files = files;
}
public int getEtat() {
	return etat;
}
public void setEtat(int etat) {
	this.etat = etat;
}
public Set<Motif> getMotif() {
	return motif;
}
public void setMotif(Set<Motif> motif) {
	this.motif = motif;
}
public String getPorteur_pro() {
	return porteur_pro;
}
public void setPorteur_pro(String porteur_pro) {
	this.porteur_pro = porteur_pro;
}
public String getPer_client() {
	return per_client;
}
public void setPer_client(String per_client) {
	this.per_client = per_client;
}
public String getDate_demande() {
	return date_demande;
}
public void setDate_demande(String date_demande) {
	this.date_demande = date_demande;
}

public int getEtat2() {
	return etat2;
}

public void setEtat2(int etat2) {
	this.etat2 = etat2;
}


public String getUser_etape() {
	return user_etape;
}


public void setUser_etape(String user_etape) {
	this.user_etape = user_etape;
}



}
