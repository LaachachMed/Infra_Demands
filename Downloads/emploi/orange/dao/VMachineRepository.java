package com.orange.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orange.entities.VMachine;


@Repository
public interface VMachineRepository extends JpaRepository<VMachine,Long> {
	 //selectionner les VMachine qui ont appartient a une demande spécifique
	 @Query("SELECT v FROM VMachine v WHERE DEMANDE_ID_DEMANDE = :id")
	 public ArrayList<VMachine> getAllMachines(@Param("id")Long id_demande);
	 //selectionner la demande ainsi que le nombre de VMachine d'une demande specifique
	 @Query("SELECT v.demande.id_demande,COUNT(v.nom_machine) FROM VMachine v , Demande d where d.id_demande = v.demande.id_demande group by v.demande.id_demande")
	 public ArrayList<String> getNbrVMachine();
	 //selectionner une VMachine par son ID
	 @Query("SELECT v FROM VMachine v WHERE ID_MACHINE = :id")
	 public VMachine getMachine(@Param("id")Long id_machine);
	//suppression d'une VMachine
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM VMachine WHERE DEMANDE_ID_DEMANDE = :id_demande") 
	 public int deleteVMachine(@Param("id_demande")Long id_d);
 
}
