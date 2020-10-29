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

import kr.lineus.aipalm.entity.HomeAboutEntity;
import kr.lineus.aipalm.entity.HomeAboutImgEntity;
import kr.lineus.aipalm.entity.HomeCarouselEntity;
import kr.lineus.aipalm.entity.HomeClientImgEntity;
import kr.lineus.aipalm.entity.HomeContactEntity;
import kr.lineus.aipalm.entity.HomeIntroduceEntity;
import kr.lineus.aipalm.entity.HomeMenuEntity;
import kr.lineus.aipalm.entity.HomeOrderLayoutEntity;
import kr.lineus.aipalm.entity.HomeOurServiceEntity;
import kr.lineus.aipalm.entity.HomeSubEmailEntity;
import kr.lineus.aipalm.entity.HomeSubMenuEntity;
import kr.lineus.aipalm.entity.HomeUseLinkEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.service.BlogService;
import kr.lineus.aipalm.service.HomeAboutImgService;
import kr.lineus.aipalm.service.HomeAboutService;
import kr.lineus.aipalm.service.HomeCarouselService;
import kr.lineus.aipalm.service.HomeClientImgService;
import kr.lineus.aipalm.service.HomeContactService;
import kr.lineus.aipalm.service.HomeEmailServiceImpl;
import kr.lineus.aipalm.service.HomeIntroduceService;
import kr.lineus.aipalm.service.HomeMenuService;
import kr.lineus.aipalm.service.HomeOrderLayoutImgService;
import kr.lineus.aipalm.service.HomeOurService;
import kr.lineus.aipalm.service.HomeSubMenuService;
import kr.lineus.aipalm.service.HomeUseLinkService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
@RestController
@RequestMapping("/api/v1/home")

public class HomeController {

	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	HomeMenuService homemenu;

	@Autowired
	HomeSubMenuService homeSubmenu;

	@Autowired
	HomeUseLinkService homeUseLink;

	@Autowired
	HomeOurService homeOur;

	@Autowired
	HomeContactService homeContact;

	@Autowired
	HomeAboutService homeAbout;

	@Autowired
	HomeCarouselService homeCarouselSv;

	@Autowired
	HomeIntroduceService homeIntroSv;

	@Autowired
	HomeAboutImgService homeAboutImgSv;

	@Autowired
	HomeClientImgService homeClientImgSv;

	@Autowired
	BlogService blogService;

	@Autowired
	HomeOrderLayoutImgService homeOrLayOutSv;

	@Autowired
	HomeEmailServiceImpl homeEmailSv;

	@Autowired
	ObjectMapper mapper;

	// Menu
	@GetMapping(value = "/menu")
	public ResponseEntity<?> loadAllService() {
		List<HomeMenuEntity> listHomeMenu = homemenu.loadAllHomeMenuService();
		return new ResponseEntity<List<HomeMenuEntity>>(listHomeMenu, HttpStatus.OK);
	}

	@GetMapping(value = "/menu/{id}")
	public ResponseEntity<?> loadAllServiceId(@PathVariable String id) {
		HomeMenuEntity homeMenuId = homemenu.getHomeMenu(id).get();
		return new ResponseEntity<HomeMenuEntity>(homeMenuId, HttpStatus.OK);
	}

