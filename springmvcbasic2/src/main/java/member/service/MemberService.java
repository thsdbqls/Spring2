package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import member.model.User;

@Service
public class MemberService {

	@Autowired
	@Qualifier(value = "userDAOH2")
	private member.repository.UserDAOH2 userDAO;

	public boolean register(User user) {
		return userDAO.save(user)>0 ? true : false;
		
	}
}
