package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.AboutStatisticalEntity;
import kr.lineus.aipalm.entity.AboutTestimonialsEntity;
import kr.lineus.aipalm.entity.ImageEntity;

public interface AboutStatisticalService {
	List<AboutStatisticalEntity> loadStatistical();
	Optional <AboutStatisticalEntity> getSta( String id);
	AboutStatisticalEntity saveSta(AboutStatisticalEntity sta);
	ImageEntity saveImage(ImageEntity img);
	
	void delete(List<String> id);
}
