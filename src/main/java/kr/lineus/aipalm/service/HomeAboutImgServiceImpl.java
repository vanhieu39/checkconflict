package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeAboutImgEntity;
import kr.lineus.aipalm.repository.HomeAboutImgRepository;

@Service
public class HomeAboutImgServiceImpl implements HomeAboutImgService {

	
	@Autowired
	HomeAboutImgRepository homeAboutRepos;
	
	@Override
	public List<HomeAboutImgEntity> loadAllHomeAboutImg() {
		// TODO Auto-generated method stub
		return homeAboutRepos.findAll();
	}

	@Override
	public Optional<HomeAboutImgEntity> getHomeAboutImg(String id) {
		// TODO Auto-generated method stub
		return homeAboutRepos.findById(id);
	}

	@Override
	public HomeAboutImgEntity saveHomeAboutImg(HomeAboutImgEntity homeAboutImg) {
		// TODO Auto-generated method stub
		return homeAboutRepos.save(homeAboutImg);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeAboutRepos.deleteById(id);
	}

}
