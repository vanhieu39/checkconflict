package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeAboutEntity;
import kr.lineus.aipalm.repository.HomeAboutRepository;

@Service
public class HomeAboutServceImpl implements HomeAboutService{

	@Autowired
	HomeAboutRepository homeAboutRepos;
	
	@Override
	public List<HomeAboutEntity> loadAllHomeAbout() {
		// TODO Auto-generated method stub
		return homeAboutRepos.findAll();
	}

	@Override
	public Optional<HomeAboutEntity> getHomeAbout(String id) {
		// TODO Auto-generated method stub
		return homeAboutRepos.findById(id);
	}

	@Override
	public HomeAboutEntity saveHomeAbout(HomeAboutEntity homeAbout) {
		// TODO Auto-generated method stub
		return homeAboutRepos.save(homeAbout);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeAboutRepos.deleteById(id);
	}

}
