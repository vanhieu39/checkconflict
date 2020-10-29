package kr.lineus.aipalm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.ServicesPercentEntity;
import kr.lineus.aipalm.repository.ServicesPercentRepository;

@Service
public class SerPercentServicesImpl implements SerPercentServices{
	@Autowired
	ServicesPercentRepository servicespercent;
	@Override
	public List<ServicesPercentEntity> loadAllPercent() {
		// TODO Auto-generated method stub
		return servicespercent.findAll();
	}

}
