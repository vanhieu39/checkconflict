package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeIntroduceEntity;
import kr.lineus.aipalm.repository.HomeIntroduceRepository;

@Service
public class HomeIntroduceServiceImpl implements HomeIntroduceService{

	@Autowired
	HomeIntroduceRepository homeIntroRepos;
	
	@Override
	public List<HomeIntroduceEntity> loadAllHomeIntroduce() {
		// TODO Auto-generated method stub
		return homeIntroRepos.findAll();
	}

	@Override
	public Optional<HomeIntroduceEntity> getHomeIntroduce(String id) {
		// TODO Auto-generated method stub
		return homeIntroRepos.findById(id);
	}

	@Override
	public HomeIntroduceEntity saveHomeIntroduce(HomeIntroduceEntity homeIntroduce) {
		// TODO Auto-generated method stub
		return homeIntroRepos.save(homeIntroduce);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeIntroRepos.deleteById(id);
	}

}
