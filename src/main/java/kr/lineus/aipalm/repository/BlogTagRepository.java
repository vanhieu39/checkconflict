package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.BlogTagEntity;

@Repository
public interface BlogTagRepository extends JpaRepository<BlogTagEntity, Integer> {
	
	List<BlogTagEntity> findAll();
	
	Optional<BlogTagEntity> findByName(String name);
	
	boolean existsTagByName(String name);
}
