package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.AboutDetailEntity;
import kr.lineus.aipalm.entity.ImageEntity;
import kr.lineus.aipalm.repository.AboutClientRepository;
import kr.lineus.aipalm.repository.AboutDetailRepository;

@Service
@Qualifier("detailService")
public class AboutDetailServiceImpl implements AboutDetailService {

	@Autowired
	AboutDetailRepository aboutDetail;
	
	@Override
	public List<AboutDetailEntity> loadDetail() {
		// TODO Auto-generated method stub
		return aboutDetail.findAll();
	}

	@Override
	public Optional<AboutDetailEntity> getDetail(String id) {
		// TODO Auto-generated method stub
		return aboutDetail.findById(id);
	}

	@Override
	public AboutDetailEntity saveDetail(AboutDetailEntity client) {
		// TODO Auto-generated method stub
		return aboutDetail.save(client);
	}
	
	@Override
	public void delete(String id) {
		aboutDetail.deleteById(id);
	}

	@Override
	public ImageEntity saveImage(ImageEntity img) {
		// TODO Auto-generated method stub
		return null;
	}

}
