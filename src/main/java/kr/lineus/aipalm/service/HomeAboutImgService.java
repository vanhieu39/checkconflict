package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeAboutImgEntity;

public interface HomeAboutImgService {

	List<HomeAboutImgEntity> loadAllHomeAboutImg();
	Optional<HomeAboutImgEntity> getHomeAboutImg(String id);
	HomeAboutImgEntity saveHomeAboutImg(HomeAboutImgEntity homeAboutImg);
	void delete(String id);
}
