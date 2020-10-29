package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeOrderLayoutEntity;

public interface HomeOrderLayoutImgService {

	List<HomeOrderLayoutEntity> loadAllHomeOrderLayout();
	Optional<HomeOrderLayoutEntity> getHomeOrderLayout(int id);
	HomeOrderLayoutEntity saveHomeOrLayout(HomeOrderLayoutEntity homeOrderLayout);
}
