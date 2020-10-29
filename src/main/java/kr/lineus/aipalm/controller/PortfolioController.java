package kr.lineus.aipalm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import kr.lineus.aipalm.converter.PortfolioConverter;
import kr.lineus.aipalm.dto.PortfolioCategoryDTO;
import kr.lineus.aipalm.dto.PortfolioDto;
import kr.lineus.aipalm.entity.PortfolioCategory;
import kr.lineus.aipalm.entity.PortfolioDataEntity;
import kr.lineus.aipalm.entity.PortfolioImageEntity;
import kr.lineus.aipalm.service.PortfolioService;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("api/v1/portfolio/")
public class PortfolioController {
	public static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	@Autowired
	@Qualifier("portfolioService")
	PortfolioService portfolioService;
	@Autowired
	ObjectMapper mapper;
//	Load All Portfolio
	@GetMapping(value = "listAllPortfolio")
	public ResponseEntity<?> loadALlPortfolio() {
		logger.info("Get all portfolio");
		List<PortfolioDto> portfolioList = PortfolioConverter.getInconverter().PorfolioDTOList(portfolioService.loadAllportfolio());
		
		return new ResponseEntity<List<PortfolioDto>>(portfolioList,HttpStatus.OK);
	}
//	

//	Load Portfolio By Id
	@GetMapping(value = "detail/{portfolio_id}")
	public ResponseEntity<?> loadPortfolioById(HttpServletResponse response,@PathVariable("portfolio_id") String portfolio_id){
		logger.info("Get detai portfolio");
		PortfolioDto portfolioDto = PortfolioConverter.getInconverter().portfolioDataEntity(portfolioService.getPortfolioById(portfolio_id).get());
		return new ResponseEntity<PortfolioDto>(portfolioDto,HttpStatus.OK);
	}
//	
//	Load Portfolio By Category
	@GetMapping(value = "category/{category_id}")
	public ResponseEntity<?> loadPortfolioByCategory(HttpServletResponse response,@PathVariable("category_id") String category_id) {
		logger.info("Get portfolio by category");
		PortfolioCategoryDTO cateDto = PortfolioConverter.getInconverter().cateEntityToDto(portfolioService.getPortfolioCategoryById(category_id).get());
		List<PortfolioDto> lisportfolio = new ArrayList<>(cateDto.getPortfolio());
		return new ResponseEntity<List<PortfolioDto>>(lisportfolio,HttpStatus.OK);
	}
//	
//	Load All Category
	@GetMapping(value = "category/list")
	public ResponseEntity<?> loadAllCategory(){
		logger.info("Get all portfolio");
		List<PortfolioCategoryDTO> catList = PortfolioConverter.getInconverter().cateEntityToList(portfolioService.loadAllCategory());
		return new ResponseEntity<List<PortfolioCategoryDTO>>(catList,HttpStatus.OK);
	}
//	
//	InSert Portfolio
	@PostMapping(value = "create")
	public ResponseEntity<?> insertPortfolio(@RequestParam("data") String portfolioData,@RequestParam("files") MultipartFile[] files) throws JsonMappingException,JsonProcessingException {
		logger.info("Create Portfolio!");
		PortfolioDto portfolioRequest = mapper.readValue(portfolioData, PortfolioDto.class);
		PortfolioDataEntity portfolioEntity = PortfolioConverter.getInconverter().portDataDtoToEntity(portfolioRequest);
		PortfolioCategory cate = portfolioService.getPortfolioCategoryById(portfolioRequest.getCategory().getId()).get();
		portfolioEntity.setCategory(cate);
		portfolioEntity.setDateCreated(LocalDateTime.now());
		portfolioEntity.setUser_created_id("741df580-aa07-11ea-ac9d-d7acda3fada4");
		portfolioService.savePortfolio(portfolioEntity);
		saveImage(files, portfolioEntity);
		PortfolioDto pd = PortfolioConverter.getInconverter().portfolioDataEntity(portfolioEntity);
		return new ResponseEntity<PortfolioDto>(pd,HttpStatus.OK);
	}
//	

//	Save Image
	public void saveImage(MultipartFile[] files,PortfolioDataEntity portfolio) {
		System.out.println(files.length);
		for (MultipartFile file : files) {
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			if(name.contains("..")) {
				logger.error("error for path of file");
			}
			try {
				PortfolioImageEntity image = new PortfolioImageEntity();
				image.setFileName(name);
				image.setFileType(file.getContentType());
				image.setData(file.getBytes());
				image.setPortfolio(portfolio);
				image = portfolioService.savePortfolioImage(image);
			} catch (IOException e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			}
		}
	}
//	
//	Edit portfolio by Id
	@PutMapping(value = "/edit/{portfolioId}")
	public ResponseEntity<?> editPortfolio(@PathVariable("portfolioId") String portfolioId,@RequestParam("data") String portfolioData,@RequestParam(name="files",required = false) MultipartFile[] files) throws JsonMappingException,JsonProcessingException {
		logger.info("Editting portfolio");
		PortfolioDto portfolioRequest = mapper.readValue(portfolioData, PortfolioDto.class);
		final PortfolioDataEntity portfolioEntity = portfolioService.getPortfolioById(portfolioId).get();
		if (portfolioEntity!=null) {
			System.out.println();
			PortfolioCategory cate = portfolioService.getPortfolioCategoryById(portfolioRequest.getCategory().getId()).get();
			portfolioEntity.setClientName(portfolioRequest.getClientName());
			portfolioEntity.setContent(portfolioRequest.getContent());
			portfolioEntity.setProjectDate(portfolioRequest.getProjectDate());
			portfolioEntity.setProjectName(portfolioRequest.getProjectName());
			portfolioEntity.setProjectUrl(portfolioRequest.getProjectUrl());
			portfolioEntity.setCategory(cate);
			portfolioEntity.setDateCreated(LocalDateTime.now());
			portfolioEntity.setUser_created_id("741df580-aa07-11ea-ac9d-d7acda3fada4");
		}
		saveImage(files, portfolioEntity);
		portfolioService.savePortfolio(portfolioEntity);
		PortfolioDto pd = PortfolioConverter.getInconverter().portfolioDataEntity(portfolioEntity);
		return new ResponseEntity<PortfolioDto>(pd,HttpStatus.OK);
	}
//	
//	Get Image with file Type
	@GetMapping(value = "/viewJPEG/{fileId}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPEImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		PortfolioImageEntity img = portfolioService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	@GetMapping(value = "/viewPNG/{fileId}",produces = MediaType.IMAGE_PNG_VALUE)
	public void getPNGImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		PortfolioImageEntity img = portfolioService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	@GetMapping(value = "/viewJPG/{fileId}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPGImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		PortfolioImageEntity img = portfolioService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	@GetMapping(value = "/viewGIF/{fileId}",produces = MediaType.IMAGE_GIF_VALUE)
	public void getGIFImage(HttpServletResponse response,@PathVariable String fileId) throws IOException {
		PortfolioImageEntity img = portfolioService.loadImageById(fileId).get();
		InputStream inputStream = new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_GIF_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
//	
//	Delete portfolio
	@PostMapping(value = "delete")
	public ResponseEntity<?> deletePortfolios(@RequestBody List<String> idArray) {
		logger.info("Delete Portfolio");
		portfolioService.deletePortfolios(idArray);
		return new ResponseEntity<String>("Delete Successful",HttpStatus.OK);
	}
//	Dowload Image 
	@GetMapping("download/image/{image_id}")
	public ResponseEntity<?> dowloadImage(@PathVariable String image_id, HttpServletRequest request) {
		PortfolioImageEntity img = portfolioService.loadImageById(image_id).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(img.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\"" + img.getFileName() + "\"").body(new ByteArrayResource(img.getData()));
	}
//	Delete Image 
	@PostMapping(value = "delete/image")
	public ResponseEntity<?> deleteImage(@RequestBody List<String> idArray) {
		logger.info("Delete Image");
		portfolioService.deleteImages(idArray);
		return new ResponseEntity<String>("Delete Successful",HttpStatus.OK);
	}
}
