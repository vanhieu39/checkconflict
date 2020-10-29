package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.HomeClientImgEntity;
import kr.lineus.aipalm.repository.HomeClientImgRepository;

@Service
public class HomeClientImgServiceImpl implements HomeClientImgService{

	@Autowired
	HomeClientImgRepository homeClientImgRepos;
	
	@Override
	public List<HomeClientImgEntity> loadAllHomeClientImg() {
		// TODO Auto-generated method stub
		return homeClientImgRepos.findAll();
	}

	@Override
	public Optional<HomeClientImgEntity> getHomeClientImg(String id) {
		// TODO Auto-generated method stub
		return homeClientImgRepos.findById(id);
	}

	@Override
	public HomeClientImgEntity saveHomeClientImg(HomeClientImgEntity homeClientImg) {
		// TODO Auto-generated method stub
		return homeClientImgRepos.save(homeClientImg);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		homeClientImgRepos.deleteById(id);
	}

}
