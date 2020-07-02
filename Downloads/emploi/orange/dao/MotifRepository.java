package com.orange.dao;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orange.entities.Motif;
@Repository
public interface MotifRepository extends JpaRepository<Motif,Long> {
	//affichage d'un motif envoyer par un emetteur ( ing. infra ou Manager)
	@Query("SELECT m FROM Motif m WHERE USER_ID_SESSION = :n AND DEMANDER_ID_DEMANDE = :d")
	public ArrayList<Motif> chercherMotif(@Param("n")Long id_n,@Param("d")Long id_d);
	
	//suppresision des motifs d'une demande
	@Transactional
	@Modifying
	@Query("DELETE FROM Motif WHERE DEMANDER_ID_DEMANDE = :id_demande") 
	public int deleteMotif(@Param("id_demande")Long id_d);
}
