package com.orange.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.orange.dao.DemandeRepository;
import com.orange.dao.FileSystemRepository;
import com.orange.dao.FilesRepository;
import com.orange.dao.MotifRepository;
import com.orange.dao.SuiviDemandeRepository;
import com.orange.dao.UserRepository;
import com.orange.dao.VMachineRepository;
import com.orange.entities.Demande;
import com.orange.entities.FileSystem;
import com.orange.entities.Motif;
import com.orange.entities.User;

@RestController
@CrossOrigin("*")
@SessionAttributes("login")
public class UserRest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DemandeRepository demandeRepository;
	@Autowired
	private FileSystemRepository fileSystemRepository;
	@Autowired
	private FilesRepository filesRepository;
	@Autowired
	private MotifRepository motifRepository;
	@Autowired
	private SuiviDemandeRepository suiviRepository;
	@Autowired
	private VMachineRepository vmRepository;
	

	//connection d'utilisateur
	@RequestMapping(value="/connect/{login}/{pass}",method=RequestMethod.POST)
	public User connectUser(@PathVariable("login") String login,@PathVariable("pass") String pass) {
		
		
		System.out.println(userRepository.countUser(login, pass));
		if(userRepository.countUser(login, pass) != 0) {
			//session = HttpSession(session,login);
			//System.out.println(session.getAttribute("login").toString());
		}
		return userRepository.connectUser(login, pass);
	}
	
	//affichage de tous les utilisateurs
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<User> getSessions(){
		return userRepository.findAll();
	}
	// insertion d'un utilisateur dans la base de données
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public User save(@RequestBody User s){
		return userRepository.save(s);
	}
	
	//suppression d'un utilisateur et ces demande ainsi que les motifs assossier a ces demandes ainsi que les fs et les Fichiers uploadé
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id){
		ArrayList<Demande> d = demandeRepository.AllUserDemandes(id);
		ArrayList<FileSystem> f = (ArrayList<FileSystem>) fileSystemRepository.findAll();
		ArrayList<Motif> m = (ArrayList<Motif>) motifRepository.findAll();
		for (int i = 0; i<d.size();i++) {
			System.out.println(d.get(i).getId_demande());
			filesRepository.deleteFiles(d.get(i).getId_demande());
			suiviRepository.deleteSuivi(d.get(i).getId_demande());
			
				for(int j=0;j<f.size();j++) {
				if (f.get(j).getVmachine().getDemande().getId_demande() == d.get(i).getId_demande()) {
					Long id_fs = f.get(j).getId_fs();
					fileSystemRepository.deleteById(id_fs);
					
				} 
			}
				for(int k=0; k<m.size();k++) {
					if(m.get(k).getDemander().getId_demande() == d.get(i).getId_demande()) {
						Long id_motif = m.get(k).getId_motif();
						motifRepository.deleteById(id_motif);
					}
				}
				vmRepository.deleteVMachine(d.get(i).getId_demande());
				demandeRepository.deleteById(d.get(i).getId_demande());
		}
		
		userRepository.deleteById(id);
		return true;
	}
	//affichage d'un utilisateur specifique
	@RequestMapping(value="/user/{id}",method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable Long id) {
		return userRepository.findById(id);
	}
	//Affichage de la liste des administrateur
	@RequestMapping(value="/admin_users",method=RequestMethod.GET)
	public ArrayList<User> getAdmins(){
		ArrayList<User> userAdmin = (ArrayList<User>) userRepository.findAll();
		ArrayList<User> userAdminR = new ArrayList<>();
		 for (int i=0;i<userAdmin.size();i++) {
			   	if(userAdmin.get(i).getType().getId_service() == 4) {
			   		System.out.printf(userAdmin.get(i).getNom_complet());
			   		userAdminR.add(userAdmin.get(i));
			   	}else {
			   		
			   	}
		 }
		 return userAdminR;
	}
	//selectionner un utilisateur par son mail et mdp pour effectuer la connection
	@RequestMapping(value="/get_login/{mail}/{pass}",method=RequestMethod.GET)
	public User getLogin(@PathVariable("mail")String mail,@PathVariable("pass")String pass){
		ArrayList<User> usersLog = (ArrayList<User>) userRepository.findAll();
		User userLogin = new User();
		System.out.println(mail + " path mail");
		System.out.println(pass + " path pass");
		for(int i=0; i<usersLog.size();i++) { 
			if(usersLog.get(i).getMail().equals(mail) && usersLog.get(i).getMdp_session().equals(pass)) {
				System.out.println(usersLog.get(i).getMail());
				userLogin = usersLog.get(i);
			}else {
				 
			}
		}
		
		return userLogin;
	}
	//Modifier les information d'un utilisateur
	@RequestMapping(value="/modify_user/{id_user}",method=RequestMethod.PUT)
	public User changerInfo(@PathVariable("id_user")Long id_user,@RequestBody User u) {
		User user = userRepository.findById(id_user).get();
		user.setMail(u.getMail());
		user.setLogin(u.getLogin());
		user.setNom_complet(u.getNom_complet());
		user.setType(u.getType());
		user.setMdp_session(u.getMdp_session());
		return userRepository.save(user);
	}
	//Modifier le mdp d'un utilisateur
	@RequestMapping(value="/modify_user_mdp/{id_user}",method=RequestMethod.PUT)
	public User changerMdp(@PathVariable("id_user")Long id_user,@RequestBody User u) {
		User user = userRepository.findById(id_user).get();
		user.setMdp_session(u.getMdp_session());
		return userRepository.save(user); 
	}
	
}
