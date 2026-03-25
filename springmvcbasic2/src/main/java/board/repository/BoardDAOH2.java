package board.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import board.model.Board;
import board.model.BoardTable;

@Repository(value = "boardDAOH2")
public class BoardDAOH2 implements BoardDAO {

	@Autowired
	DriverManagerDataSource ds;
	
	@Override
	public int save(BoardTable board) {
		StringBuffer sql= new StringBuffer();
		sql.append("INSERT INTO board (title, USERID, content, attachment, viewcnt, type, createdate)");		
		sql.append(" VALUES (?,?,?,?,0,'일반',CURRENT_TIMESTAMP)");
		
		try {
		PreparedStatement ps = ds.getConnection().prepareStatement(sql.toString());
		ps.setString(1,board.getTitle());
		ps.setInt(2, board.getUserid());
		ps.setString(3,board.getContent());
		ps.setString(4,board.getAttachment());
		int result = ps.executeUpdate();
		ps.close();
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			// 반드시 실행해야하는 코드를 넣어줘야 한다
			// 로그 기록
			return 0;
		}
		
	}

	@Override
	public List<BoardTable> findAll(int startnum, int endnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardTable findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardTable findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(BoardTable board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int viewcntup(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board viewBoard(int id, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
