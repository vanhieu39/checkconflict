package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.PortfolioCategory;
import kr.lineus.aipalm.entity.PortfolioDataEntity;
import kr.lineus.aipalm.entity.PortfolioImageEntity;

public interface PortfolioService {
	public List<PortfolioDataEntity> loadAllportfolio();

	Optional<PortfolioDataEntity> getPortfolioById(String id);

	Optional<PortfolioCategory> getPortfolioCategoryById(String id);

	PortfolioDataEntity savePortfolio(PortfolioDataEntity portfolio);

	List<PortfolioCategory> loadAllCategory();

	PortfolioImageEntity savePortfolioImage(PortfolioImageEntity img);

	List<PortfolioImageEntity> loaPortfolioImageEntities(PortfolioDataEntity portfolio);

	Optional<PortfolioImageEntity> loadImageById(String id);

	void deletePortfolios(List<String> idArray);

	void deleteImages(List<String> idArray);
}
