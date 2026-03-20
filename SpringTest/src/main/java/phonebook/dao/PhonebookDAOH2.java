package phonebook.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import phonebook.vo.PhonebookVO;


@Repository
public class PhonebookDAOH2 implements PhonebookDAO{
	@Autowired
	Connection conn;

	public PhonebookDAOH2() {
		System.out.println("dao:"+conn);
	}
	
	@Override
	public int save(PhonebookVO pb) {
		String sql="insert into phonebook values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pb.getId());
			ps.setString(2,pb.getName());
			ps.setString(3,pb.getHp());
			ps.setString(4,pb.getEmail());
			ps.setString(5,pb.getMemo());
			ps.setString(6,pb.getAddress());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public List<PhonebookVO> findAll() {
		System.out.println("dao:"+conn);
		String sql="select * from phonebook"; 
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<PhonebookVO> list=new ArrayList<PhonebookVO>();
			while(rs.next()) {
				/*
				PhonebookVO pb=new PhonebookVO(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("hp"), 
						rs.getString("email"), 
						rs.getString("memo")
						);
				*/
				// 위의 생성자를 builder를 이용하여 처리한다
				PhonebookVO pb = PhonebookVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.hp(rs.getString("hp"))
						.email(rs.getString("email"))
						.memo(rs.getString("memo"))
						.build();
				list.add(pb);				
			}
			rs.close();ps.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public PhonebookVO findById(int id) {
		String sql="select * from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				PhonebookVO pb=new PhonebookVO(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("hp"), 
						rs.getString("email"), 
						rs.getString("memo"),
						rs.getString("address")
						);
				rs.close();ps.close();
				return pb;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(PhonebookVO pb) {
		String sql="update phonebook set name=?, hp=?, email=?, address=?, memo=? where id=?";
		//update는 변경하지 않는 값은 null이므로 기존의 값으로 대체
		PhonebookVO oldpb=findById(pb.getId());
		if(pb.getName()==null) pb.setName(sql);
		if(pb.getHp()==null) pb.setHp(oldpb.getHp());
		if(pb.getEmail()==null) pb.setEmail(oldpb.getEmail());
		if(pb.getMemo()==null) pb.setMemo(oldpb.getMemo());
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,pb.getName());
			ps.setString(2,pb.getHp());
			ps.setString(3,pb.getEmail());
			ps.setString(4,pb.getMemo());
			ps.setString(5,pb.getAddress());
			ps.setInt(6, pb.getId());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;	
		}		
	}

	@Override
	public int delete(int id) {
		String sql="delete from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}


}
