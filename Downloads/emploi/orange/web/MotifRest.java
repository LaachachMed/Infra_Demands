package com.orange.web;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orange.entities.Demande;
import com.orange.entities.Motif;
import com.orange.entities.User;
import com.orange.dao.DemandeRepository;
import com.orange.dao.MotifRepository;
import com.orange.dao.UserRepository;

@RestController
@CrossOrigin("*")
public class MotifRest {
   
	@Autowired
	MotifRepository motifRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DemandeRepository demandeRepository;
	Mail mail = new Mail();
	//affichage des motifs en generale
	@RequestMapping(value="/motifs",method=RequestMethod.GET)
	public ArrayList<Motif> getMotifs(){
		ArrayList<Motif> m = (ArrayList<Motif>) motifRepository.findAll();
			return m;
	}
	//Enregistrer un motif envoyer par un emetteur specifique --> n -> id emetteur , d -> id demande
	@RequestMapping(value = "/motif/{n}/{d}",method=RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Motif addMotif(@PathVariable("n") Long id_n,@PathVariable("d") Long id_d,@RequestBody Motif m) {
		User user = userRepository.findById(id_n).get();
		Demande demande = demandeRepository.findById(id_d).get();
		m.setUser(user);
		m.setDemander(demande);
		DateFormat dernier_modif = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		m.setDate_motif(date);
//		try { 
//			mail.sendMail("mohamed.fes14@gmail.com");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return motifRepository.save(m);
	}
	//Affichage des notif specifique pour un utilisateur par rapport une damande
	@RequestMapping(value="/motif/{n}/{d}",method=RequestMethod.GET)
	public ArrayList<Motif> showMotif(@PathVariable("n") Long id_n,@PathVariable("d") Long id_d){
		ArrayList<Motif> m = motifRepository.chercherMotif(id_n,id_d);
		ArrayList<Motif> m2 = new ArrayList<Motif>();
		int index_max = 0;
		
		for(int i=0;i<m.size()-1;i++) {
				if(m.get(i).getId_motif() > m.get(i+1).getId_motif()) {
					index_max =i;
				} else {
					index_max = i+1;
				}
		} 
		m2.add(m.get(index_max));
		return m2;
	}
	 
}
