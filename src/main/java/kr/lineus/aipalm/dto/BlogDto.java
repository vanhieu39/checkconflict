package kr.lineus.aipalm.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import kr.lineus.aipalm.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
	
	private String id;
	private String title;
	private int quantityComment;
	private String content;
	private ImageDto img;
	private BlogAuthorElementDto author;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createdDateTime;
	private BlogCategoryElementDto category;
	private Set<BlogTagElementDto> tags = new HashSet<BlogTagElementDto>();
	private List<BlogCommentDto> comments = new ArrayList<BlogCommentDto>();
}
