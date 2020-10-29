package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeAboutEntity;

public interface HomeAboutService {

	List<HomeAboutEntity> loadAllHomeAbout();
	Optional<HomeAboutEntity> getHomeAbout(String id);
	HomeAboutEntity saveHomeAbout(HomeAboutEntity homeAbout);
	void delete(String id);
}