	@PostMapping(value = "/menu")
	public ResponseEntity<?> InsertHomeMenu(@RequestParam(name = "data") String homeMenuData)
			throws JsonMappingException, JsonProcessingException {
		HomeMenuEntity homeMenuRequest = mapper.readValue(homeMenuData, HomeMenuEntity.class);
		homemenu.saveHomeMenu(homeMenuRequest);
		return new ResponseEntity<HomeMenuEntity>(homeMenuRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/menu/{id}")
	public ResponseEntity<?> updateHomeMenu(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeMenuData) throws JsonMappingException, JsonProcessingException {
		HomeMenuEntity homeMenurequest = mapper.readValue(homeMenuData, HomeMenuEntity.class);
		final HomeMenuEntity bEn = homemenu.getHomeMenu(id).get();
		bEn.setName(homeMenurequest.getName());
		bEn.setSource(homeMenurequest.getSource());

		homemenu.saveHomeMenu(bEn);

		return new ResponseEntity<HomeMenuEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/menu/{id}")
	public ResponseEntity<?> deleteHomeMenu(@PathVariable String id) {
		homemenu.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// Subscribe
	@GetMapping(value = "/subemail")
	public ResponseEntity<?> loadAllEmail() {
		List<HomeSubEmailEntity> listHomeEmail = homeEmailSv.loadAllHomeEmail();
		return new ResponseEntity<List<HomeSubEmailEntity>>(listHomeEmail, HttpStatus.OK);
	}
	@PostMapping(value = "/subemail")
	public ResponseEntity<?> InsertEmail(@RequestParam(name = "data") String homeEmailData)
			throws JsonMappingException, JsonProcessingException {
		HomeSubEmailEntity homeEmailRequest = mapper.readValue(homeEmailData, HomeSubEmailEntity.class);
		homeEmailSv.saveHomeEmail(homeEmailRequest);
		return new ResponseEntity<HomeSubEmailEntity>(homeEmailRequest, HttpStatus.OK);
	}
		
	// OrderLayout
	@GetMapping(value = "/orderlayout")
	public ResponseEntity<?> loadAllService1() {
		List<HomeOrderLayoutEntity> listHomeMenu = homeOrLayOutSv.loadAllHomeOrderLayout();
		return new ResponseEntity<List<HomeOrderLayoutEntity>>(listHomeMenu, HttpStatus.OK);
	}

	@PutMapping(value = "/orderlayout/{id}")
	public ResponseEntity<?> updateHomeOrderLayout(@PathVariable("id") int id,
			@RequestParam(name = "data") String homeOrLayoutData) throws JsonMappingException, JsonProcessingException {
		HomeOrderLayoutEntity homeOrLayoutrequest = mapper.readValue(homeOrLayoutData, HomeOrderLayoutEntity.class);

		final HomeOrderLayoutEntity bEn = homeOrLayOutSv.getHomeOrderLayout(id).get();

		bEn.setNumorder(homeOrLayoutrequest.getNumorder());

		homeOrLayOutSv.saveHomeOrLayout(bEn);

		return new ResponseEntity<HomeOrderLayoutEntity>(bEn, HttpStatus.OK);
	}

	// SubMenu
	@GetMapping(value = "/submenu")
	public ResponseEntity<?> loadAllSubMenu() {
		List<HomeSubMenuEntity> listHomeSubMenu = homeSubmenu.loadAllHomeSubMenuService();
		return new ResponseEntity<List<HomeSubMenuEntity>>(listHomeSubMenu, HttpStatus.OK);
	}

	@GetMapping(value = "/submenu/{id}")
	public ResponseEntity<?> loadAllSubMenuId(@PathVariable String id) {
		HomeSubMenuEntity homeSubMenuId = homeSubmenu.getHomeSubMenu(id).get();
		return new ResponseEntity<HomeSubMenuEntity>(homeSubMenuId, HttpStatus.OK);
	}

	@PostMapping(value = "/submenu")
	public ResponseEntity<?> InsertHomeSubMenu(@RequestParam(name = "data") String homeSubMenuData)
			throws JsonMappingException, JsonProcessingException {
		HomeSubMenuEntity homeSubMenuRequest = mapper.readValue(homeSubMenuData, HomeSubMenuEntity.class);
		homeSubmenu.saveHomeSubMenu(homeSubMenuRequest);
		return new ResponseEntity<HomeSubMenuEntity>(homeSubMenuRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/submenu/{id}")
	public ResponseEntity<?> updateHomeSubMenu(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeSubMenuData) throws JsonMappingException, JsonProcessingException {
		HomeSubMenuEntity homeSubMenurequest = mapper.readValue(homeSubMenuData, HomeSubMenuEntity.class);

		final HomeSubMenuEntity bEn = homeSubmenu.getHomeSubMenu(id).get();

		bEn.setName(homeSubMenurequest.getName());
		bEn.setSource(homeSubMenurequest.getSource());

		homeSubmenu.saveHomeSubMenu(bEn);

		return new ResponseEntity<HomeSubMenuEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/submenu/{id}")
	public ResponseEntity<?> deleteHomeSubMenu(@PathVariable String id) {
		homeSubmenu.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// User Link
	@GetMapping(value = "/uselink")
	public ResponseEntity<?> loadAllHomeUseLink() {
		List<HomeUseLinkEntity> listHomeUseLink = homeUseLink.loadAllHomeUseLink();
		return new ResponseEntity<List<HomeUseLinkEntity>>(listHomeUseLink, HttpStatus.OK);
	}

	@GetMapping(value = "/uselink/{id}")
	public ResponseEntity<?> loadAllHomeUseLinkId(@PathVariable String id) {
		HomeUseLinkEntity homeUseLinkId = homeUseLink.getHomeUseLink(id).get();
		return new ResponseEntity<HomeUseLinkEntity>(homeUseLinkId, HttpStatus.OK);
	}

	@PostMapping(value = "/uselink")
	public ResponseEntity<?> InsertHomeUserLink(@RequestParam(name = "data") String homeUserLinkData)
			throws JsonMappingException, JsonProcessingException {
		HomeUseLinkEntity homeUseLinkRequest = mapper.readValue(homeUserLinkData, HomeUseLinkEntity.class);
		homeUseLink.saveHomeUseLink(homeUseLinkRequest);
		return new ResponseEntity<HomeUseLinkEntity>(homeUseLinkRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/uselink/{id}")
	public ResponseEntity<?> updateHomeUseLink(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeUserLinkData) throws JsonMappingException, JsonProcessingException {
		HomeUseLinkEntity homeUseLinkRequest = mapper.readValue(homeUserLinkData, HomeUseLinkEntity.class);
		final HomeUseLinkEntity bEn = homeUseLink.getHomeUseLink(id).get();

		bEn.setName(homeUseLinkRequest.getName());
		bEn.setSource(homeUseLinkRequest.getSource());

		homeUseLink.saveHomeUseLink(bEn);

		return new ResponseEntity<HomeUseLinkEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/uselink/{id}")
	public ResponseEntity<?> deleteHomeUseLink(@PathVariable String id) {
		homeUseLink.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// Our service
	@GetMapping(value = "/ourservice")
	public ResponseEntity<?> loadAllHomeOurServce() {
		List<HomeOurServiceEntity> listHomeOurService = homeOur.loadAllHomeOur();
		return new ResponseEntity<List<HomeOurServiceEntity>>(listHomeOurService, HttpStatus.OK);
	}

	@PostMapping(value = "/ourservice")
	public ResponseEntity<?> InsertHomeOurService(@RequestParam(name = "data") String homeOurData)
			throws JsonMappingException, JsonProcessingException {
		HomeOurServiceEntity homeOurRequest = mapper.readValue(homeOurData, HomeOurServiceEntity.class);
		homeOur.saveHomeOur(homeOurRequest);
		return new ResponseEntity<HomeOurServiceEntity>(homeOurRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/ourservice/{id}")
	public ResponseEntity<?> updateHomeOurSevice(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeOurServiceData)
			throws JsonMappingException, JsonProcessingException {
		HomeOurServiceEntity homeOurServiceRequest = mapper.readValue(homeOurServiceData, HomeOurServiceEntity.class);
		final HomeOurServiceEntity bEn = homeOur.getHomeOur(id).get();
		bEn.setName(homeOurServiceRequest.getName());
		bEn.setSource(homeOurServiceRequest.getSource());

		homeOur.saveHomeOur(bEn);

		return new ResponseEntity<HomeOurServiceEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/ourservice/{id}")
	public ResponseEntity<?> deleteHomeOurService(@PathVariable String id) {
		homeOur.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// About
	@GetMapping(value = "/about")
	public ResponseEntity<?> loadAllHomeAbout() {
		List<HomeAboutEntity> listHomeOurService = homeAbout.loadAllHomeAbout();
		return new ResponseEntity<List<HomeAboutEntity>>(listHomeOurService, HttpStatus.OK);
	}

	@GetMapping(value = "/about/{id}")
	public ResponseEntity<?> loadAllHomeAboutId(@PathVariable String id) {
		HomeAboutEntity homeAboutId = homeAbout.getHomeAbout(id).get();
		return new ResponseEntity<HomeAboutEntity>(homeAboutId, HttpStatus.OK);
	}

	@PostMapping(value = "/about")
	public ResponseEntity<?> InsertHomeAbout(@RequestParam(name = "data") String homeAboutData)
			throws JsonMappingException, JsonProcessingException {
		HomeAboutEntity homeAboutRequest = mapper.readValue(homeAboutData, HomeAboutEntity.class);
		homeAbout.saveHomeAbout(homeAboutRequest);
		return new ResponseEntity<HomeAboutEntity>(homeAboutRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/about/{id}")
	public ResponseEntity<?> updateHomeAbout(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeAboutData) throws JsonMappingException, JsonProcessingException {
		HomeAboutEntity homeAboutRequest = mapper.readValue(homeAboutData, HomeAboutEntity.class);
		final HomeAboutEntity bEn = homeAbout.getHomeAbout(id).get();
		bEn.setContent(homeAboutRequest.getContent());

		homeAbout.saveHomeAbout(bEn);

		return new ResponseEntity<HomeAboutEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/about/{id}")
	public ResponseEntity<?> deleteHomeAbout(@PathVariable String id) {
		homeAbout.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// Contact
	@GetMapping(value = "/contact")
	public ResponseEntity<?> loadAllHomeContact() {
		List<HomeContactEntity> listHomeContact = homeContact.loadAllHomeContact();
		return new ResponseEntity<List<HomeContactEntity>>(listHomeContact, HttpStatus.OK);
	}

	@GetMapping(value = "/contact/{id}")
	public ResponseEntity<?> loadAllHomeContactId(@PathVariable String id) {
		HomeContactEntity homeContactId = homeContact.getHomeContact(id).get();
		return new ResponseEntity<HomeContactEntity>(homeContactId, HttpStatus.OK);
	}

	@PostMapping(value = "/contact")
	public ResponseEntity<?> InsertHomeContact(@RequestParam(name = "data") String homeContactData)
			throws JsonMappingException, JsonProcessingException {
		HomeContactEntity homeContactRequest = mapper.readValue(homeContactData, HomeContactEntity.class);
		homeContact.saveHomeContact(homeContactRequest);
		return new ResponseEntity<HomeContactEntity>(homeContactRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/contact/{id}")
	public ResponseEntity<?> updateHomeContact(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeContactData) throws JsonMappingException, JsonProcessingException {
		HomeContactEntity homeContactRequest = mapper.readValue(homeContactData, HomeContactEntity.class);
		final HomeContactEntity bEn = homeContact.getHomeContact(id).get();
		bEn.setAddress(homeContactRequest.getAddress());
		bEn.setPhone(homeContactRequest.getPhone());
		bEn.setEmail(homeContactRequest.getEmail());

		homeContact.saveHomeContact(bEn);

		return new ResponseEntity<HomeContactEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/contact/{id}")
	public ResponseEntity<?> deleteHomeContact(@PathVariable String id) {
		homeContact.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// Carousel
	@GetMapping(value = "/carousel")
	public ResponseEntity<?> loadAllHomeCarousel() {
		List<HomeCarouselEntity> listHomeCarousel = homeCarouselSv.loadAllHomeCarousel();
		return new ResponseEntity<List<HomeCarouselEntity>>(listHomeCarousel, HttpStatus.OK);
	}

	@GetMapping(value = "/carousel/{id}")
	public ResponseEntity<?> listHomeCarouselId(@PathVariable String id) {
		HomeCarouselEntity homeCarouselId = homeCarouselSv.getHomeCarousel(id).get();
		return new ResponseEntity<HomeCarouselEntity>(homeCarouselId, HttpStatus.OK);
	}

	// update imdage
	public void saveCarouselImage(MultipartFile file, HomeCarouselEntity bEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			ImageEntity imgBlog;
			if (bEn.getImage() == null) {
				ImageEntity imgEn = new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgEn);
				bEn.setImage(imgBlog);
			} else {
				imgBlog = bEn.getImage();
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgBlog);
				bEn.setImage(imgBlog);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@PostMapping(value = "/carousel")
	public ResponseEntity<?> InsertHomeCarousel(@RequestParam(name = "data") String homeCarouselData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		HomeCarouselEntity homeCarouselRequest = mapper.readValue(homeCarouselData, HomeCarouselEntity.class);
		if (file != null) {
			saveCarouselImage(file, homeCarouselRequest);
		}
		homeCarouselSv.saveHomeCarousel(homeCarouselRequest);

		return new ResponseEntity<HomeCarouselEntity>(homeCarouselRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/carousel/{id}")
	public ResponseEntity<?> updateHomeCarousel(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeCarouselData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		HomeCarouselEntity homeCarouselRequest = mapper.readValue(homeCarouselData, HomeCarouselEntity.class);
		final HomeCarouselEntity bEn = homeCarouselSv.getHomeCarousel(id).get();

		bEn.setTitle(homeCarouselRequest.getTitle());
		bEn.setContent(homeCarouselRequest.getContent());
		bEn.setSource(homeCarouselRequest.getSource());

		if (file != null) {
			saveCarouselImage(file, bEn);
		}

		homeCarouselSv.saveHomeCarousel(bEn);

		return new ResponseEntity<HomeCarouselEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/carousel/{id}")
	public ResponseEntity<?> deleteHomeCarousel(@PathVariable String id) {
		homeCarouselSv.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// Introduce
	@GetMapping(value = "/introduce")
	public ResponseEntity<?> loadAllHomeIntroduce() {
		List<HomeIntroduceEntity> listHomeIntroduce = homeIntroSv.loadAllHomeIntroduce();
		return new ResponseEntity<List<HomeIntroduceEntity>>(listHomeIntroduce, HttpStatus.OK);
	}

	@GetMapping(value = "/introduce/{id}")
	public ResponseEntity<?> loadAllHomeIntroduce(@PathVariable String id) {
		HomeIntroduceEntity homeIntroId = homeIntroSv.getHomeIntroduce(id).get();
		return new ResponseEntity<HomeIntroduceEntity>(homeIntroId, HttpStatus.OK);
	}

	@PostMapping(value = "/introduce")
	public ResponseEntity<?> InsertHomeIntroduce(@RequestParam(name = "data") String homeIntroData)
			throws JsonMappingException, JsonProcessingException {
		HomeIntroduceEntity homeIntroRequest = mapper.readValue(homeIntroData, HomeIntroduceEntity.class);
		homeIntroSv.saveHomeIntroduce(homeIntroRequest);
		return new ResponseEntity<HomeIntroduceEntity>(homeIntroRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/introduce/{id}")
	public ResponseEntity<?> updateHomeIntroduce(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeIntroData) throws JsonMappingException, JsonProcessingException {
		HomeIntroduceEntity homeIntroRequest = mapper.readValue(homeIntroData, HomeIntroduceEntity.class);
		final HomeIntroduceEntity bEn = homeIntroSv.getHomeIntroduce(id).get();

		bEn.setTitle(homeIntroRequest.getTitle());
		bEn.setContent(homeIntroRequest.getContent());

		homeIntroSv.saveHomeIntroduce(bEn);

		return new ResponseEntity<HomeIntroduceEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/introduce/{id}")
	public ResponseEntity<?> deleteHomeIntroduce(@PathVariable String id) {
		homeIntroSv.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// About image
	@GetMapping(value = "/aboutimg")
	public ResponseEntity<?> loadAllHomeAboutImg() {
		List<HomeAboutImgEntity> listHomeAboutImg = homeAboutImgSv.loadAllHomeAboutImg();
		return new ResponseEntity<List<HomeAboutImgEntity>>(listHomeAboutImg, HttpStatus.OK);
	}

	@GetMapping(value = "/aboutimg/{id}")
	public ResponseEntity<?> loadAllHomeAboutImgId(@PathVariable String id) {
		HomeAboutImgEntity homeaboutImgId = homeAboutImgSv.getHomeAboutImg(id).get();
		return new ResponseEntity<HomeAboutImgEntity>(homeaboutImgId, HttpStatus.OK);
	}

	// update image
	public void saveAboutImage(MultipartFile file, HomeAboutImgEntity bEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			ImageEntity imgBlog;
			if (bEn.getImage() == null) {
				ImageEntity imgEn = new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgEn);
				bEn.setImage(imgBlog);
			} else {
				imgBlog = bEn.getImage();
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgBlog);
				bEn.setImage(imgBlog);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@PostMapping(value = "/aboutimg")
	public ResponseEntity<?> InsertHomeAboutImg(@RequestParam(name = "data") String homeAboutImgData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		HomeAboutImgEntity homeAboutImgRequest = mapper.readValue(homeAboutImgData, HomeAboutImgEntity.class);
		if (file != null) {
			saveAboutImage(file, homeAboutImgRequest);
		}
		homeAboutImgSv.saveHomeAboutImg(homeAboutImgRequest);

		return new ResponseEntity<HomeAboutImgEntity>(homeAboutImgRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/aboutimg/{id}")
	public ResponseEntity<?> updateHomeAboutImg(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeAboutImgData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		HomeAboutImgEntity homeAboutImgRequest = mapper.readValue(homeAboutImgData, HomeAboutImgEntity.class);
		final HomeAboutImgEntity bEn = homeAboutImgSv.getHomeAboutImg(id).get();

		if (file != null) {
			saveAboutImage(file, bEn);
		}

		homeAboutImgSv.saveHomeAboutImg(bEn);

		return new ResponseEntity<HomeAboutImgEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/aboutimg/{id}")
	public ResponseEntity<?> deleteHomeAboutImg(@PathVariable String id) {
		homeAboutImgSv.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

	// Client image
	@GetMapping(value = "/clientimg")
	public ResponseEntity<?> loadAllHomeClientImg() {
		List<HomeClientImgEntity> listHomeClientImg = homeClientImgSv.loadAllHomeClientImg();
		return new ResponseEntity<List<HomeClientImgEntity>>(listHomeClientImg, HttpStatus.OK);
	}

	// update image
	public void saveClientImage(MultipartFile file, HomeClientImgEntity bEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			ImageEntity imgBlog;
			if (bEn.getImage() == null) {
				ImageEntity imgEn = new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgEn);
				bEn.setImage(imgBlog);
			} else {
				imgBlog = bEn.getImage();
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgBlog);
				bEn.setImage(imgBlog);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@PostMapping(value = "/clientimg")
	public ResponseEntity<?> InsertHomeClientImg(@RequestParam(name = "data") String homeClientImgData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		HomeClientImgEntity homeClientImgRequest = mapper.readValue(homeClientImgData, HomeClientImgEntity.class);
		if (file != null) {
			saveClientImage(file, homeClientImgRequest);
		}

		homeClientImgSv.saveHomeClientImg(homeClientImgRequest);

		return new ResponseEntity<HomeClientImgEntity>(homeClientImgRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/clientimg/{id}")
	public ResponseEntity<?> updateHomeClientImg(@PathVariable("id") String id,
			@RequestParam(name = "data") String homeClientImgData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		HomeClientImgEntity homeClientImgRequest = mapper.readValue(homeClientImgData, HomeClientImgEntity.class);
		final HomeClientImgEntity bEn = homeClientImgSv.getHomeClientImg(id).get();

		if (file != null) {
			saveClientImage(file, bEn);
		}

		homeClientImgSv.saveHomeClientImg(bEn);

		return new ResponseEntity<HomeClientImgEntity>(bEn, HttpStatus.OK);
	}

	@DeleteMapping(value = "/clientimg/{id}")
	public ResponseEntity<?> deleteHomeClientImg(@PathVariable String id) {
		homeClientImgSv.delete(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}

}
