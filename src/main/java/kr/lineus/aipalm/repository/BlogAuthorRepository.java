package kr.lineus.aipalm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.lineus.aipalm.entity.BlogAuthorEntity;
import kr.lineus.aipalm.entity.BlogDataEntity;
import kr.lineus.aipalm.entity.UserEntity;

public interface BlogAuthorRepository extends JpaRepository<BlogAuthorEntity, String> {
	//public Optional<BlogDataEntity> findById(String id);
	public Optional<BlogAuthorEntity> findByUser(UserEntity user);
}
