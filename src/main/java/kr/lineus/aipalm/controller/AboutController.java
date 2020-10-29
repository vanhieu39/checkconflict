package kr.lineus.aipalm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.lineus.aipalm.converter.AboutConverter;
import kr.lineus.aipalm.converter.PortfolioConverter;
import kr.lineus.aipalm.dto.AboutDto;
import kr.lineus.aipalm.dto.PortfolioDto;
import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutDetailEntity;
import kr.lineus.aipalm.entity.AboutImgEntity;
import kr.lineus.aipalm.entity.AboutStatisticalEntity;
import kr.lineus.aipalm.entity.AboutTestimonialsEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.service.AboutClientService;
import kr.lineus.aipalm.service.AboutDetailService;
import kr.lineus.aipalm.service.AboutStatisticalService;
import kr.lineus.aipalm.service.AboutTestimonialsService;
import kr.lineus.aipalm.service.BlogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/aboutClient")
public class AboutController {
	public static final Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	@Autowired
	@Qualifier("clientService")
	AboutClientService clientService;
	
	@Autowired
	@Qualifier("staService")
	AboutStatisticalService staService;
	
	@Autowired
	@Qualifier("testService")
	AboutTestimonialsService testService;
	
	@Autowired
	@Qualifier("blogService")
	BlogService blogService;
	
	@Autowired
	@Qualifier("detailService")
	AboutDetailService detailService;
	
	@Autowired
	ObjectMapper mapper;
	

