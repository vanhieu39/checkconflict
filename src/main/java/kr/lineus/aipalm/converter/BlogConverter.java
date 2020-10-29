package kr.lineus.aipalm.converter;

import java.util.List;
import java.util.stream.Collectors;

import kr.lineus.aipalm.dto.BlogCategoryDto;
import kr.lineus.aipalm.dto.BlogDto;
import kr.lineus.aipalm.dto.BlogTagDto;
import kr.lineus.aipalm.entity.BlogCategoryEntity;
import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.BlogTagEntity;

public class BlogConverter extends BaseConverter{
	
	private static BlogConverter instance;
	
	public static BlogConverter getInstance() {
		if(instance == null) {
			instance = new BlogConverter();
		}
		return instance;
	}
	
	private BlogConverter() {};
	
//	public BlogDto blogDataEntityToDto( BlogDataEntity blogEntity) {
//		BlogDto blogDto = new BlogDto();
//		blogDto.setId(blogEntity.getId());
//		blogDto.setName(blogEntity.getName());
//		blogDto.setCreatedDateTime(blogEntity.getCreatedDateTime());
//		blogDto.setCategory(new BlogCategoryDto(blogEntity.getCategory().getId(), blogEntity.getCategory().getName()));
//		return blogDto;
//	}
	
	public BlogDto blogDataEntityToDto(BlogDataEntity blogEntity) {
		//ModelMapper modelMapper = new ModelMapper();
		BlogDto blogDto = mapper.map(blogEntity, BlogDto.class);
		return blogDto;
	}
	
	public List<BlogDto> blogDataEntityToDtoList(List<BlogDataEntity> list) {
		return list.stream().map(b -> { return blogDataEntityToDto(b); }).collect(Collectors.toList());
	}
	
	public BlogDataEntity blogDtoToEntity(BlogDto blogDto) {
		BlogDataEntity bEn = mapper.map(blogDto, BlogDataEntity.class);
		return bEn;
	}
	
	public BlogTagDto blogTagEntityToDto(BlogTagEntity tagEntity) {
		BlogTagDto tagDto = mapper.map(tagEntity, BlogTagDto.class);
		return tagDto;
	}
	
	public List<BlogTagDto> blogTagEntityToDtoList(List<BlogTagEntity> list) {
		return list.stream().map(t -> { return blogTagEntityToDto(t); }).collect(Collectors.toList());
	}
	
	public BlogCategoryDto catEntityToDto(BlogCategoryEntity catEn) {
		BlogCategoryDto catDto = mapper.map(catEn, BlogCategoryDto.class);
		return catDto;
	}
	
	public List<BlogCategoryDto> catEntityToDtoList(List<BlogCategoryEntity> list) {
		return list.stream().map(t -> {return catEntityToDto(t);}).collect(Collectors.toList());
	}
}
