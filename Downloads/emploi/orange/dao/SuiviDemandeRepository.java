package com.orange.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orange.entities.SuiviDemande;

@Repository
public interface SuiviDemandeRepository extends JpaRepository<SuiviDemande,Long> {
	//suppresion du historique d'une demande
	@Transactional
	@Modifying
	@Query("DELETE FROM SuiviDemande WHERE DEMANDER_ID_DEMANDE = :id_demande") 
	public int deleteSuivi(@Param("id_demande")Long id_d);
}
