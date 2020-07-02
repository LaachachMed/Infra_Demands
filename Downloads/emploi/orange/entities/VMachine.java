package com.orange.entities;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="VMACHINE")
@Inheritance(strategy = InheritanceType.JOINED)
public class VMachine {
	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id_machine; 
	 @ManyToOne(targetEntity = Demande.class)
	 @JsonIgnore
	 private Demande demande;
	 private String nom_machine;
	 private String os_vers;
	 private String ram;
	 private String vcpu;
	 private String	stockage;
	 private String Envie;
	 private String type_app;
	 private String type_bd;
	 private String serveur_app;
	 private String addressage;
	 private String date_fin_vali;
	 private String rto;
	 private String replication_second;
	 @Temporal(TemporalType.DATE)
	 private Date date_mise_produc;	 
	 @OneToMany(targetEntity= Files.class)
	 private Set<FileSystem> filesystem = new TreeSet<>(); 
	 private String nom_machine_rep;
	 private String os_vers_rep;
	 private String ram_rep;
	 private String vcpu_rep;
	 private String stockage_rep;
	 private String mode_rep;
	 private String compte;
	 private String privil;
	 private String vmname;
	 private String vm_cluster;
	 private String ip_adress;
	 private String vlan_id;
	 private String vm_netmask;
	 private String vm_getway;
	 private String vm_wwn;
	 private String vm_site;
	 private String vm_commentaire;

