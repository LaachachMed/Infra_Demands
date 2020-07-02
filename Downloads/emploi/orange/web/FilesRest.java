package com.orange.web;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.orange.dao.DemandeRepository;
import com.orange.dao.FilesRepository;
import com.orange.entities.Demande;
import com.orange.entities.Files;


@RestController
@CrossOrigin("*")
public class FilesRest {
	@Autowired
	FilesRepository fileRepository;
	@Autowired
	DemandeRepository demandeRepository;
	
	//affichage d'un fichiers uploadé par le client
	@RequestMapping(value="/telecharger_fichier/{id_demande}",method=RequestMethod.GET)
	 public ResponseEntity<Resource> downloadFile(@PathVariable("id_demande") Long fileId,HttpServletRequest request) throws MalformedURLException, IOException{
		Demande d = demandeRepository.findById(fileId).get();
		String name = d.getUser().getNom_complet();	
		String contentType = null;
		File path = new File(fileRepository.getAllFiles(fileId).getFile_loca());
		Resource res = new UrlResource(path.toURI());
		// Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
		 return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + res.getFilename())
	                .body(res);
	}	
	//affichage de tous les fichiers dans la bd
	@RequestMapping(value="/files",method=RequestMethod.GET)
	public ArrayList<Files> getfiles(){
		return (ArrayList<Files>) fileRepository.findAll();
		
	}
	//chargement d'un fichier vers le repertoire c://uploads
	@RequestMapping(value="/uploadFile/{id_demande}",headers = ("content-type=multipart/*"),method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String UploadFiles(@RequestParam("file") MultipartFile file,@PathVariable("id_demande")Long id_d) throws FileUploadException, IOException {
		String msg="Failed";
		Demande d = demandeRepository.findById(id_d).get();
		System.out.println(file.getName());
		String extention = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
		String uploadsDir = "C://uploads/"; //changer cette repertoire si necessaire
        
        if(! new File(uploadsDir).exists())
        {
            new File(uploadsDir).mkdir();
        }
        //String orgName = file.getOriginalFilename();
        String filePath = uploadsDir + d.getId_demande()+"_"+ d.getUser().getNom_complet()+"_dat."+ extention;
        File dest = new File(filePath);
        file.transferTo(dest);
		Files files = new Files(d,extention,extention,filePath);
		fileRepository.save(files);
		msg = "uploaded";
		return msg; 
	}
	
	//get file by id
	 @RequestMapping(value="/file/{id_file}",method=RequestMethod.GET)
	 public Files getFile(@PathVariable("id_file")Long fileId) {
	     //Files f = fileRepository.findById(fileId).get();
	     
		 return fileRepository.findById(fileId).get();
	        
	    }
	
	
}
