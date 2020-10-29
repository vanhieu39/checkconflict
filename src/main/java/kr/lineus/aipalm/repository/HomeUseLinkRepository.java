package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeUseLinkEntity;

@Repository
public interface HomeUseLinkRepository extends JpaRepository<HomeUseLinkEntity, String>{
	
}
