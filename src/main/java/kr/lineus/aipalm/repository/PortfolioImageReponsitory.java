package kr.lineus.aipalm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.lineus.aipalm.entity.PortfolioDataEntity;
import kr.lineus.aipalm.entity.PortfolioImageEntity;

public interface PortfolioImageReponsitory extends JpaRepository<PortfolioImageEntity, String>{
	List<PortfolioImageEntity> findByPortfolio(PortfolioDataEntity portfolio);
	public void deleteByIdIn(List<String> idArr);
}