	public VMachine() {
		super();
		// TODO Auto-generated constructor stub
	}

public VMachine(Demande demande, String nom_machine, String os_vers, String ram, String vcpu, String stockage,
		String envie, String type_app, String type_bd, String serveur_app, String addressage, String date_fin_vali,
		String rto, String replication_second, Date date_mise_produc, Set<FileSystem> filesystem,
		String nom_machine_rep, String os_vers_rep, String ram_rep, String vcpu_rep, String stockage_rep,
		String mode_rep, String compte, String privil, String vmname, String vm_cluster, String ip_adress,
		String vlan_id, String vm_netmask, String vm_getway, String vm_wwn, String vm_site, String vm_commentaire) {
	super();
	this.demande = demande;
	this.nom_machine = nom_machine;
	this.os_vers = os_vers;
	this.ram = ram;
	this.vcpu = vcpu;
	this.stockage = stockage;
	Envie = envie;
	this.type_app = type_app;
	this.type_bd = type_bd;
	this.serveur_app = serveur_app;
	this.addressage = addressage;
	this.date_fin_vali = date_fin_vali;
	this.rto = rto;
	this.replication_second = replication_second;
	this.date_mise_produc = date_mise_produc;
	this.filesystem = filesystem;
	this.nom_machine_rep = nom_machine_rep;
	this.os_vers_rep = os_vers_rep;
	this.ram_rep = ram_rep;
	this.vcpu_rep = vcpu_rep;
	this.stockage_rep = stockage_rep;
	this.mode_rep = mode_rep;
	this.compte = compte;
	this.privil = privil;
	this.vmname = vmname;
	this.vm_cluster = vm_cluster;
	this.ip_adress = ip_adress;
	this.vlan_id = vlan_id;
	this.vm_netmask = vm_netmask;
	this.vm_getway = vm_getway;
	this.vm_wwn = vm_wwn;
	this.vm_site = vm_site;
	this.vm_commentaire = vm_commentaire;
}

public Long getId_machine() {
	return id_machine;
}
public void setId_machine(Long id_machine) {
	this.id_machine = id_machine;
}
public Demande getDemande() {
	return demande;
}
public void setDemande(Demande demande) {
	this.demande = demande;
}
public String getNom_machine() {
	return nom_machine;
}
public void setNom_machine(String nom_machine) {
	this.nom_machine = nom_machine;
}
public String getOs_vers() {
	return os_vers;
}
public void setOs_vers(String os_vers) {
	this.os_vers = os_vers;
}
public String getRam() {
	return ram;
}
public void setRam(String ram) {
	this.ram = ram;
}
public String getVcpu() {
	return vcpu;
}
public void setVcpu(String vcpu) {
	this.vcpu = vcpu;
}
public String getStockage() {
	return stockage;
}
public void setStockage(String stockage) {
	this.stockage = stockage;
}
public String getEnvie() {
	return Envie;
}
public void setEnvie(String envie) {
	Envie = envie;
}
public String getType_app() {
	return type_app;
}
public void setType_app(String type_app) {
	this.type_app = type_app;
}
public String getType_bd() {
	return type_bd;
}
public void setType_bd(String type_bd) {
	this.type_bd = type_bd;
}
public String getServeur_app() {
	return serveur_app;
}
public void setServeur_app(String serveur_app) {
	this.serveur_app = serveur_app;
}
public String getAddressage() {
	return addressage;
}
public void setAddressage(String addressage) {
	this.addressage = addressage;
}
public String getDate_fin_vali() {
	return date_fin_vali;
}
public void setDate_fin_vali(String date_fin_vali) {
	this.date_fin_vali = date_fin_vali;
}
public String getRto() {
	return rto;
}
public void setRto(String rto) {
	this.rto = rto;
}
public String getReplication_second() {
	return replication_second;
}
public void setReplication_second(String replication_second) {
	this.replication_second = replication_second;
}
public Date getDate_mise_produc() {
	return date_mise_produc;
}
public void setDate_mise_produc(Date date_mise_produc) {
	this.date_mise_produc = date_mise_produc;
}

public Set<FileSystem> getFilesystem() {
	return filesystem;
}

public void setFilesystem(Set<FileSystem> filesystem) {
	this.filesystem = filesystem;
}

public String getNom_machine_rep() {
	return nom_machine_rep;
}
public void setNom_machine_rep(String nom_machine_rep) {
	this.nom_machine_rep = nom_machine_rep;
}
public String getOs_vers_rep() {
	return os_vers_rep;
}
public void setOs_vers_rep(String os_vers_rep) {
	this.os_vers_rep = os_vers_rep;
}
public String getRam_rep() {
	return ram_rep;
}
public void setRam_rep(String ram_rep) {
	this.ram_rep = ram_rep;
}
public String getVcpu_rep() {
	return vcpu_rep;
}
public void setVcpu_rep(String vcpu_rep) {
	this.vcpu_rep = vcpu_rep;
}
public String getStockage_rep() {
	return stockage_rep;
}
public void setStockage_rep(String stockage_rep) {
	this.stockage_rep = stockage_rep;
}
public String getMode_rep() {
	return mode_rep;
}
public void setMode_rep(String mode_rep) {
	this.mode_rep = mode_rep;
}
public String getCompte() {
	return compte;
}
public void setCompte(String compte) {
	this.compte = compte;
}
public String getPrivil() {
	return privil;
}
public void setPrivil(String privil) {
	this.privil = privil;
}
public String getVmname() {
	return vmname;
}
public void setVmname(String vmname) {
	this.vmname = vmname;
}
public String getVm_cluster() {
	return vm_cluster;
}
public void setVm_cluster(String vm_cluster) {
	this.vm_cluster = vm_cluster;
}
public String getIp_adress() {
	return ip_adress;
}
public void setIp_adress(String ip_adress) {
	this.ip_adress = ip_adress;
}
public String getVlan_id() {
	return vlan_id;
}
public void setVlan_id(String vlan_id) {
	this.vlan_id = vlan_id;
}
public String getVm_netmask() {
	return vm_netmask;
}
public void setVm_netmask(String vm_netmask) {
	this.vm_netmask = vm_netmask;
}
public String getVm_getway() {
	return vm_getway;
}
public void setVm_getway(String vm_getway) {
	this.vm_getway = vm_getway;
}
public String getVm_wwn() {
	return vm_wwn;
}
public void setVm_wwn(String vm_wwn) {
	this.vm_wwn = vm_wwn;
}
public String getVm_site() {
	return vm_site;
}
public void setVm_site(String vm_site) {
	this.vm_site = vm_site;
}
public String getVm_commentaire() {
	return vm_commentaire;
}
public void setVm_commentaire(String vm_commentaire) {
	this.vm_commentaire = vm_commentaire;
}
	

	
}
