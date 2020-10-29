package kr.lineus.aipalm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioImageDTO {
	private String id;
	private String fileName;
	private String fileType;

}
