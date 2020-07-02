package com.orange.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.orange.entities.*;

import com.orange.dao.FileSystemRepository;
import com.orange.dao.VMachineRepository;



@RestController
@CrossOrigin("*")
public class FileSystemRest {
	@Autowired
	FileSystemRepository fileSystemRepository;
	@Autowired
	VMachineRepository vmachineRepository;
	
	
	//enregistre la liste des file system pour une machine
	@RequestMapping(value="filesystem/{id_machine}",method=RequestMethod.POST)
	public ArrayList<FileSystem> saveAllFileSystem(@PathVariable("id_machine")Long id_machine,@RequestBody ArrayList<FileSystem> fs){
		VMachine vm = vmachineRepository.findById(id_machine).get();
		for(int i=0; i<fs.size(); i++) {
			fs.get(i).setVmachine(vm);
		}
		return (ArrayList<FileSystem>) fileSystemRepository.saveAll(fs);
		
	}
	//afficher les file system d'une machine
	@RequestMapping(value="filesystem/{id_machine}",method=RequestMethod.GET)
	public ArrayList<FileSystem> showAllFileSystem(@PathVariable("id_machine")Long id_machine){
		ArrayList<FileSystem> fs = (ArrayList<FileSystem>) fileSystemRepository.findAll();
		ArrayList<FileSystem> fs1 = new ArrayList<>();
		System.out.println(fs.size());
		for(int i=0;i<fs.size();i++) {
			if(fs.get(i).getVmachine().getId_machine() == id_machine) {
				System.out.println("machine found");
				FileSystem fs2 = fs.get(i);
				System.out.println(fs2.getFs());
				fs1.add(fs2);
			}else {
				
			}
		}
		return fs1;
	}
	//modification  des file system d'une machine
	@RequestMapping(value="filesystem",method=RequestMethod.PUT)
	public ArrayList<FileSystem> modifyFS(@RequestBody ArrayList<FileSystem> fs) {
		return (ArrayList<FileSystem>) fileSystemRepository.saveAll(fs);
	}

}
