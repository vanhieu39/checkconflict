package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeContactEntity;
import kr.lineus.aipalm.repository.HomeContactRepository;


@Service
public class HomeContactServiceImpl implements HomeContactService{
	
	@Autowired
	HomeContactRepository homeContactRepos;
	

	@Override
	public List<HomeContactEntity> loadAllHomeContact() {
		// TODO Auto-generated method stub
		return homeContactRepos.findAll();
	}

	@Override
	public Optional<HomeContactEntity> getHomeContact(String id) {
		// TODO Auto-generated method stub
		return homeContactRepos.findById(id);
	}

	@Override
	public HomeContactEntity saveHomeContact(HomeContactEntity homeContact) {
		// TODO Auto-generated method stub
		return homeContactRepos.save(homeContact);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeContactRepos.deleteById(id);
	}

}
