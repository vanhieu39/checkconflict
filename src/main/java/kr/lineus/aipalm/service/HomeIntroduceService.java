package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeIntroduceEntity;

public interface HomeIntroduceService {

	List<HomeIntroduceEntity> loadAllHomeIntroduce();
	Optional<HomeIntroduceEntity> getHomeIntroduce(String id);
	HomeIntroduceEntity saveHomeIntroduce(HomeIntroduceEntity homeIntroduce);
	void delete(String id);
}
