package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeSubEmailEntity;
import kr.lineus.aipalm.repository.HomeSubEmailRepository;

@Service
public class HomeEmailServiceImpl implements HomeEmailService{

	@Autowired
	HomeSubEmailRepository homeSubEmailRepo;

	@Override
	public List<HomeSubEmailEntity> loadAllHomeEmail() {
		// TODO Auto-generated method stub
		return homeSubEmailRepo.findAll();
	}

	@Override
	public Optional<HomeSubEmailEntity> getHomeEmail(String id) {
		// TODO Auto-generated method stub
		return homeSubEmailRepo.findById(id);
	}

	@Override
	public HomeSubEmailEntity saveHomeEmail(HomeSubEmailEntity homeSubEmail) {
		// TODO Auto-generated method stub
		return homeSubEmailRepo.save(homeSubEmail);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeSubEmailRepo.deleteById(id);
	}
	
	
	

}
