package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import kr.lineus.aipalm.entity.BlogAuthorEntity;
import kr.lineus.aipalm.entity.BlogCategoryEntity;
import kr.lineus.aipalm.entity.BlogCommentEntity;
import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.BlogTagEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.UserEntity;

public interface BlogService {
	public List<BlogDataEntity> loadAllBlog();
	
	Optional <BlogDataEntity> getBlog( String id);
	
	public Optional<BlogCategoryEntity> getCategory(int catId);
	
	public Optional<BlogTagEntity> getTag(int tagId);
	
	public Optional<BlogAuthorEntity> getAuthor(String autId);
	
	BlogDataEntity saveBlog(BlogDataEntity blog);
	
	void deleteBlogs(List<String> idArray);
	
	void deleteBlog(String id);
	
	List<BlogTagEntity> loadAllTag();
	
	Optional<BlogTagEntity> findTagByName(String name);
	
	boolean haveTag(Example<BlogTagEntity> name);
	
	BlogTagEntity saveTag(BlogTagEntity tag);
	
	ImageEntity saveImage(ImageEntity img);
	
	void deleteImage(List<String> idArr);
	
	Optional<ImageEntity> findImageById(String id);
	
	List<BlogCategoryEntity> loadAllCategory();
	
	Optional<BlogCategoryEntity> findCateById(int id);
	
	Optional<BlogAuthorEntity> findAuthorById(String id);
	
	Optional<BlogAuthorEntity> findAuthorByUser(UserEntity user);
	
	Optional<UserEntity> findUserById(long userId);
	
	BlogCommentEntity saveComment(BlogCommentEntity comment);
}
