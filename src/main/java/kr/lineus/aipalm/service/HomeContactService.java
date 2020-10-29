package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeContactEntity;

public interface HomeContactService {
	List<HomeContactEntity> loadAllHomeContact();
	Optional<HomeContactEntity> getHomeContact(String id);
	HomeContactEntity saveHomeContact(HomeContactEntity homeContact);
	void delete(String id);
}
