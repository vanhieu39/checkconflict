package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import kr.lineus.aipalm.entity.QuestionContactEntity;

public interface QuestionContactService {

	List<QuestionContactEntity> loadAllContact();
	Optional <QuestionContactEntity> getContact( String id);
	QuestionContactEntity saveContact(QuestionContactEntity contact);
	
	void delete(String id);
}
