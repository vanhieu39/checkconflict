package kr.lineus.aipalm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.lineus.aipalm.dto.PortfolioDto;
import kr.lineus.aipalm.entity.PortfolioCategory;
import kr.lineus.aipalm.entity.PortfolioDataEntity;

public interface PortfolioRepository extends JpaRepository<PortfolioDataEntity, String>{
	public void deleteByIdIn(List<String> idArr);
}
