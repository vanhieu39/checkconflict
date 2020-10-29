package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.TeamEntity;

public interface TeamService {

	List<TeamEntity> loadAllTeam();
	Optional <TeamEntity> getTeam( String id);
	TeamEntity saveTeam(TeamEntity team);
	
	void delete(String id);
	ImageEntity saveImage(ImageEntity img);
	
	Optional<ImageEntity> findImageById(String id);
}
