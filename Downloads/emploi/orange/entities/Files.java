package com.orange.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Files {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_files;
	
	@ManyToOne(targetEntity = Demande.class)
	private Demande demande;
	private String filetype;
	private String fileExtention;
	private String file_loca;
	
	public Files() {
		super();
	}
	
	public Files(Demande demande, String filetype, String fileExtention, String file_loca) {
		super();
		this.demande = demande;
		this.filetype = filetype;
		this.fileExtention = fileExtention;
		this.file_loca = file_loca;
	}

	public Long getId_files() {
		return id_files;
	}
	public void setId_files(Long id_files) {
		this.id_files = id_files;
	}
	public Demande getDemande() {
		return demande;
	}
	public void setDemande(Demande demande) {
		this.demande = demande;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFileExtention() {
		return fileExtention;
	}
	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getFile_loca() {
		return file_loca;
	}

	public void setFile_loca(String file_loca) {
		this.file_loca = file_loca;
	}
	
	
}
