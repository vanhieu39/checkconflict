package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeCarouselEntity;

public interface HomeCarouselService {

	List<HomeCarouselEntity> loadAllHomeCarousel();
	Optional<HomeCarouselEntity> getHomeCarousel(String id);
	HomeCarouselEntity saveHomeCarousel(HomeCarouselEntity homeCarousel);
	void delete(String id);
	
}
