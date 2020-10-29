package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeMenuEntity;
import kr.lineus.aipalm.repository.HomeMenuRepository;

@Service
public class HomeMenuServiceImpl implements HomeMenuService {

	@Autowired
	HomeMenuRepository homeMenuRepos;
	
	@Override
	public List<HomeMenuEntity> loadAllHomeMenuService() {
		// TODO Auto-generated method stub
		return homeMenuRepos.findAll();
	}

	@Override
	public Optional<HomeMenuEntity> getHomeMenu(String id) {
		// TODO Auto-generated method stub
		return homeMenuRepos.findById(id);
	}

	@Override
	public HomeMenuEntity saveHomeMenu(HomeMenuEntity menu) {
		// TODO Auto-generated method stub
		return homeMenuRepos.save(menu);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeMenuRepos.deleteById(id);
	}

}
