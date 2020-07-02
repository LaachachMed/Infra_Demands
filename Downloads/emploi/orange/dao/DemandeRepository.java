package com.orange.dao;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orange.entities.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long>  {
  @Transactional
  @Modifying
  @Query("UPDATE Demande SET ETAT = :x WHERE ID_DEMANDE = :i")
  public void changerEtat(@Param("i")Long id,@Param("x")int etat);
  
  //affichage des demandes effecter par date descendante  
  @Query("SELECT d from Demande d WHERE USER_ID_SESSION = :id order by d.date_demande desc")
  public ArrayList<Demande>AllUserDemandes(@Param("id")Long id);
  //affichage des demande par un client
  @Query("SELECT d FROM Demande d,User u WHERE d.user.id_session = u.id_session order by d.user.id_session")
  public ArrayList<Demande> getAllDemandesByUsers();
  
  //nbr des demande par utilisateur
  @Query("SELECT distinct(d.user.nom_complet), count(d.id_demande) FROM Demande d, User u WHERE d.user.id_session = u.id_session group by d.user.nom_complet")
  public ArrayList<String> getStates();
  
  //statistiques des demande pour chaque client
  @Query("SELECT d.user.id_session,count(d.id_demande),MAX(d.date_demande) from Demande d WHERE d.etat != 0 and d.etat != 6 group by d.user.id_session")
  public ArrayList<String> getStatisques();
  
  //chercher par mot clé
  @Query("SELECT d FROM Demande d WHERE d.nom_projet like :x or d.object_projet like :x or d.user.nom_complet like :x")
  public Page<Demande> chercher(@Param("x")String mc , Pageable pageable);
  
public Demande save(Optional<Demande> d);


}
