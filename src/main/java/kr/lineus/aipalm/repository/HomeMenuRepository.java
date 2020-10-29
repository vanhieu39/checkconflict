package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeMenuEntity;

@Repository
public interface HomeMenuRepository extends JpaRepository<HomeMenuEntity, String>{

	public List<HomeMenuEntity> findAll();
	public Optional <HomeMenuEntity> findById(String id);
	
}