	@GetMapping(value="/client")
	public ResponseEntity<?> loadAllClient() {
		logger.info("Loading all client");
		List <AboutDto> clientList = AboutConverter.getInstance().clientDTOList(clientService.loadAllClient());
		return new ResponseEntity<List<AboutDto>>(clientList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detailClient")
	public ResponseEntity<?> loadClientById(HttpServletResponse response,@PathVariable("1") String id){
		logger.info("Get detai client");
		AboutDto aboutDto = AboutConverter.getInstance().clientEntityToDto(clientService.getClient("1").get());
		return new ResponseEntity<AboutDto>(aboutDto, HttpStatus.OK);
	}
	
	@GetMapping(value="/testimonials")
	public ResponseEntity<?> loadTestimonials() {
		logger.info("Loading all testimonials");
		List <AboutTestimonialsEntity> testList = testService.loadTestimonials();
		return new ResponseEntity<List<AboutTestimonialsEntity>>(testList, HttpStatus.OK);
	}
	
	@GetMapping(value="/statistical")
	public ResponseEntity<?> loadStatistical() {
		logger.info("Loading all statistical");
		List <AboutStatisticalEntity> staList = staService.loadStatistical();
		return new ResponseEntity<List<AboutStatisticalEntity>>(staList, HttpStatus.OK);
	}
	
	@GetMapping(value="/detail")
	public ResponseEntity<?> loadDetail() {
		logger.info("Loading all detail");
		List <AboutDetailEntity> detailList = detailService.loadDetail();
		return new ResponseEntity<List<AboutDetailEntity>>(detailList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertTest")
	public ResponseEntity<?> updateTestServices(@RequestParam(name="data") String aboutData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		AboutTestimonialsEntity aboutrequest = mapper.readValue(aboutData, AboutTestimonialsEntity.class);
		System.out.print(aboutrequest);
		if(file != null) {
			saveTestImage(file,aboutrequest);
		}
		testService.saveTest(aboutrequest);
		return new ResponseEntity<AboutTestimonialsEntity>(aboutrequest, HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertSta")
	public ResponseEntity<?> updateStaServices(@RequestParam(name="data") String aboutData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		AboutStatisticalEntity aboutrequest = mapper.readValue(aboutData, AboutStatisticalEntity.class);
		System.out.print(aboutrequest);
		if(file != null) {
			saveStaImage(file,aboutrequest);
		}
		staService.saveSta(aboutrequest);
		return new ResponseEntity<AboutStatisticalEntity>(aboutrequest, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateTest/{tId}")
	public ResponseEntity<?> updateTestServices(@PathVariable("tId") String serId, @RequestParam(name="data") String aboutData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		AboutTestimonialsEntity aboutrequest = mapper.readValue(aboutData, AboutTestimonialsEntity.class);
		System.out.print(aboutrequest);
		final AboutTestimonialsEntity bEn = testService.getTest(serId).get();
		bEn.setName(aboutrequest.getName());
		bEn.setPosition(aboutrequest.getPosition());
		bEn.setIntro(aboutrequest.getIntro());
		if(file != null) {
			saveTestImage(file,bEn);
		}
		testService.saveTest(bEn);
		return new ResponseEntity<AboutTestimonialsEntity>(bEn, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateSta/{sId}")
	public ResponseEntity<?> updateStaServices(@PathVariable("sId") String serId, @RequestParam(name="data") String aboutData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		AboutStatisticalEntity aboutrequest = mapper.readValue(aboutData, AboutStatisticalEntity.class);
		System.out.print(aboutrequest);
		final AboutStatisticalEntity bEn = staService.getSta(serId).get();
		bEn.setTitle(aboutrequest.getTitle());
		bEn.setContent(aboutrequest.getContent());
		bEn.setCount(aboutrequest.getCount());
		if(file != null) {
			saveStaImage(file,bEn);
		}
		staService.saveSta(bEn);
		return new ResponseEntity<AboutStatisticalEntity>(bEn, HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateDetailServices(@RequestParam(name="data") String aboutData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
		AboutDetailEntity aboutrequest = mapper.readValue(aboutData, AboutDetailEntity.class);
		System.out.print(aboutrequest);
		final AboutDetailEntity bEn = detailService.getDetail("1").get();
		bEn.setTitle(aboutrequest.getTitle());
		bEn.setContent(aboutrequest.getContent());
		if(file != null) {
			saveDetailImage(file,bEn);
		}
		detailService.saveDetail(bEn);
		return new ResponseEntity<AboutDetailEntity>(bEn, HttpStatus.OK);
	}

	@PutMapping(value = "/updateClient")
	public ResponseEntity<?> updateClientServices(@RequestParam(name="data") String aboutData, @RequestParam(name = "file", required = false)
	MultipartFile[] files) throws JsonMappingException, JsonProcessingException {
		
		logger.info("update Client");
		AboutClientEntity aboutrequest = mapper.readValue(aboutData, AboutClientEntity.class);
		//System.out.print(aboutrequest);
		final AboutClientEntity bEn = clientService.getClient("1").get();
		bEn.setContent(aboutrequest.getContent());
		clientService.saveClient(bEn);
		System.out.println("1");
		System.out.println(files.length);
		if(files != null) {
			System.out.println("2");
			saveClientImage(files,bEn);
		}
		//clientService.saveClient(bEn);
		
		AboutDto aboutDto = AboutConverter.getInstance().clientEntityToDto(clientService.getClient("1").get());
		return new ResponseEntity<AboutDto>(aboutDto, HttpStatus.OK);
	}
	
	public void saveClientImage(MultipartFile[] files,AboutClientEntity bEn) {
		System.out.println("3");
		for (MultipartFile file : files) {
			System.out.println("4");
			//System.out.println("hello");
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			if(name.contains("..")) {
				logger.error("error for path of file");
			}
			try {
			AboutImgEntity image = new AboutImgEntity();
			image.setFileName(name);
			image.setFileType(file.getContentType());
			image.setData(file.getBytes());
			image.setClient(bEn);
			image = clientService.saveClientImg(image);
			bEn.getImg().add(image);
			} catch (IOException e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			}
		}
	}

	
	//update imdage
//	public void saveClientImage(MultipartFile file, AboutClientEntity bEn) {
//		String name = StringUtils.cleanPath(file.getOriginalFilename());
//		if(name.contains("..")) {
//			logger.error("error for path of file");
//		}
//		
//		try {
//			ImageEntity imgBlog;
//			if( bEn.getImg() == null) {
//				ImageEntity imgEn= new ImageEntity();
//				imgEn.setFileName(name);
//				imgEn.setFileType(file.getContentType());
//				imgEn.setData(file.getBytes());
//				imgBlog = blogService.saveImage(imgEn);
//				bEn.setImg(imgBlog);
//			}
//			else {
//				imgBlog = bEn.getImg(); 
//				imgBlog.setFileName(name);
//				imgBlog.setFileType(file.getContentType());
//				imgBlog.setData(file.getBytes());
//				imgBlog = blogService.saveImage(imgBlog);
//				bEn.setImg(imgBlog);
//			}
//		} 
//		catch (IOException e) {
//			logger.error(e.toString());
//		}
//	}
	
	//update imdage
	public void saveDetailImage(MultipartFile file, AboutDetailEntity bEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if(name.contains("..")) {
			logger.error("error for path of file");
		}
		
		try {
			ImageEntity imgBlog;
			if( bEn.getImg() == null) {
				ImageEntity imgEn= new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgEn);
				bEn.setImg(imgBlog);
			}
			else {
				imgBlog = bEn.getImg(); 
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				imgBlog = blogService.saveImage(imgBlog);
				bEn.setImg(imgBlog);
			}
		} 
		catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	//update imdage
		public void saveTestImage(MultipartFile file, AboutTestimonialsEntity bEn) {
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			if(name.contains("..")) {
				logger.error("error for path of file");
			}
			
			try {
				ImageEntity imgBlog;
				if( bEn.getImg() == null) {
					ImageEntity imgEn= new ImageEntity();
					imgEn.setFileName(name);
					imgEn.setFileType(file.getContentType());
					imgEn.setData(file.getBytes());
					imgBlog = blogService.saveImage(imgEn);
					bEn.setImg(imgBlog);
				}
				else {
					imgBlog = bEn.getImg(); 
					imgBlog.setFileName(name);
					imgBlog.setFileType(file.getContentType());
					imgBlog.setData(file.getBytes());
					imgBlog = blogService.saveImage(imgBlog);
					bEn.setImg(imgBlog);
				}
			} 
			catch (IOException e) {
				logger.error(e.toString());
			}
		}
	
		//update imdage
				public void saveStaImage(MultipartFile file, AboutStatisticalEntity bEn) {
					String name = StringUtils.cleanPath(file.getOriginalFilename());
					if(name.contains("..")) {
						logger.error("error for path of file");
					}
					
					try {
						ImageEntity imgBlog;
						if( bEn.getImg() == null) {
							ImageEntity imgEn= new ImageEntity();
							imgEn.setFileName(name);
							imgEn.setFileType(file.getContentType());
							imgEn.setData(file.getBytes());
							imgBlog = blogService.saveImage(imgEn);
							bEn.setImg(imgBlog);
						}
						else {
							imgBlog = bEn.getImg(); 
							imgBlog.setFileName(name);
							imgBlog.setFileType(file.getContentType());
							imgBlog.setData(file.getBytes());
							imgBlog = blogService.saveImage(imgBlog);
							bEn.setImg(imgBlog);
						}
					} 
					catch (IOException e) {
						logger.error(e.toString());
					}
				}	
	
	@PostMapping(value = "/deleteTest")
	   public ResponseEntity<?> deleteAboutTest(@RequestBody List<String> id) {
	      
	      logger.info("Delete Test");
	      testService.delete(id);
	      return new ResponseEntity<String>("Delete Successfull", HttpStatus.OK);
	 }
	
	@PostMapping(value = "/deleteSta")
	   public ResponseEntity<?> deleteAboutSta(@RequestBody List<String> id) {
	      
	      logger.info("Delete Statistical");
	      staService.delete(id);
	      return new ResponseEntity<String>("Delete Successfull", HttpStatus.OK);
	 }
	
	@DeleteMapping(value = "/deleteImg/{id}")
	public ResponseEntity<?> deleteClientImg(@PathVariable String id) {
		logger.info("delete services Img");
		clientService.deleteImg(id);
		return new ResponseEntity<String>("delete successfull!", HttpStatus.OK);
	}
	
//	Get Image with file Type
	@GetMapping(value = "/viewJPEG/{fileId}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPEImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		AboutImgEntity img = clientService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	@GetMapping(value = "/viewPNG/{fileId}",produces = MediaType.IMAGE_PNG_VALUE)
	public void getPNGImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		AboutImgEntity img = clientService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	@GetMapping(value = "/viewJPG/{fileId}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPGImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		AboutImgEntity img = clientService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	@GetMapping(value = "/viewGIF/{fileId}",produces = MediaType.IMAGE_GIF_VALUE)
	public void getGIFImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		AboutImgEntity img = clientService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_GIF_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
//	
}

