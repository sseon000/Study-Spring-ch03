package com.fastcampus.ch3;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {
	@Autowired
	DataSource ds;
	
	@Test
	public void insertUserTest() throws Exception {
		deleteUser(65);
		
		User user = new User(0101, "test123", "test123", "테스터123", "남자", new Date(), "01012345678", "승인", "정규직");
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt == 1);
	}
	
	@Test
	public void selectUserTest() throws Exception {
		User user = selectUser(65);
		assertTrue(user.getId().equals("test123"));
	}
	
	public User selectUser(int userNum) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "select * from hankjae.intechmembers where num = ?"; 
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격 대응, 성능향상
		pstmt.setInt(1, userNum);
		
		ResultSet rs = pstmt.executeQuery(); // select
		
		if(rs.next()) {
			User user = new User();
			user.setNum(rs.getInt(1));
			user.setId(rs.getString(2));
			user.setPw(rs.getString(3));
			user.setName(rs.getString(4));
			user.setGender(rs.getString(5));
			user.setBirthday(new Date(rs.getDate(6).getTime()));
			user.setPh(rs.getString(7));
			user.setAcept(rs.getString(8));
			user.setType(rs.getString(9));
			return user;
		}
		
		return null;
	}
	
	private void deleteUser(int userNum) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "delete from hankjae.intechmembers where num = ?"; 
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격 대응, 성능향상
		pstmt.setInt(1, userNum);
		
		pstmt.executeUpdate(); // insert, delete, update
	}
	
	// 사용자 정보를 intechmembers에 저장하는 메서드
	public int insertUser(User user) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "insert into hankjae.intechmembers "
				   + "     values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격 대응, 성능향상
		pstmt.setInt(1, user.getNum());
		pstmt.setString(2, user.getId());
		pstmt.setString(3, user.getPw());
		pstmt.setString(4, user.getName());
		pstmt.setString(5, user.getGender());
		pstmt.setDate(6, new java.sql.Date(user.getBirthday().getDate()));
		pstmt.setString(7, user.getPh());
		pstmt.setString(8, user.getAcept());
		pstmt.setString(9, user.getType());
		
		int rowCnt = pstmt.executeUpdate(); // insert, delete, update
		
		return rowCnt;
	}
	
	@Test
	public void springJdbcConnectionTest() throws Exception{
//	      ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//	      DataSource ds = ac.getBean(DataSource.class);

	      Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

	      System.out.println("conn = " + conn);
	      assertTrue(conn!=null); // 괄호 안의 조건식이 true면, 테스트 성공, 아니면 실패
	}

}
