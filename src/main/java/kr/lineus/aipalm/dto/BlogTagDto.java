package kr.lineus.aipalm.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogTagDto {
	private Integer id;
	private String name;
	private Set<BlogDto> blogs = new HashSet<BlogDto>();
}
