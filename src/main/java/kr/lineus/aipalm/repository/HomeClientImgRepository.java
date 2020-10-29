package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeClientImgEntity;

@Repository
public interface HomeClientImgRepository extends JpaRepository<HomeClientImgEntity, String>{

}
