package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeSubMenuEntity;

@Repository
public interface HomeSubMenuRepository extends JpaRepository<HomeSubMenuEntity, String>{
	
}
