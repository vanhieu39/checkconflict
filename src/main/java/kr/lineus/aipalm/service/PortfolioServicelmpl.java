package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import kr.lineus.aipalm.entity.PortfolioCategory;
import kr.lineus.aipalm.entity.PortfolioDataEntity;
import kr.lineus.aipalm.entity.PortfolioImageEntity;
import kr.lineus.aipalm.repository.PortfolioCategoryReponsitory;
import kr.lineus.aipalm.repository.PortfolioImageReponsitory;
import kr.lineus.aipalm.repository.PortfolioRepository;

@Transactional
@Service
@Qualifier("portfolioService")
public class PortfolioServicelmpl implements PortfolioService{
	@Autowired
	PortfolioRepository portfolioRepo;
	@Autowired
	PortfolioCategoryReponsitory cateRepo;
	@Autowired
	PortfolioImageReponsitory imageRepo;
	@Override
	public List<PortfolioDataEntity> loadAllportfolio() {
		// TODO Auto-generated method stub
		return portfolioRepo.findAll();
	}
	@Override
	public Optional<PortfolioDataEntity> getPortfolioById(String id) {
		// TODO Auto-generated method stub
		return portfolioRepo.findById(id);
	}
	@Override
	public PortfolioDataEntity savePortfolio(PortfolioDataEntity portfolio) {
		// TODO Auto-generated method stub
		return portfolioRepo.save(portfolio);
	}
	@Override
	public List<PortfolioCategory> loadAllCategory() {
		// TODO Auto-generated method stub
		return cateRepo.findAll();
	}
	@Override
	public PortfolioImageEntity savePortfolioImage(PortfolioImageEntity img) {
		// TODO Auto-generated method stub
		return imageRepo.save(img);
	}
	
	@Override
	public Optional<PortfolioCategory> getPortfolioCategoryById(String id) {
		// TODO Auto-generated method stub
		return cateRepo.findById(id);
	}
	@Override
	public List<PortfolioImageEntity> loaPortfolioImageEntities(PortfolioDataEntity portfolio) {
		// TODO Auto-generated method stub
		return imageRepo.findByPortfolio(portfolio);
	}
	@Override
	public Optional<PortfolioImageEntity> loadImageById(String id) {
		// TODO Auto-generated method stub
		return imageRepo.findById(id);
	}
	
	@Override
	public void deletePortfolios(List<String> idArray) {
		// TODO Auto-generated method stub
		portfolioRepo.deleteByIdIn(idArray);
	}
	@Override
	public void deleteImages(List<String> idArray) {
		// TODO Auto-generated method stub
		imageRepo.deleteByIdIn(idArray);
	}
}
