package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.ServicesDataEntity;
import kr.lineus.aipalm.entity.ServicesOurSkillEntity;
import kr.lineus.aipalm.entity.ServicesPercentEntity;

public interface ServicesList {
	//services list
	List<ServicesDataEntity> loadAllServices();
	Optional <ServicesDataEntity> getServices( String id);
	ServicesDataEntity saveServices(ServicesDataEntity blog);
	void delete(String id);
	
	//services ourskill
	List<ServicesOurSkillEntity> loadAllOurSkill();
	Optional <ServicesOurSkillEntity> getServicesourskill( String id);
	ServicesOurSkillEntity saveServicesOurSkill(ServicesOurSkillEntity blog);
	void deleteourskill(String id);
	
	//services Percent
	List<ServicesPercentEntity> loadAllPercent();
	Optional <ServicesPercentEntity> getServicesPercent( String id);
	ServicesPercentEntity saveServicesPercent(ServicesPercentEntity blog);
	void deletepercent(String id);
}
