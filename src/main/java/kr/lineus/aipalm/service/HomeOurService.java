package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeOurServiceEntity;

public interface HomeOurService {

	List<HomeOurServiceEntity> loadAllHomeOur();
	Optional<HomeOurServiceEntity> getHomeOur(String id);
	HomeOurServiceEntity saveHomeOur(HomeOurServiceEntity homeOur);
	void delete(String id);
}
