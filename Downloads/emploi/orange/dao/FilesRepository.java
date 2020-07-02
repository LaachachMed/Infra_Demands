package com.orange.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orange.entities.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files,Long> {
	//suppression du document d'une demande
	@Transactional
	@Modifying
	@Query("DELETE FROM Files WHERE DEMANDE_ID_DEMANDE = :id_demande")
	public int deleteFiles(@Param("id_demande")Long id_d);
	
	//affichage du document d'une demande
	@Query("SELECT f FROM Files f WHERE DEMANDE_ID_DEMANDE = :id_demande")
	public Files getAllFiles(@Param("id_demande")Long id_d);
}
