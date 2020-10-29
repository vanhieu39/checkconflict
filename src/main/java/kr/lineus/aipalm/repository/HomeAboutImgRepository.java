package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeAboutImgEntity;

@Repository
public interface HomeAboutImgRepository extends JpaRepository<HomeAboutImgEntity, String>{

}
