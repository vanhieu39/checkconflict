package kr.lineus.aipalm.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogCategoryDto {
	private int id;
	private String name;
	private Set<BlogDto> blogs;
}
