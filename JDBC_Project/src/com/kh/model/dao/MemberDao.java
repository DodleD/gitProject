package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

///DAO(Date Access Object) : db에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과를 반환한다 (JDBC)
public class MemberDao {
	
	/**
	 * 사용자가 입력한 정보들을 db에 추가시켜주는 메소드
	 * @param m : 사용자가 입력한 값들이 담겨있는 member 객체
	 * @return : insert문 실행 후 처리된 행의 수
	 */
	
	public int insertMember(Member m) {
		//insert문 -> 반환값 처리된 행의 수(int) -> 트랜잭션 처리해줘야함
		
		//필요한 변수들 먼저 세팅
		int result = 0; //처리된 결과 (행의 수)를 받아줄 변수
		Connection conn = null; //연결된 db정보를 담는 객체
		PreparedStatement stmt = null; //완성된 sql문 전달해서 곧 바로 실행 후 결과를 받는 객체
		
		//실행할 sql문
//		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL," +
//		"'"+m.getUserId()+"'," +
//		"'"+m.getUserPwd()+"'," +
//		"'"+m.getUserName()+"'," +
//		"'"+m.getGender()+"'," +
//			 m.getAge() +"," +
//		"'"+m.getEmail()+"'," +
//		"'"+m.getPhone()+"'," +
//		"'"+m.getAddress()+"'," +
//		"'"+m.getHobby()+"'," + "SYSDATE)"; //거지같은 방법
		
		String sql = "INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2) Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			//3) Statement객체 생성
			stmt = conn.prepareStatement(sql); //prepare은 Statement의 자식이다. 그래서 사용가능
			//아직 미완성임, 표를 전부 채워줘야한다.
			stmt.setString(1, m.getUserId());
			stmt.setString(2, m.getUserPwd());
			stmt.setString(3, m.getUserName());
			stmt.setString(4, m.getGender());
			stmt.setInt(5, m.getAge());
			stmt.setString(6, m.getEmail());
			stmt.setString(7, m.getPhone());
			stmt.setString(8, m.getAddress());
			stmt.setString(9, m.getHobby());
			
			//4,5) sql문 전달하면서 실행 후 값 받아오기
			
			result = stmt.executeUpdate();
			
			//6) 커밋해주기
			if(result>0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//7다 쓴 jdbc객체 반환
			stmt.close();
			conn.close();
			
		}
		
		return result;
	}
	
	public ArrayList<Member> selectList() {
		// select문(여러행 조회) -> ResultSet객체 -> ArrayList<Member>에 담기
		
		//필요한 변수를 세팅
		ArrayList<Member> list = new ArrayList<Member>(); //비어있는 상태
		
		Connection conn =null;
		Statement stmt = null;
		ResultSet rset = null; //select문 실행시 조회된 결과값들이 최초로 담기는 공간
		
		//실행할 sql
		String spl = "SELECT * FROM MEMBER";
		
		//1) JDBC Driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2) Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			//3) Statement 객체생성
			stmt = conn.createStatement();
			
			
			//4, 5)sql전송 및 결과받기
			rset = stmt.executeQuery(spl);
			
			//6 Resultset으로부터 데이터를 하나씩 꺼내서 담아준다
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("userno"));
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userPwd"));
				m.setUserName(rset.getString("userName"));
				m.setGender(rset.getString("Gender"));
				m.setAge(rset.getInt("Age"));
				m.setEmail(rset.getString("Email"));
				m.setAddress(rset.getString("Address"));
				m.setHobby(rset.getString("Hobby"));
				m.setEnrollDate(rset.getDate("enrollDate"));
				//현재 참조하고 있는 형에 대한 모든 컬럼에 데이터들을 한 Member객체에 담기
				
				list.add(m);
			}
			//반복문이 끝난 시점
			//조회된 데이터가 없었다면 리스트는 비어있다.
			//조호된 데이터가 있다면 전부 list에 담겨있을 것이다.
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			rset.close();
			stmt.close();
			conn.close();
		}

		return list;
	}
	
	public int updateMember(Member m) {
		//update -> 처리된 행의 수
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		/*
		 * update member
		 * set userpwd = 'xx', email = 'xx', phone = 'xx', address = 'xx'
		 * where userid = 'xxx'; <- 입력받은 아이디만
		 */
		String sql = "UPDATE MEMBER " + //띄어쓰기 조심(해야함)
					 "SET USERPWD = ?," +
					 "SET EMAIL = ?," +
					 "SET PHONE = ?," +
					 "SET ADDRESS = ?" +
					 "WHERE USERID = ?";
		
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2) Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			//3) 값 넣어주기
			stmt = conn.prepareStatement(sql); //미완성 sql 완성해주기
			stmt.setString(1, m.getUserPwd());
			stmt.setString(1, m.getEmail());
			stmt.setString(1, m.getPhone());
			stmt.setString(1, m.getAddress());
			stmt.setString(1, m.getUserId());
			
			result = stmt.executeUpdate();
			
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		
		return result;

	}
	
	public int deleteMember(String userId) {
		//delete문 -> 처리된 행의 수
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		//DELETE FROM MEMBER WHERE USERID = ?
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ? ";
		//String sql = "DELETE FROM MEMBER WHERE USERID = " + "'"+ userId +"'" ;
		//1) JDBC Driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2) Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.prepareStatement(sql); //미완성 sql 완성해주기
			
			stmt.setString(1, m.getUserId);
			//stmt.createStatement();
			
			
			result = stmt.executeUpdate();
			
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		
		
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		
		return result;
		
	}
	
	
}
