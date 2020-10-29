package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutStatisticalEntity;
import kr.lineus.aipalm.entity.AboutTestimonialsEntity;

@Repository
public interface AboutStatisticalRepository extends JpaRepository<AboutStatisticalEntity, String> {
//	 public List<AboutClientEntity> findAll();
//	 public Optional<AboutClientEntity> findById(String id);
	 public void deleteByIdIn(List<String> id);
}
