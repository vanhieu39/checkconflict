package kr.lineus.aipalm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.ServicesDataEntity;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesDataEntity, String> {
	public Optional<ServicesDataEntity> findById(String id);
}
