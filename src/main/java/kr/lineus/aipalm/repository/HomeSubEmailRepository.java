package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeSubEmailEntity;

@Repository
public interface HomeSubEmailRepository extends JpaRepository<HomeSubEmailEntity, String>{
	
}
