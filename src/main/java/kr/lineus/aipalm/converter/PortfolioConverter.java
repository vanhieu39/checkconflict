package kr.lineus.aipalm.converter;

import java.util.List;
import java.util.stream.Collectors;

import kr.lineus.aipalm.dto.PortfolioCategoryDTO;
import kr.lineus.aipalm.dto.PortfolioDto;
import kr.lineus.aipalm.entity.PortfolioCategory;
import kr.lineus.aipalm.entity.PortfolioDataEntity;

public class PortfolioConverter extends BaseConverter {
	private static PortfolioConverter inConverter;

	public static PortfolioConverter getInconverter() {
		if (inConverter == null) {
			inConverter = new PortfolioConverter();
		}
		return inConverter;
	}

	private PortfolioConverter() {
	};
	
	public PortfolioDto portfolioDataEntity(PortfolioDataEntity portfolioDataEntity) {
		PortfolioDto porfoliotDTO = mapper.map(portfolioDataEntity, PortfolioDto.class);
		return porfoliotDTO;
	}
	public List<PortfolioDto> PorfolioDTOList(List<PortfolioDataEntity> portfolioDataEntities) {
		return portfolioDataEntities.stream().map(b ->{return portfolioDataEntity(b);}).collect(Collectors.toList());
	}
	
	public PortfolioDataEntity portDataDtoToEntity(PortfolioDto portfolioDto) {
		PortfolioDataEntity portEntity = mapper.map(portfolioDto, PortfolioDataEntity.class);
		return portEntity;
	}
	public PortfolioCategoryDTO cateEntityToDto(PortfolioCategory cateEntity) {
		PortfolioCategoryDTO cateDto = mapper.map(cateEntity, PortfolioCategoryDTO.class);
		return cateDto;
	}
	public List<PortfolioCategoryDTO> cateEntityToList(List<PortfolioCategory> categoriesList) {
		return categoriesList.stream().map(t -> {return cateEntityToDto(t);}).collect(Collectors.toList());
	}
}
