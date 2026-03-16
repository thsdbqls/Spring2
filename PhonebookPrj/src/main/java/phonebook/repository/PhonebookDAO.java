package phonebook.repository;

import java.util.List;

import phonebook.model.PhonebookVO;

public interface PhonebookDAO {
public int save(PhonebookVO pb);
public List<PhonebookVO> findAll();
public PhonebookVO findById(int id);
public int update(PhonebookVO pb); //id가 존재하면 해당 아이디에 대해 나머지를 수정하세요.
public int delete(int id);
}
