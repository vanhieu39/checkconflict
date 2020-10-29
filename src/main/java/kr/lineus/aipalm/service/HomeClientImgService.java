package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeClientImgEntity;

public interface HomeClientImgService {

	List<HomeClientImgEntity> loadAllHomeClientImg();
	Optional<HomeClientImgEntity> getHomeClientImg(String id);
	HomeClientImgEntity saveHomeClientImg(HomeClientImgEntity homeClientImg);
	void delete(String id);
}
