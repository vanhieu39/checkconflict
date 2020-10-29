package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeSubMenuEntity;
import kr.lineus.aipalm.repository.HomeSubMenuRepository;

@Service
public class HomeSubMenuServiceImpl implements HomeSubMenuService {

	@Autowired
	HomeSubMenuRepository homeSubMenuRep;
	
	@Override
	public List<HomeSubMenuEntity> loadAllHomeSubMenuService() {
		// TODO Auto-generated method stub
		return homeSubMenuRep.findAll();
	}

	@Override
	public Optional<HomeSubMenuEntity> getHomeSubMenu(String id) {
		// TODO Auto-generated method stub
		return homeSubMenuRep.findById(id);
	}

	@Override
	public HomeSubMenuEntity saveHomeSubMenu(HomeSubMenuEntity menu) {
		// TODO Auto-generated method stub
		return homeSubMenuRep.save(menu);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeSubMenuRep.deleteById(id);
	}

	
}
