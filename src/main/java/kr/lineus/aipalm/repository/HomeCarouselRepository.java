package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeCarouselEntity;

@Repository
public interface HomeCarouselRepository extends JpaRepository<HomeCarouselEntity, String>{

}
