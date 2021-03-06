package kr.lineus.aipalm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogAuthorElementDto {
	private String id;
	private String name;
	private String sns;
	private String intro;
}
