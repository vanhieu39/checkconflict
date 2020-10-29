package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.AboutTestimonialsEntity;
import kr.lineus.aipalm.entity.ImageEntity;

public interface AboutTestimonialsService {
	List<AboutTestimonialsEntity> loadTestimonials();
	Optional <AboutTestimonialsEntity> getTest( String id);
	AboutTestimonialsEntity saveTest(AboutTestimonialsEntity test);
	ImageEntity saveImage(ImageEntity img);
	
	void delete(List<String> id);
}
