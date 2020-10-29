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

import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.ServicesDataEntity;
import kr.lineus.aipalm.entity.ServicesOurSkillEntity;
import kr.lineus.aipalm.entity.ServicesPercentEntity;
import kr.lineus.aipalm.service.BlogService;
import kr.lineus.aipalm.service.ServicesList;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
@RestController
@RequestMapping("/api/v1/services")
public class ServicesController {
	public static final Logger logger = LoggerFactory.getLogger(ServicesController.class);
	@Autowired
	ServicesList serviceslist;

	@Autowired
	BlogService blogService;
	@Autowired
	ObjectMapper mapper;

//------------------------------------------Load----------------------------------------//
	// load services
	@GetMapping(value = "/list")
	public ResponseEntity<?> loadAllServices() {
		logger.info("load all services");
		List<ServicesDataEntity> listServices = serviceslist.loadAllServices();
		return new ResponseEntity<List<ServicesDataEntity>>(listServices, HttpStatus.OK);
	}

	// load services ourskill
	@GetMapping(value = "/ourskill")
	public ResponseEntity<?> loadAllOurSkill() {
		logger.info("load all ourskill");
		List<ServicesOurSkillEntity> listourskill = serviceslist.loadAllOurSkill();
		return new ResponseEntity<List<ServicesOurSkillEntity>>(listourskill, HttpStatus.OK);
	}

	// load services percent
	@GetMapping(value = "/percent")
	public ResponseEntity<?> loadAllPercent() {
		logger.info("load all percent");
		List<ServicesPercentEntity> listPercent = serviceslist.loadAllPercent();
		return new ResponseEntity<List<ServicesPercentEntity>>(listPercent, HttpStatus.OK);
	}

//-------------------------------------------Insert---------------------------------------//
	// Insert services
	@PostMapping(value = "/insert")
	public ResponseEntity<?> updateServices(@RequestParam(name = "data") String servicesData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Insert services");
		ServicesDataEntity servicesrequest = mapper.readValue(servicesData, ServicesDataEntity.class);
		System.out.print(servicesrequest);
		if (file != null) {
			saveBlogImage(file, servicesrequest);
		}
		serviceslist.saveServices(servicesrequest);
		return new ResponseEntity<ServicesDataEntity>(servicesrequest, HttpStatus.OK);
	}

	// Insert services ourskill
	@PostMapping(value = "/inserourskill")
	public ResponseEntity<?> insertServicesOurSkill(@RequestParam(name = "data") String ourskillData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Insert services ourskill");
		ServicesOurSkillEntity servicesourskillrequest = mapper.readValue(ourskillData, ServicesOurSkillEntity.class);
		System.out.print(servicesourskillrequest);
		if (file != null) {
			saveServicesOurSkillImage(file, servicesourskillrequest);
		}
		serviceslist.saveServicesOurSkill(servicesourskillrequest);
		return new ResponseEntity<ServicesOurSkillEntity>(servicesourskillrequest, HttpStatus.OK);
	}

	// Insert services Percent
	@PostMapping(value = "/inserPercent")
	public ResponseEntity<?> insertServicesPercent(@RequestParam(name = "data") String percentData)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Insert services inserPercent");
		ServicesPercentEntity servicespercentrequest = mapper.readValue(percentData, ServicesPercentEntity.class);
		System.out.print(servicespercentrequest);
		serviceslist.saveServicesPercent(servicespercentrequest);
		return new ResponseEntity<ServicesPercentEntity>(servicespercentrequest, HttpStatus.OK);
	}

