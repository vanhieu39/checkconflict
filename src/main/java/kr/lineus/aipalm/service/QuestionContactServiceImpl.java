package kr.lineus.aipalm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lineus.aipalm.entity.QuestionContactEntity;
import kr.lineus.aipalm.repository.QuestionContactRepository;

@Service
public class QuestionContactServiceImpl implements QuestionContactService {

	@Autowired
	QuestionContactRepository contactRepos;

	@Override
	public List<QuestionContactEntity> loadAllContact() {
		// load All Contact
		return contactRepos.findAll();
	}

	@Override
	public Optional<QuestionContactEntity> getContact(String id) {
		// Update Contact by id
		return contactRepos.findById(id);
	}

	@Override
	public QuestionContactEntity saveContact(QuestionContactEntity contact) {
		// Insert Contact
		return contactRepos.save(contact);
	}

	@Override
	public void delete(String id) {
		contactRepos.deleteById(id);
		
	}
}
