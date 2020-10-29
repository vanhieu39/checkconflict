package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeOurServiceEntity;
import kr.lineus.aipalm.repository.HomeOurServiceRepository;

@Service
public class HomeOurServiceImpl implements HomeOurService{

	@Autowired
	HomeOurServiceRepository homeOurRepos;
	
	
	@Override
	public List<HomeOurServiceEntity> loadAllHomeOur() {
		// TODO Auto-generated method stub
		return homeOurRepos.findAll();
	}

	@Override
	public Optional<HomeOurServiceEntity> getHomeOur(String id) {
		// TODO Auto-generated method stub
		return homeOurRepos.findById(id);
	}

	@Override
	public HomeOurServiceEntity saveHomeOur(HomeOurServiceEntity homeOur) {
		// TODO Auto-generated method stub
		return homeOurRepos.save(homeOur);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeOurRepos.deleteById(id);
	}

}