//-------------------------------------------Update---------------------------------------//
	// update services
	@PutMapping(value = "/update/{serId}")
	public ResponseEntity<?> updateServices(@PathVariable("serId") String serId,
			@RequestParam(name = "data") String servicesData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("update services");
		ServicesDataEntity servicesrequest = mapper.readValue(servicesData, ServicesDataEntity.class);
		System.out.print(servicesrequest);
		final ServicesDataEntity bEn = serviceslist.getServices(serId).get();
		bEn.setTitle(servicesrequest.getTitle());
		bEn.setTitleshort(servicesrequest.getTitleshort());
		bEn.setContent(servicesrequest.getContent());
		bEn.setBoxicon(servicesrequest.getBoxicon());
		if (file != null) {
			saveBlogImage(file, bEn);
		}
		serviceslist.saveServices(bEn);
		return new ResponseEntity<ServicesDataEntity>(bEn, HttpStatus.OK);
	}

	// update image services
	public void saveBlogImage(MultipartFile file, ServicesDataEntity bEn) {
		logger.info("update image services");
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			ImageEntity imgBlog;
			if (bEn.getImg() == null) {
				ImageEntity imgEn = new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgEn);
				bEn.setImg(imgBlog);
			} else {
				imgBlog = bEn.getImg();
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgBlog);
				bEn.setImg(imgBlog);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	// update services ourskill
	@PutMapping(value = "/updateourskill/{serId}")
	public ResponseEntity<?> updateServicesOurSkill(@PathVariable("serId") String serId,
			@RequestParam(name = "data") String servicesourskillData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("update services ourskill");
		ServicesOurSkillEntity servicesourskillrequest = mapper.readValue(servicesourskillData,
				ServicesOurSkillEntity.class);
		System.out.print(servicesourskillrequest);
		final ServicesOurSkillEntity bEn = serviceslist.getServicesourskill(serId).get();
		bEn.setOurskill(servicesourskillrequest.getOurskill());
		bEn.setGeneralintroduction(servicesourskillrequest.getGeneralintroduction());
		bEn.setTitle(servicesourskillrequest.getTitle());
		bEn.setShortintroduction(servicesourskillrequest.getShortintroduction());
		if (file != null) {
			saveServicesOurSkillImage(file, bEn);
		}
		serviceslist.saveServicesOurSkill(bEn);
		return new ResponseEntity<ServicesOurSkillEntity>(bEn, HttpStatus.OK);
	}

	// update image services OurSkill
	public void saveServicesOurSkillImage(MultipartFile file, ServicesOurSkillEntity bEn) {
		logger.info("update image services OurSkill");
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			ImageEntity imgBlog;
			if (bEn.getImg() == null) {
				ImageEntity imgEn = new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgEn);
				bEn.setImg(imgBlog);
			} else {
				imgBlog = bEn.getImg();
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgBlog);
				bEn.setImg(imgBlog);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	// update services Percent
	@PutMapping(value = "/updatepercent/{serId}")
	public ResponseEntity<?> updateServicesPercent(@PathVariable("serId") String serId,
			@RequestParam(name = "data") String servicespercentData)
			throws JsonMappingException, JsonProcessingException {
		logger.info("update services Percent");
		ServicesPercentEntity servicespercentrequest = mapper.readValue(servicespercentData,
				ServicesPercentEntity.class);
		System.out.print(servicespercentrequest);
		final ServicesPercentEntity bEn = serviceslist.getServicesPercent(serId).get();
		bEn.setName(servicespercentrequest.getName());
		bEn.setPercent(servicespercentrequest.getPercent());
		serviceslist.saveServicesPercent(bEn);
		return new ResponseEntity<ServicesPercentEntity>(bEn, HttpStatus.OK);
	}

//-------------------------------------------Delete---------------------------------------//
	// delete services
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteServices(@PathVariable String id) {
		logger.info("delete services");
		serviceslist.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// delete services ourskill
	@DeleteMapping(value = "/deleteourskill/{id}")
	public ResponseEntity<?> deleteServicesOurSkill(@PathVariable String id) {
		logger.info("delete services ourskill");
		serviceslist.deleteourskill(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// delete services Percent
	@DeleteMapping(value = "/deletepercent/{id}")
	public ResponseEntity<?> deleteServicespercent(@PathVariable String id) {
		logger.info("delete services Percent");
		serviceslist.deletepercent(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

}
