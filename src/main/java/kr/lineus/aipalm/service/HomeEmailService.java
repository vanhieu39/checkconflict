package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.HomeSubEmailEntity;


public interface HomeEmailService {
	List<HomeSubEmailEntity> loadAllHomeEmail();
	Optional<HomeSubEmailEntity> getHomeEmail(String id);
	HomeSubEmailEntity saveHomeEmail(HomeSubEmailEntity homeSubEmail);
	void delete(String id);
}
