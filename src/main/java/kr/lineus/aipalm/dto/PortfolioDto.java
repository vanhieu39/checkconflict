package kr.lineus.aipalm.dto;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto {
	private String id;
	private String projectName;
	private String clientName;
	private String projectDate;
	private String projectUrl;
	private String content;
	private PortfolioCategoryElementDTO category;
	private Set<PortfolioImageDTO> image;
}
