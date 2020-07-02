package com.orange.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FileSystem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_fs;
	@ManyToOne(targetEntity = VMachine.class)
	private VMachine vmachine;
	private String fs;
	private String taille;
	public FileSystem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileSystem(VMachine vmachine, String fs, String taille) {
		super();
		this.vmachine = vmachine;
		this.fs = fs;
		this.taille = taille;
	}
	public Long getId_fs() {
		return id_fs;
	}
	public void setId_fs(Long id_fs) {
		this.id_fs = id_fs;
	}
	public VMachine getVmachine() {
		return vmachine;
	}
	public void setVmachine(VMachine vmachine) {
		this.vmachine = vmachine;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	
	
	

}
