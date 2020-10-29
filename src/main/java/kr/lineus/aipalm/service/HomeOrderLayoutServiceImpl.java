package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeOrderLayoutEntity;
import kr.lineus.aipalm.repository.HomeOrderLayoutRepository;


@Service
public class HomeOrderLayoutServiceImpl implements HomeOrderLayoutImgService {

	@Autowired
	HomeOrderLayoutRepository homeOrLayoutRepos;
	
	@Override
	public List<HomeOrderLayoutEntity> loadAllHomeOrderLayout() {
		// TODO Auto-generated method stub
		return homeOrLayoutRepos.findAll();
	}

	@Override
	public Optional<HomeOrderLayoutEntity> getHomeOrderLayout(int id) {
		// TODO Auto-generated method stub
		return homeOrLayoutRepos.findById(id);
	}

	@Override
	public HomeOrderLayoutEntity saveHomeOrLayout(HomeOrderLayoutEntity homeOrderLayout) {
		// TODO Auto-generated method stub
		return homeOrLayoutRepos.save(homeOrderLayout);
	}

	
}
