package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutImgEntity;
import kr.lineus.aipalm.entity.ImageEntity;

public interface AboutClientService {
	List<AboutClientEntity> loadAllClient();
	Optional <AboutClientEntity> getClient( String id);
	AboutClientEntity saveClient(AboutClientEntity client);
	ImageEntity saveImage(ImageEntity img);
	void delete(String id);
	
	
	AboutImgEntity saveClientImg(AboutImgEntity img);
	List<AboutImgEntity> aboutImageEntities();
	Optional<AboutImgEntity> loadImageById(String id);	
	void deleteImg(String id);

}
