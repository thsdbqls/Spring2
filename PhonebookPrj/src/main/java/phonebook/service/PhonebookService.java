package phonebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phonebook.model.PhonebookVO;
import phonebook.repository.PhonebookDAO;

@Service
public class PhonebookService {
	@Autowired
	PhonebookDAO dao;
	
	public PhonebookService() {
		
	}
	public List<PhonebookVO> getPhonebooks() {
		return dao.findAll();
	}
	
	public boolean insert(PhonebookVO pb) {
		//PhonebookVO pb=new PhonebookVO(id, name, hp, email, memo, pic);
		int result=dao.save(pb);
		if(result>0) return true;
		else return false;
	}
	public PhonebookVO getPhonebook(int id) {
		return dao.findById(id);
	}

	public boolean update(PhonebookVO pb) {
		return dao.update(pb)>0 ? true : false;
	}
	
	public boolean delete(int id) {
		 return dao.delete(id) >0 ? true : false;
		
	}


}
