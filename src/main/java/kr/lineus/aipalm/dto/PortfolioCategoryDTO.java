package kr.lineus.aipalm.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfolioCategoryDTO {
	private String id;
	private String category_name;
	private Set<PortfolioDto> portfolio;
}
