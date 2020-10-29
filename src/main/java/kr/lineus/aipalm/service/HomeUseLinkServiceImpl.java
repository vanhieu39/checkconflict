package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeUseLinkEntity;
import kr.lineus.aipalm.repository.HomeUseLinkRepository;

@Service
public class HomeUseLinkServiceImpl implements HomeUseLinkService {

	@Autowired
	HomeUseLinkRepository homeuseRepos;
	
	@Override
	public List<HomeUseLinkEntity> loadAllHomeUseLink() {
		// TODO Auto-generated method stub
		return homeuseRepos.findAll();
	}

	@Override
	public Optional<HomeUseLinkEntity> getHomeUseLink(String id) {
		// TODO Auto-generated method stub
		return homeuseRepos.findById(id);
	}

	@Override
	public HomeUseLinkEntity saveHomeUseLink(HomeUseLinkEntity useLink) {
		// TODO Auto-generated method stub
		return homeuseRepos.save(useLink);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeuseRepos.deleteById(id);
	}

}
