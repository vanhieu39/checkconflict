package kr.lineus.aipalm.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.ServicesDataEntity;
import kr.lineus.aipalm.entity.TeamEntity;
import kr.lineus.aipalm.exceptionhandler.WebappException;
import kr.lineus.aipalm.service.TeamService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

	public static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService teamService;

	@Autowired
	ObjectMapper mapper;
	
	@GetMapping(value="/list")
	public ResponseEntity<?> loadAllTeam() throws WebappException {
		logger.info("Load All Team");
		List<TeamEntity> listTeam = teamService.loadAllTeam();
		
		return new ResponseEntity<List<TeamEntity>>(listTeam, HttpStatus.OK);
		
	}
	@PutMapping(value="/update/{teamId}")
	public ResponseEntity<?> updateTeam(@PathVariable("teamId") String teamId, @RequestParam(name="data") String teamData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException{
		logger.info("Update Team by id");
		TeamEntity teamResquest = mapper.readValue(teamData, TeamEntity.class);
		final TeamEntity tEn = teamService.getTeam(teamId).get();
		tEn.setEnglishName(teamResquest.getEnglishName());
		tEn.setPosition(teamResquest.getPosition());
		tEn.setRealName(teamResquest.getRealName());
		tEn.setFacebook(teamResquest.getFacebook());
		tEn.setTwitter(teamResquest.getTwitter());
		tEn.setInstagram(teamResquest.getInstagram());
		tEn.setLinkedin(teamResquest.getLinkedin());
		if(file != null) {
			saveTeamImage(file, tEn);
		}
		teamService.saveTeam(tEn);
		return new ResponseEntity<TeamEntity>(tEn, HttpStatus.OK);
	
	}
	//update imdage
		public void saveTeamImage(MultipartFile file, TeamEntity tEn) {
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			if(name.contains("..")) {
				logger.error("error for path of file");
			}
			
			try {
				ImageEntity imgTeam;
				if( tEn.getImg() == null) {
					ImageEntity imgEn= new ImageEntity();
					imgEn.setFileName(name);
					imgEn.setFileType(file.getContentType());
					imgEn.setData(file.getBytes());
					imgTeam = teamService.saveImage(imgEn);
					tEn.setImg(imgTeam);
				}
				else {
					imgTeam = tEn.getImg(); 
					imgTeam.setFileName(name);
					imgTeam.setFileType(file.getContentType());
					imgTeam.setData(file.getBytes());
					imgTeam = teamService.saveImage(imgTeam);
					tEn.setImg(imgTeam);
				}
			} 
			catch (IOException e) {
				logger.error(e.toString());
			}
		}
		@PostMapping(value = "/insert")
		public ResponseEntity<?> InsertTeam(@RequestParam(name="data") String teamData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
			TeamEntity teamRequest = mapper.readValue(teamData, TeamEntity.class);

			if(file != null) {
				saveTeamImage(file,teamRequest);
			}
			teamService.saveTeam(teamRequest);
			return new ResponseEntity<TeamEntity>(teamRequest, HttpStatus.OK);
		}
		@DeleteMapping(value ="/delete/{id}")
		public ResponseEntity<?> deleteTeam(@PathVariable String id){
			teamService.delete(id);
			
			return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
			
		}
}
