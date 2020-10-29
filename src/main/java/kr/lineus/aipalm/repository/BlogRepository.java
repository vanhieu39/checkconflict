package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.BlogDataEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogDataEntity, String> {
	
	 public List<BlogDataEntity> findAll();
	 
	 public Optional<BlogDataEntity> findById(String id);
	 
	 public void deleteByIdIn(List<String> idArr);
}
