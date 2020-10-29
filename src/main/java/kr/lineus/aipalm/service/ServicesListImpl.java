package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.ServicesDataEntity;
import kr.lineus.aipalm.entity.ServicesOurSkillEntity;
import kr.lineus.aipalm.entity.ServicesPercentEntity;
import kr.lineus.aipalm.repository.ServicesOurSkilRepository;
import kr.lineus.aipalm.repository.ServicesPercentRepository;
import kr.lineus.aipalm.repository.ServicesRepository;

@Service
public class ServicesListImpl implements ServicesList {
	@Autowired
	ServicesRepository serposintory;
	@Autowired
	ServicesOurSkilRepository servicesourskill;
	@Autowired
	ServicesPercentRepository servicespercent;

	//-------------------Services-------------------------
	@Override
	public List<ServicesDataEntity> loadAllServices() {
		// TODO Auto-generated method stub
		return serposintory.findAll();
	}

	@Override
	public Optional<ServicesDataEntity> getServices(String id) {
		// TODO Auto-generated method stub
		return serposintory.findById(id);
	}

	@Override
	public ServicesDataEntity saveServices(ServicesDataEntity ser) {
		// TODO Auto-generated method stub
		return serposintory.save(ser);
	}

	@Override
	public void delete(String id) {
		serposintory.deleteById(id);
	}

	//-------------------Services OurSkill-------------------------
	@Override
	public List<ServicesOurSkillEntity> loadAllOurSkill() {
		// TODO Auto-generated method stub
		return servicesourskill.findAll();
	}

	@Override
	public ServicesOurSkillEntity saveServicesOurSkill(ServicesOurSkillEntity our) {
		// TODO Auto-generated method stub
		return servicesourskill.save(our);
	}

	@Override
	public Optional<ServicesOurSkillEntity> getServicesourskill(String id) {
		// TODO Auto-generated method stub
		return servicesourskill.findById(id);
	}

	@Override
	public void deleteourskill(String id) {
		// TODO Auto-generated method stub
		servicesourskill.deleteById(id);
	}
	
	//-------------------Services Percent-------------------------
	@Override
	public List<ServicesPercentEntity> loadAllPercent() {
		// TODO Auto-generated method stub
		return servicespercent.findAll();
	}

	@Override
	public Optional<ServicesPercentEntity> getServicesPercent(String id) {
		// TODO Auto-generated method stub
		return servicespercent.findById(id);
	}

	@Override
	public ServicesPercentEntity saveServicesPercent(ServicesPercentEntity blog) {
		// TODO Auto-generated method stub
		return servicespercent.save(blog);
	}

	@Override
	public void deletepercent(String id) {
		// TODO Auto-generated method stub
		servicespercent.deleteById(id);
		
	}
}
