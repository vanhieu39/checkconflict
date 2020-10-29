package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeOurServiceEntity;

@Repository
public interface HomeOurServiceRepository extends JpaRepository<HomeOurServiceEntity, String>{

}
