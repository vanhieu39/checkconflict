package kr.lineus.aipalm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

import kr.lineus.aipalm.converter.BlogConverter;
import kr.lineus.aipalm.dto.BlogCategoryDto;
import kr.lineus.aipalm.dto.BlogCommentDto;
import kr.lineus.aipalm.dto.BlogDto;
import kr.lineus.aipalm.dto.BlogTagDto;
import kr.lineus.aipalm.dto.BlogTagElementDto;
import kr.lineus.aipalm.dto.CkImage;
import kr.lineus.aipalm.dto.ImageDto;
import kr.lineus.aipalm.entity.BlogAuthorEntity;
import kr.lineus.aipalm.entity.BlogCategoryEntity;
import kr.lineus.aipalm.entity.BlogCommentEntity;
import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.BlogTagEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.UserEntity;
import kr.lineus.aipalm.service.BlogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
	public static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	@Qualifier("blogService")
	BlogService blogService;
	
	@Autowired
	ObjectMapper mapper;
	
	@GetMapping(value="/list")
	public ResponseEntity<?> loadAllBlog() {
		logger.info("Loading all blog");
		List <BlogDto> blogList = BlogConverter.getInstance().blogDataEntityToDtoList(blogService.loadAllBlog());
		return new ResponseEntity<List<BlogDto>>(blogList, HttpStatus.OK);
		
//		List <BlogDataEntity> blogList = blogService.loadAllBlog();
//		return new ResponseEntity<List<BlogDataEntity>>(blogList, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/get/{blogId}")
	public ResponseEntity<?> getBlogById(@PathVariable String blogId) {
		logger.info("Loading blog by Id: ", blogId);
		BlogDto blogDto = BlogConverter.getInstance().blogDataEntityToDto(blogService.getBlog(blogId).get());
		return new ResponseEntity<BlogDto>(blogDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listByCat/{catId}")
	public ResponseEntity<?> getBlogByCat(@PathVariable String catId) {
		
		logger.info("Loading blog by Category: ", catId);
		int catIdInt = Integer.parseInt(catId);
		List <BlogDataEntity> blogEnList = new ArrayList<>(blogService.getCategory(catIdInt).get().getBlogs());
		List <BlogDto> blogList = BlogConverter.getInstance().blogDataEntityToDtoList(blogEnList);
		return new ResponseEntity<List<BlogDto>>(blogList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listByTag/{tagId}")
	public ResponseEntity<?> getBlogByTag(@PathVariable String tagId) {
		
		logger.info("Loading blog by Tag id: ", tagId);
		int tagIdInt = Integer.parseInt(tagId);
		List <BlogDataEntity> blogEnList = new ArrayList<>(blogService.getTag(tagIdInt).get().getBlogs());
		List <BlogDto> blogList = BlogConverter.getInstance().blogDataEntityToDtoList(blogEnList);
		return new ResponseEntity<List<BlogDto>>(blogList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listByAuthor/{autId}")
	public ResponseEntity<?> getBlogByAuthor(@PathVariable String autId) {
		
		logger.info("Loading blog by Author: ", autId);
		List <BlogDataEntity> blogEnList = new ArrayList<>(blogService.getAuthor(autId).get().getBlogs());
		List <BlogDto> blogList = BlogConverter.getInstance().blogDataEntityToDtoList(blogEnList);
		return new ResponseEntity<List<BlogDto>>(blogList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/tag/list")
	public ResponseEntity<?> loadAllTag() {
		
		logger.info("Loading all blog_tag");
		List <BlogTagDto> tagList = BlogConverter.getInstance().blogTagEntityToDtoList(blogService.loadAllTag());
		return new ResponseEntity<List<BlogTagDto>>(tagList, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{blogId}")
	public ResponseEntity<?> updateBlog(@PathVariable("blogId") String blogId, @RequestParam("data") String blogData, 
										@RequestParam(name = "file", required = false) MultipartFile file) 
										throws JsonMappingException, JsonProcessingException {
		
		logger.info("Updating an blog");
		BlogDto blogRequest = mapper.readValue(blogData, BlogDto.class);
		final BlogDataEntity bEn = blogService.getBlog(blogId).get();
		if(bEn != null) {
			bEn.setTitle(blogRequest.getTitle());
			bEn.setContent(blogRequest.getContent());
			BlogCategoryEntity catEn = blogService.findCateById(blogRequest.getCategory().getId()).get();
			bEn.setCategory(catEn);
			saveTags(blogRequest.getTags(), bEn);
			if(file != null) {
				saveBlogImage(file, bEn);
			}
		}
		blogService.saveBlog(bEn);
		BlogDto a = BlogConverter.getInstance().blogDataEntityToDto(bEn);
		return new ResponseEntity<BlogDto>(a, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> createBlog(@RequestParam("data") String blogData , 
										@RequestParam(name = "file", required = false) MultipartFile file ) 
										throws JsonMappingException, JsonProcessingException {
		
		logger.info("Create Blog");
		BlogDto blogRequest =  mapper.readValue(blogData, BlogDto.class);
		BlogDataEntity bEn = BlogConverter.getInstance().blogDtoToEntity(blogRequest);
		BlogCategoryEntity catEn = blogService.findCateById(blogRequest.getCategory().getId()).get();
		bEn.setCategory(catEn);
		UserEntity user = blogService.findUserById(Long.parseLong(blogRequest.getAuthor().getId())).get();
		BlogAuthorEntity autEn = blogService.findAuthorByUser(user).get();
		if(autEn == null) {
			autEn = new BlogAuthorEntity() ;
			autEn.setUser(user);
		}
		bEn.setAuthor(autEn);
		saveTags(blogRequest.getTags(), bEn);
		if(file != null) {
			saveBlogImage(file, bEn);
		}
		blogService.saveBlog(bEn);
		BlogDto a = BlogConverter.getInstance().blogDataEntityToDto(bEn);
		return new ResponseEntity<BlogDto>(a, HttpStatus.OK);
	}
	
	public void saveBlogImage(MultipartFile file, BlogDataEntity bEn) {
		
		logger.info("saveBlogImage");
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if(name.contains("..")) {
			logger.error("error for path of file");
		}
		
		try {
			ImageEntity imgBlog;
			if( bEn.getImg() == null) {
				logger.info("save img with null");
				ImageEntity imgEn= new ImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				//imgBlog = blogService.saveImage(imgEn);
				//bEn.setImg(imgBlog);
				bEn.setImg(imgEn);
			}
			else {
				logger.info("save img with not null");
				imgBlog = bEn.getImg(); 
				imgBlog.setFileName(name);
				imgBlog.setFileType(file.getContentType());
				imgBlog.setData(file.getBytes());
				//imgBlog = blogService.saveImage(imgBlog);
				bEn.setImg(imgBlog);
			}
		} 
		catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void saveTags(Set<BlogTagElementDto> tagList, BlogDataEntity bEn) {
		
		logger.info("save tags");
		bEn.getTags().clear();
		for(BlogTagElementDto tag : tagList) {
			//Optional<BlogTagEntity> found = blogService.findTagByName(tag.getName());
			BlogTagEntity tagEx = new BlogTagEntity();
			tagEx.setName(tag.getName());
			Example<BlogTagEntity> example = Example.of(tagEx, ExampleMatcher.matchingAny());
			boolean existTag = blogService.haveTag(example);
			if(existTag) {
				bEn.getTags().add(blogService.findTagByName(tag.getName()).get());
			}
			else {
				BlogTagEntity newTag = new BlogTagEntity();
				newTag.setName(tag.getName());
				bEn.getTags().add(newTag);
			}
		}
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteBlogs(@RequestBody List<String> idArray) {
		
		logger.info("Delete Blog");
		blogService.deleteBlogs(idArray);
		return new ResponseEntity<String>("Delete Successfull", HttpStatus.OK);
	}
	
	@GetMapping(value = "/viewJPG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get JPG Image");
		ImageEntity img = blogService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/viewJPEG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPEGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get JPEG Image");
		ImageEntity img = blogService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/viewPNG/{fileId}", produces = MediaType.IMAGE_PNG_VALUE)
	public void getPNGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get PNG Image");
		ImageEntity img = blogService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/viewGIF/{fileId}", produces = MediaType.IMAGE_GIF_VALUE)
	public void getGIFImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get GIF Image");
		ImageEntity img = blogService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_GIF_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/category/list")
	public ResponseEntity<?> loadAllCategory() {
		
		logger.info("Loading all category");
		List<BlogCategoryDto> catListDto = BlogConverter.getInstance().catEntityToDtoList(blogService.loadAllCategory());
		return new ResponseEntity<List<BlogCategoryDto>>(catListDto, HttpStatus.OK);
	}
	
	@PostMapping(value= "/comment/create/{blogId}")
	public ResponseEntity<?> createComment(@PathVariable String blogId, @RequestParam("data") String comment) throws JsonMappingException, JsonProcessingException {
		
		logger.info("Create comment");
		BlogCommentEntity commentEn = mapper.readValue(comment, BlogCommentEntity.class);
		BlogDataEntity blogEn = blogService.getBlog(blogId).get();
//		BlogCommentEntity commentEn = new BlogCommentEntity();
//		commentEn.setContent(comment.getContent());
//		commentEn.setUserId(comment.getUserId());
//		commentEn.setEmail(comment.getEmail());
//		commentEn.setImg(comment.getImg());
//		commentEn.setName(comment.getName());
		commentEn.setBlog(blogEn);
		blogService.saveComment(commentEn);
		return new ResponseEntity<String>("Successfull", HttpStatus.OK);
	}
	
	@PostMapping (value= "image/save")
	public ResponseEntity<?> saveImage(@RequestParam (value="file") MultipartFile file) {
		
		logger.info("saveImage");
		ImageEntity imgEditor = new ImageEntity();
		ImageDto imgDto= new ImageDto();
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}
		
		try {
			imgEditor.setFileName(name);
			imgEditor.setFileType(file.getContentType());
			imgEditor.setData(file.getBytes());
			imgEditor = blogService.saveImage(imgEditor);
			imgDto.setId(imgEditor.getId());
			imgDto.setFileName(imgEditor.getFileName());
			imgDto.setFileType(imgEditor.getFileType());
		} catch (IOException ex) {
			logger.error(ex.toString());
		}
		return new ResponseEntity<ImageDto> (imgDto, HttpStatus.OK);
	} 
	
	@PostMapping (value= "/image/saveck")
	public ResponseEntity<?> saveCkImage(@RequestParam (value="upload") MultipartFile file) {
		
		logger.info("saveImage");
		CkImage ck = new CkImage();
		ImageEntity imgEditor = new ImageEntity();
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}
		
		try {
			imgEditor.setFileName(name);
			imgEditor.setFileType(file.getContentType());
			imgEditor.setData(file.getBytes());
			imgEditor = blogService.saveImage(imgEditor);
			ck.setUrl("http://aipalm.co.kr/aipalm" + "/api/v1/blog/view" + imgEditor.getFileType().replace("image/","").toUpperCase() + "/" + imgEditor.getId().toString());
			//ck.setUrl("http://localhost:18080" + "/api/v1/blog/view" + imgEditor.getFileType().replace("image/","").toUpperCase() + "/" + imgEditor.getId().toString());
		} catch (IOException ex) {
			logger.error(ex.toString());
		}
		return new ResponseEntity<CkImage> (ck, HttpStatus.OK);
	}
	
	@PostMapping (value="image/delete")
	public ResponseEntity<?> deleteImage(@RequestBody List<String> idArr ) {
		blogService.deleteImage(idArr);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

}
	
