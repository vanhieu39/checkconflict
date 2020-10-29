package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.entity.TeamEntity;
import kr.lineus.aipalm.repository.ImageRepository;
import kr.lineus.aipalm.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	TeamRepository teamRepos;
	
	@Autowired
	ImageRepository imgRepo;

	@Override
	public List<TeamEntity> loadAllTeam() {
		// load all Team
		return teamRepos.findAll();
	}

	@Override
	public Optional<TeamEntity> getTeam(String id) {
		// Update Team by id
		return teamRepos.findById(id);
	}

	@Override
	public TeamEntity saveTeam(TeamEntity team) {
		// insert Team , update 
		return teamRepos.save(team);
	}

	@Override
	public void delete(String id) {

		teamRepos.deleteById(id);
	}

	@Override
	public ImageEntity saveImage(ImageEntity img) {
		// TODO Auto-generated method stub
		return imgRepo.save(img);
	}

	@Override
	public Optional<ImageEntity> findImageById(String id) {
		// TODO Auto-generated method stub
		return imgRepo.findById(id);
	}
	

}
