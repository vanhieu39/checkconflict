package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeCarouselEntity;
import kr.lineus.aipalm.repository.HomeCarouselRepository;

@Service
public class HomeCarouselServiceImpl implements HomeCarouselService {

	@Autowired
	HomeCarouselRepository homeCarouselRepos;
		
	
	@Override
	public List<HomeCarouselEntity> loadAllHomeCarousel() {
		// TODO Auto-generated method stub
		return homeCarouselRepos.findAll();
	}

	@Override
	public Optional<HomeCarouselEntity> getHomeCarousel(String id) {
		// TODO Auto-generated method stub
		return homeCarouselRepos.findById(id);
	}

	@Override
	public HomeCarouselEntity saveHomeCarousel(HomeCarouselEntity homeCarousel) {
		// TODO Auto-generated method stub
		return homeCarouselRepos.save(homeCarousel);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeCarouselRepos.deleteById(id);
	}

}
