package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeSubMenuEntity;

public interface HomeSubMenuService {
	List<HomeSubMenuEntity> loadAllHomeSubMenuService();
	Optional <HomeSubMenuEntity> getHomeSubMenu(String id);
	HomeSubMenuEntity saveHomeSubMenu(HomeSubMenuEntity menu);
	void delete(String id);
}
