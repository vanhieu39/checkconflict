package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutStatisticalEntity;
import kr.lineus.aipalm.entity.AboutTestimonialsEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.repository.AboutClientRepository;
import kr.lineus.aipalm.repository.AboutStatisticalRepository;
import kr.lineus.aipalm.repository.AboutTestimonialsRepository;
@Transactional
@Service
@Qualifier("staService")
public class AboutStatisticalServiceImpl implements AboutStatisticalService {

	@Autowired
	AboutStatisticalRepository aboutSta;
	
	@Override
	public List<AboutStatisticalEntity> loadStatistical() {
		// TODO Auto-generated method stub
		return aboutSta.findAll();
	}

	@Override
	public Optional<AboutStatisticalEntity> getSta(String id) {
		// TODO Auto-generated method stub
		return aboutSta.findById(id);
	}

	@Override
	public ImageEntity saveImage(ImageEntity img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AboutStatisticalEntity saveSta(AboutStatisticalEntity sta) {
		// TODO Auto-generated method stub
		return aboutSta.save(sta);
	}

	@Override
	public void delete(List<String> id) {
		// TODO Auto-generated method stub
		aboutSta.deleteByIdIn(id);
		
	}

}
