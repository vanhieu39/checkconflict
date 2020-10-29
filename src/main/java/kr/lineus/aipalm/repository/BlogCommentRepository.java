package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.BlogCommentEntity;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogCommentEntity, Integer> {

}
