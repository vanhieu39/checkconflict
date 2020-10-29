package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutDetailEntity;

@Repository
public interface AboutDetailRepository extends JpaRepository<AboutDetailEntity, String> {
//	 public List<AboutClientEntity> findAll();
//	 public Optional<AboutClientEntity> findById(String id);
}
