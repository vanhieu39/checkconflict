package kr.lineus.aipalm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.lineus.aipalm.entity.QuestionContactEntity;
import kr.lineus.aipalm.entity.TeamEntity;
import kr.lineus.aipalm.exceptionhandler.WebappException;
import kr.lineus.aipalm.service.QuestionContactService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
@RestController
@RequestMapping("/api/v1/contact")
public class QuestionContactController {

	public static final Logger logger = LoggerFactory.getLogger(QuestionContactController.class);

	@Autowired
	QuestionContactService questionContactService;
	
	@Autowired
	ObjectMapper mapper;
	
	@GetMapping(value="/list")
	public ResponseEntity<?> loadAllContact() throws WebappException {
		logger.info("Load All Contact");
		List<QuestionContactEntity> listContact = questionContactService.loadAllContact();
		
		return new ResponseEntity<List<QuestionContactEntity>>(listContact, HttpStatus.OK);
		
	}
	@PostMapping(value = "/insert")
	public ResponseEntity<?> InsertContact(@RequestParam(name="data") String contactData) throws JsonMappingException, JsonProcessingException {
		QuestionContactEntity contactRequest = mapper.readValue(contactData, QuestionContactEntity.class);
		questionContactService.saveContact(contactRequest);
		return new ResponseEntity<QuestionContactEntity>(contactRequest, HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/delete/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable String id){
		questionContactService.delete(id);
		
		return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
		
	}
}
