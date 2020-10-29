package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.lineus.aipalm.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String>{
	Optional<ImageEntity> findById(String id);
	void deleteByIdIn(List<String> idArr);
}
