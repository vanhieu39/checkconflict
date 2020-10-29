package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.AboutClientEntity;
import kr.lineus.aipalm.entity.AboutImgEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.repository.AboutClientImgRepository;
import kr.lineus.aipalm.repository.AboutClientRepository;

@Service
@Qualifier("clientService")
public class AboutClientServiceImpl implements AboutClientService {

	@Autowired
	AboutClientRepository aboutClient;
	
	@Autowired
	AboutClientImgRepository imageRepo;
	
	@Override
	public List<AboutClientEntity> loadAllClient() {
		// TODO Auto-generated method stub
		return aboutClient.findAll();
	}

	@Override
	public Optional<AboutClientEntity> getClient(String id) {
		// TODO Auto-generated method stub
		return aboutClient.findById(id);
	}

	@Override
	public AboutClientEntity saveClient(AboutClientEntity client) {
		// TODO Auto-generated method stub
		return aboutClient.save(client);
	}
	
	@Override
	public void delete(String id) {
		aboutClient.deleteById(id);
	}

	@Override
	public ImageEntity saveImage(ImageEntity img) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AboutImgEntity saveClientImg(AboutImgEntity img) {
		// TODO Auto-generated method stub
		return imageRepo.save(img);
	}



	@Override
	public Optional<AboutImgEntity> loadImageById(String id) {
		// TODO Auto-generated method stub
		return imageRepo.findById(id);
	}

	@Override
	public void deleteImg(String id) {
		// TODO Auto-generated method stub
		imageRepo.deleteById(id);
	}

	@Override
	public List<AboutImgEntity> aboutImageEntities() {
		// TODO Auto-generated method stub
		return imageRepo.findAll();
	}



	

}
