package kr.lineus.aipalm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutImgEntity;

@Repository
public interface AboutClientImgRepository extends JpaRepository<AboutImgEntity, String> {
//	List<AboutImgEntity> findByClient(AboutImgEntity client);
}
