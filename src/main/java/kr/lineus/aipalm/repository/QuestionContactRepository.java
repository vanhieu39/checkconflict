package kr.lineus.aipalm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.QuestionContactEntity;

@Repository
public interface QuestionContactRepository extends JpaRepository<QuestionContactEntity, String> {

	public Optional<QuestionContactEntity> findById(String id);
}
