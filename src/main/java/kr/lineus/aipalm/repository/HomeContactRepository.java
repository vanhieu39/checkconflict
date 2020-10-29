package kr.lineus.aipalm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.lineus.aipalm.entity.HomeContactEntity;

@Repository
public interface HomeContactRepository extends JpaRepository<HomeContactEntity, String>{

}
