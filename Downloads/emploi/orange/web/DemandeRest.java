package com.orange.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.orange.dao.DemandeRepository;
import com.orange.dao.FileSystemRepository;
import com.orange.dao.FilesRepository;
import com.orange.dao.MotifRepository;
import com.orange.dao.SuiviDemandeRepository;
import com.orange.dao.UserRepository;
import com.orange.dao.VMachineRepository;
import com.orange.entities.Demande;
import com.orange.entities.FileSystem;
import com.orange.entities.User;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DemandeRest {
	@Autowired
	DemandeRepository demandeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SuiviDemandeRepository suiviDemandeRepository;
	@Autowired
	MotifRepository motifRepository;
	@Autowired
	FilesRepository filesRepository;
	@Autowired
	VMachineRepository vMachineRepository;
	@Autowired
	private FileSystemRepository fileSystemRepository;
	//affichage de tous les demande
	@RequestMapping(value="/demandes",method=RequestMethod.GET)
	public List<Demande> getDemandes(){
		return demandeRepository.findAll();
	}
	//affichage d'une demande specifique
	@RequestMapping(value="/demande/{id}",method=RequestMethod.GET)
	public Demande getDemande(@PathVariable Long id){
		Demande d =demandeRepository.findById(id).get();
		return d;
	
	}
	//affichage des demandes pour un utilisateur
		@RequestMapping(value="/demandes_user/{id}",method= RequestMethod.GET)
		public ArrayList<Demande> getAllUserDemandes(@PathVariable Long id){
			return demandeRepository.AllUserDemandes(id);
		}
	//ajout d'une demande
	@RequestMapping(value="/demande/{id}",method=RequestMethod.POST)
	public Demande saveDemande(@PathVariable Long id,@RequestBody Demande d) throws ParseException{
		User user = userRepository.findById(id).get();
		System.out.println(user.getNom_complet());
		d.setUser(user);
		DateFormat dernier_modif = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		d.setDate_demande(dernier_modif.format(date));
		demandeRepository.save(d);
		return d;
	}
	//enregistre liste demande a la fois
	@RequestMapping(value="/demande_list/{id}",method=RequestMethod.POST)
	public List<Demande> saveDemandes(@PathVariable Long id , @RequestBody List<Demande> d){
		User user = userRepository.findById(id).get();
		int nbr = d.size();
		for(int i =0;i<nbr;i++) {
			d.get(i).setUser(user);
		}
		return demandeRepository.saveAll(d);
	}
	//suppression d'une demande
	@RequestMapping(value="/demande/{id}",method=RequestMethod.DELETE)
	public boolean deleteDemande(@PathVariable Long id){
		ArrayList<FileSystem> f = (ArrayList<FileSystem>) fileSystemRepository.findAll();
		
		System.out.println("thats demande id "+id); 
		//suppression des childs aussi motif / suivi demande
		for(int j=0;j<f.size();j++) {
			if (f.get(j).getVmachine().getDemande().getId_demande() == id) {
				Long id_fs = f.get(j).getId_fs();
				fileSystemRepository.deleteById(id_fs);
				
			} 
		}
		suiviDemandeRepository.deleteSuivi(id);
		
		motifRepository.deleteMotif(id);
		filesRepository.deleteFiles(id);
		
		vMachineRepository.deleteVMachine(id);
		demandeRepository.deleteById(id);
		return true;
	}
	//modification d'une demande
	@RequestMapping(value="/demande/{id}",method=RequestMethod.PUT)
	public Demande modifyDemande(@PathVariable Long id,@RequestBody Demande d){
		d.setId_demande(id);
		return demandeRepository.save(d);
		
	}
	//modifier l'etat d'avancement d'une demande --> ?id=&etat=
	@RequestMapping(value = "/etat_demande/{id_d}/{etat}",method = RequestMethod.PUT)
	public void changerEtat(@PathVariable(value="id_d") Long id, @PathVariable(value="etat") int etat){
			demandeRepository.changerEtat(id,etat);
			}
	//modifier l'etat d'une demande --> ?id=&etat_demande=
		@RequestMapping(value = "/avancement_demande/{id_d}/{etat_demande}",method = RequestMethod.PUT)
		public Demande changerEtatDemande(@PathVariable(value="id_d") Long id, @PathVariable(value="etat_demande") int etat){
			Demande d = demandeRepository.findById(id).get();
			d.setEtat2(etat);
		return demandeRepository.save(d);
			
	}
   //Affichage des demandes selon les clients
	@RequestMapping(value = "/users_demandes",method=RequestMethod.GET)
	public ArrayList<Demande> getDemandeByUsers(){
		return demandeRepository.getAllDemandesByUsers();
	}
	
	//affichage des statistiques des demande pour les utilisateurs
	@RequestMapping(value= "/states",method=RequestMethod.GET)
	public ArrayList<String> getStates(){
		return demandeRepository.getStates();
	}
	//affichage des statisques des demande pour les user
	@RequestMapping(value="/statistiques", method=RequestMethod.GET)
	public ArrayList<String> getStatistiques(){
		return demandeRepository.getStatisques();
	}
	@RequestMapping(value="/affecter_admin/{id_admin}/{id_demande}", method= RequestMethod.PUT)
	public Demande AffecterAdmin(@PathVariable("id_admin")Long id_admin,@PathVariable("id_demande")Long id_demande) {
		Demande d = demandeRepository.findById(id_demande).get();
		d.setUser_etape(id_admin.toString());
		return demandeRepository.save(d);
	}
	
	//rechercher par mot clé
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/chercher",method=RequestMethod.GET)
	public Page<Demande> getPageDemande(
			@RequestParam(name="mc",defaultValue = "")String mc,
			@RequestParam(name="page", defaultValue ="0")int page,
			@RequestParam(name="size", defaultValue = "10")int size){
		return demandeRepository.chercher("%"+mc+"%", (Pageable) new PageRequest(page, size));
		
	}
			
}
