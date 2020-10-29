package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeUseLinkEntity;

public interface HomeUseLinkService {

	List<HomeUseLinkEntity> loadAllHomeUseLink();
	Optional<HomeUseLinkEntity> getHomeUseLink(String id);
	HomeUseLinkEntity saveHomeUseLink(HomeUseLinkEntity useLink);
	void delete (String id);
}
