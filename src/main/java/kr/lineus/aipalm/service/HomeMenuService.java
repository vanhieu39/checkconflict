package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeMenuEntity;

public interface HomeMenuService {
	List<HomeMenuEntity> loadAllHomeMenuService();
	Optional <HomeMenuEntity> getHomeMenu(String id);
	HomeMenuEntity saveHomeMenu(HomeMenuEntity menu);
	void delete(String id);
}
