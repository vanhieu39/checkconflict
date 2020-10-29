package kr.lineus.aipalm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.ServicesOurSkillEntity;
import kr.lineus.aipalm.repository.ServicesOurSkilRepository;

@Service
public class SerOurskillServiceImpl implements SerOurskillService {
	@Autowired
	ServicesOurSkilRepository servicesourskill;
	@Override
	public List<ServicesOurSkillEntity> loadAllOurSkill() {
		// TODO Auto-generated method stub
		return servicesourskill.findAll();
	}

}
