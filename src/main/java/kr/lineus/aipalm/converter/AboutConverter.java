package kr.lineus.aipalm.converter;

import java.util.List;
import java.util.stream.Collectors;

import kr.lineus.aipalm.dto.AboutDto;
import kr.lineus.aipalm.dto.BlogCategoryDto;
import kr.lineus.aipalm.dto.BlogDto;
import kr.lineus.aipalm.dto.BlogTagDto;
import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.BlogCategoryEntity;
import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.BlogTagEntity;

public class AboutConverter extends BaseConverter{
	
	private static AboutConverter instance;
	
	public static AboutConverter getInstance() {
		if(instance == null) {
			instance = new AboutConverter();
		}
		return instance;
	}
	
	private AboutConverter() {};
	
	public AboutDto clientEntityToDto(AboutClientEntity clientEntity) {
		AboutDto aboutDto = mapper.map(clientEntity, AboutDto.class);
		return aboutDto;
	}
	public AboutClientEntity DtoToclientEntity(AboutDto aboutDto) {
		AboutClientEntity aboutClient = mapper.map(aboutDto, AboutClientEntity.class);
		return aboutClient;
	}
		
	public List<AboutDto> clientDTOList(List<AboutClientEntity> list) {
		return list.stream().map(b -> { return clientEntityToDto(b); }).collect(Collectors.toList());
	}
//	
//	public AboutClientEntity aboutDtoToEntity(AboutDto clientDto) {
//		AboutClientEntity bEn = mapper.map(clientDto, AboutClientEntity.class);
//		return bEn;
//	}
//	
//	public PortfolioDto portfolioDataEntity(PortfolioDataEntity portfolioDataEntity) {
//		PortfolioDto porfoliotDTO = mapper.map(portfolioDataEntity, PortfolioDto.class);
//		return porfoliotDTO;
//	}
//
//	public List<PortfolioDto> PorfolioDTOList(List<PortfolioDataEntity> portfolioDataEntities) {
//		return portfolioDataEntities.stream().map(b ->{return portfolioDataEntity(b);}).collect(Collectors.toList());
//	}
//	
//	public PortfolioDataEntity portDataDtoToEntity(PortfolioDto portfolioDto) {
//		PortfolioDataEntity portEntity = mapper.map(portfolioDto, PortfolioDataEntity.class);
//		return portEntity;
//	}

}
