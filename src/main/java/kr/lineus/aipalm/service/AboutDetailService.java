package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutDetailEntity;
import kr.lineus.aipalm.entity.ImageEntity;

public interface AboutDetailService {
	List<AboutDetailEntity> loadDetail();
	Optional <AboutDetailEntity> getDetail( String id);
	AboutDetailEntity saveDetail(AboutDetailEntity detail);
	ImageEntity saveImage(ImageEntity img);
	
	void delete(String id);
}
