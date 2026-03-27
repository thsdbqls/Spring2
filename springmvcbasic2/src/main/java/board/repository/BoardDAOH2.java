package board.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import board.model.Board;
import board.model.BoardTable;
import lombok.Getter;
import member.model.User;

@Repository(value = "boardDAOH2")
@Getter
public class BoardDAOH2 implements BoardDAO{
	
	@Autowired
	private DriverManagerDataSource ds;
	
	@Override
	public int save(BoardTable board) {
		StringBuffer sql=new StringBuffer();
		sql.append("INSERT INTO board ");
		sql.append(" (title, USERID, content, attachment,");
		sql.append(" viewcnt, type, createdate)");
		sql.append(" VALUES (?,?,?,?,0,'일반',CURRENT_TIMESTAMP)");
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql.toString());
		ps.setString(1,board.getTitle());
		ps.setInt(2, board.getUserid());
		ps.setString(3,board.getContent());
		ps.setString(4,board.getAttachment());
		int result=ps.executeUpdate();
		ps.close();
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			
			//반드시 실행해야하는 코드 작성(로그기록)
		}
	}

	@Override
	public List<Board> findAll(int startnum, int endnum) {
		/*
		String sql="select rownum rn2,* from"
				+ " (select rownum rn, b.id b_id, b.title b_title,  b.content b_content,  b.attachment b_attachment,  b.viewcnt b_viewcnt,  b.type b_type,  b.createdate b_createdate,"
				+ " u.id u_id,  u.username u_username,  u.email u_email,  u.hp u_hp,  u.nickname u_nickname"
				+ " from board b"
				+ " join users u on b.userid = u.id order by rn desc) t "
				+ " where rn2>=? and rn2<=?";
		*/
		
		String sql="SELECT *"
				+ " FROM ("
				+ " SELECT ROWNUM rn2, a.*"
				+ " FROM ("
				+ " SELECT "
				+ " b.id b_id,"
				+ " b.title b_title,"
				+ " b.content b_content,"
				+ " b.attachment b_attachment,"
				+ " b.viewcnt b_viewcnt,"
				+ " b.type b_type,"
				+ " b.createdate b_createdate,"
				+ " u.id u_id,"
				+ " u.username u_username,"
				+ " u.email u_email,"
				+ " u.hp u_hp,"
				+ " u.nickname u_nickname"
				+ " FROM board b"
				+ " JOIN users u ON b.userid = u.id"
				+ " ORDER BY b.id DESC "
				+ " ) a"
				+ " WHERE ROWNUM <= ? "
				+ " )"
				+ " WHERE rn2 >= ?";
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			//ps.setInt(1,startnum);
			//ps.setInt(2,endnum);
			ps.setInt(1,endnum);
			ps.setInt(2,startnum);
			ResultSet rs=ps.executeQuery();
			List<Board> list=new ArrayList<Board>();
			while(rs.next()) {
				Board b = new Board();
			    b.setId(rs.getInt("b_id"));
			    b.setTitle(rs.getString("b_title"));
			    //자바객체에서는 userid대신 User객체를 사용하여 id를 얻을 수 있다.
			    //b.setUserid(rs.getInt("userid")); 
			    b.setContent(rs.getString("b_content"));
			    b.setAttachment(rs.getString("b_attachment"));
			    b.setViewcnt(rs.getInt("b_viewcnt"));
			    b.setType(rs.getString("b_type"));
			    b.setCreatedate(rs.getTimestamp("b_createdate"));

			    //User 객체 생성
			    User u = new User();
			    u.setId(rs.getInt("u_id"));
			    u.setUsername(rs.getString("u_username"));
			    u.setEmail(rs.getString("u_email"));
			    u.setHp(rs.getString("u_hp"));
			    u.setNickname(rs.getString("u_nickname"));
			    
			    b.setUser(u);
				
			    list.add(b);
			}
			rs.close();
			ps.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}
	
	/*
	@Override
	public List<Board> findAll(int startnum, int endnum) {
		String sql="select b.id b_id, b.title b_title,  b.content b_content,  b.attachment b_attachment,  b.viewcnt b_viewcnt,  b.type b_type,  b.createdate b_createdate,"
				+ " u.id u_id,  u.username u_username,  u.email u_email,  u.hp u_hp,  u.nickname u_nickname"
				+ " from board b"
				+ " join users u on b.userid = u.id";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<Board> list=new ArrayList<Board>();
			while(rs.next()) {
				Board b = new Board();
			    b.setId(rs.getInt("b_id"));
			    b.setTitle(rs.getString("b_title"));
			    //자바객체에서는 userid대신 User객체를 사용하여 id를 얻을 수 있다.
			    //b.setUserid(rs.getInt("userid")); 
			    b.setContent(rs.getString("b_content"));
			    b.setAttachment(rs.getString("b_attachment"));
			    b.setViewcnt(rs.getInt("b_viewcnt"));
			    b.setType(rs.getString("b_type"));
			    b.setCreatedate(rs.getTimestamp("b_createdate"));

			    //User 객체 생성
			    User u = new User();
			    u.setId(rs.getInt("u_id"));
			    u.setUsername(rs.getString("u_username"));
			    u.setEmail(rs.getString("u_email"));
			    u.setHp(rs.getString("u_hp"));
			    u.setNickname(rs.getString("u_nickname"));
			    
			    b.setUser(u);
				
			    list.add(b);
			}
			rs.close();
			ps.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}
	*/
	@Override
	public BoardTable findById(int id) {
		String sql="select * from board where id=?";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			BoardTable b =null;
			if(rs.next()) {
				b = new BoardTable();
			    b.setId(rs.getInt("id"));
			    b.setTitle(rs.getString("title"));
			    b.setUserid(rs.getInt("userid")); 
			    b.setContent(rs.getString("content"));
			    b.setAttachment(rs.getString("attachment"));
			    b.setViewcnt(rs.getInt("viewcnt"));
			    b.setType(rs.getString("type"));
			    b.setCreatedate(rs.getTimestamp("createdate"));
			    }
			rs.close();
			ps.close();
			return b;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}

	@Override
	public BoardTable findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(BoardTable board) {
		
		String sql="update board set title=?, content=?, attachment=? where id=?";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ps.setString(1,board.getTitle() );
			ps.setString(2,board.getContent() );
			ps.setString(3,board.getAttachment() );
			ps.setInt(4, board.getId());
			int result=ps.executeUpdate();			
			ps.close();
			return result;
		}catch (Exception e) {
			return 0;
		}finally {
			
		}
	}

	@Override
	public int delete(int id) {
		String sql="delete from board where id=?";
		
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();			
			ps.close();
			return result;
		}catch (Exception e) {
			return 0;
		}finally {
			
		}
	}

	@Override
	public int count() {
		String sql="select count(*) cnt from board";
		int totalCount=0;
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) totalCount=rs.getInt("cnt");
			rs.close();ps.close();
			return totalCount;
		}catch (Exception e) {
			return totalCount;
		}finally {
			
		}
	}

	@Override
	public int viewcntup(int id) {
		String sql="update board set viewcnt=viewcnt+1 where id=?";
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql);
		ps.setInt(1, id);
		int result=ps.executeUpdate();
		ps.close();
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			
			//반드시 실행해야하는 코드 작성(로그기록)
		}
	}

	@Override
	public Board viewBoardById(int id, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board viewBoard(int id, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

}