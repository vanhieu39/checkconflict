package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutTestimonialsEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.repository.AboutClientRepository;
import kr.lineus.aipalm.repository.AboutTestimonialsRepository;
@Transactional
@Service
@Qualifier("testService")
public class AboutTestimonialsServiceImpl implements AboutTestimonialsService {

	@Autowired
	AboutTestimonialsRepository aboutTest;
	
	@Override
	public List<AboutTestimonialsEntity> loadTestimonials() {
		// TODO Auto-generated method stub
		return aboutTest.findAll();
	}

	@Override
	public Optional<AboutTestimonialsEntity> getTest(String id) {
		// TODO Auto-generated method stub
		return aboutTest.findById(id);
	}

	@Override
	public ImageEntity saveImage(ImageEntity img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AboutTestimonialsEntity saveTest(AboutTestimonialsEntity test) {
		// TODO Auto-generated method stub
		return aboutTest.save(test);
	}

	@Override
	public void delete(List<String> id) {
		// TODO Auto-generated method stub
		aboutTest.deleteByIdIn(id);
		
	}

}
