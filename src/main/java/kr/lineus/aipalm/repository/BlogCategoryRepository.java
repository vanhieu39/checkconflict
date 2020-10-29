package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.lineus.aipalm.entity.BlogCategoryEntity;

public interface BlogCategoryRepository extends JpaRepository<BlogCategoryEntity, Integer> {
	
}
