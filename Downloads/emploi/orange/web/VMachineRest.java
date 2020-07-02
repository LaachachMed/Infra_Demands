package com.orange.web;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orange.dao.DemandeRepository;
import com.orange.dao.FilesRepository;
import com.orange.dao.MotifRepository;
import com.orange.dao.SuiviDemandeRepository;
import com.orange.dao.UserRepository;
import com.orange.dao.VMachineRepository;
import com.orange.entities.Demande;
import com.orange.entities.VMachine;

@RestController
@CrossOrigin("*")
public class VMachineRest {
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
	
	
	//save new machine
	@RequestMapping(value="new_machine/{id_demande}",method=RequestMethod.POST)
	public VMachine saveVM(@PathVariable("id_demande")Long id_demande,@RequestBody VMachine vm) {
		Demande d = demandeRepository.findById(id_demande).get();
		vm.setDemande(d);
		return vMachineRepository.save(vm);
	}
	//affichage de tt les machines d'une demande
	@RequestMapping(value="machines/{id_demande}",method=RequestMethod.GET)
	public ArrayList<VMachine> showVMachines(@PathVariable("id_demande")Long id_demande){
		return vMachineRepository.getAllMachines(id_demande);
	}
	//affichage d'une machine par son id
	@RequestMapping(value="machine/{id_machine}", method=RequestMethod.GET)
	public VMachine showVMachine(@PathVariable("id_machine")Long id_machine) {
		return vMachineRepository.getMachine(id_machine);
	}
	//affichage de nombre de machine par demande
	@RequestMapping(value="nbr_machines",method=RequestMethod.GET)
	public ArrayList<String> getNbr(){
		return vMachineRepository.getNbrVMachine();
	}
	//Modifer liste des machine d'une demande
	@RequestMapping(value="modifier_machines/{id_demande}",method=RequestMethod.PUT)
	public ArrayList<VMachine> saveAllMachines(@PathVariable("id_demande")Long id_demande,@RequestBody ArrayList<VMachine> m){
		Demande d = demandeRepository.findById(id_demande).get();
		for(int i=0 ; i< m.size();i++) {
			m.get(i).setDemande(d);
		}
		return (ArrayList<VMachine>) vMachineRepository.saveAll(m);
	}
	
}
