package kr.lineus.aipalm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {

	public Optional<TeamEntity> findById(String id);
}
