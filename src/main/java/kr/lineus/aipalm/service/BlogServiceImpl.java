package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.BlogAuthorEntity;
import kr.lineus.aipalm.entity.BlogCategoryEntity;
import kr.lineus.aipalm.entity.BlogCommentEntity;
import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.BlogTagEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.UserEntity;
import kr.lineus.aipalm.repository.BlogAuthorRepository;
import kr.lineus.aipalm.repository.BlogCategoryRepository;
import kr.lineus.aipalm.repository.BlogCommentRepository;
import kr.lineus.aipalm.repository.BlogRepository;
import kr.lineus.aipalm.repository.BlogTagRepository;
import kr.lineus.aipalm.repository.ImageRepository;
import kr.lineus.aipalm.repository.UserRepository;

@Service
@Transactional
@Qualifier("blogService")
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	BlogRepository blogRepo;
	
	@Autowired
	BlogTagRepository tagRepo;
	
	@Autowired
	ImageRepository imgRepo;
	
	@Autowired
	BlogCategoryRepository cateRepo;
	
	@Autowired
	BlogAuthorRepository authorRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	BlogCommentRepository commentRepo;
	
	@Override
	public List<BlogDataEntity> loadAllBlog() {
		return blogRepo.findAll();
	}

	@Override
	public Optional <BlogDataEntity> getBlog(String id) {
		return blogRepo.findById(id);
	}

	@Override
	public Optional<BlogCategoryEntity> getCategory(int catId) {
		return cateRepo.findById(catId);
	}

	@Override
	public Optional<BlogTagEntity> getTag(int tagId) {
		return tagRepo.findById(tagId);
	}

	@Override
	public Optional<BlogAuthorEntity> getAuthor(String autId) {
		return authorRepo.findById(autId);
	}

	
	@Override
	public BlogDataEntity saveBlog(BlogDataEntity blog) {
		return blogRepo.save(blog);
	}
	
	@Override
	public void deleteBlogs(List<String> idArr) {
		blogRepo.deleteByIdIn(idArr);
	}
	
	@Override
	public List<BlogTagEntity> loadAllTag() {
		return tagRepo.findAll();
	}

	@Override
	public Optional<BlogTagEntity> findTagByName(String name) {
		return tagRepo.findByName(name);
	}
	
	@Override
	public boolean haveTag(Example<BlogTagEntity> ex) {
		return tagRepo.exists(ex);
	}

	@Override
	public BlogTagEntity saveTag(BlogTagEntity tag) {
		return tagRepo.save(tag);
	}
	
	@Override
	public ImageEntity saveImage(ImageEntity img) {
		return imgRepo.save(img);
	}

	@Override
	public Optional<ImageEntity> findImageById(String id) {
		return imgRepo.findById(id);
	}

	@Override
	public List<BlogCategoryEntity> loadAllCategory() {
		return cateRepo.findAll();
	}
	
	@Override
	public Optional<BlogCategoryEntity> findCateById(int id) {
		return cateRepo.findById(id);
	}

	@Override
	public Optional<BlogAuthorEntity> findAuthorById(String id) {
		return authorRepo.findById(id);
	}

	@Override
	public Optional<BlogAuthorEntity> findAuthorByUser(UserEntity user) {
		return authorRepo.findByUser(user);
	}
	
	@Override
	public void deleteBlog(String id) {
		blogRepo.deleteById(id);
		
	}

	@Override
	public Optional<UserEntity> findUserById(long userId) {
		return userRepo.findById(userId);
	}

	@Override
	public BlogCommentEntity saveComment(BlogCommentEntity comment) {
		return commentRepo.save(comment);
	}

	@Override
	public void deleteImage(List<String> idArr) {
		imgRepo.deleteByIdIn(idArr);
	}
}
