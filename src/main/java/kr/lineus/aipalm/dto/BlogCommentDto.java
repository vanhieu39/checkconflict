package kr.lineus.aipalm.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogCommentDto {
	private Integer id;
	private String content;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime created_datetime;
	private Integer userId;
	private String name;
	private String email;
	private String img;
}
