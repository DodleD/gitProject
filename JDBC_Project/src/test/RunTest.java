package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunTest {
	/*
	 * JDBC를 사용하기 위해서는 자바 프로젝트에 JDBC드라이버를 추가해줘야한다.
	 * (프로젝트별로 해줘야함)
	 * 
	 * JDBC용 객체 
	 * - Connection : DB의 연결정보를 담고있는 객체
	 * - [prepared]statement : 연결된 DB에 sql문을 전달해서 실행하고 결과를 받아내는 객체
	 * - resultSet : SELECET문 실행 후 조회된 결과물들이 담겨있는 객체
	 * 
	 * *JDBC 과정(순서중요)
	 * 1) JDBC 드라이버 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
	 * 2) Connection 생성 : 연결하고자하는 db정보를 입력해서 해당 db와 연결할 수 있는 객체 생성
	 * 3) Statement객체 생성 : Connection객체를 이용해서 생성(sql문 실행 및 결과를 받는 객체)
	 * 4) sql문 전달하면서 실행 : Statement객체를 이용해서 sql문 실행
	 * 5) 결과받기 : SELECT문 실행(6-1) -> ResultSet객체(조회된 결과를 담아준다), 
	 * DML(INSERT, UPDATE, DELETE) 문 실행 (6-2)-> int로 담으면 됨
	 * 
	 * 6-1) ResultSet객체에 담겨있는 데이터들을 하나씩 추출해서 차근차근 옮겨담기[ArrayList] 가변배열
	 * 6-2) 트랜잭션 처리(성공했다면 COMMIT, 실패했다면 ROLLBACK)
	 * 
	 * 7) 다 사용한 JDBC용 객체를 반드시 자원 반납 (close) -> 생성된 역순으로 하면 됨
	 */

	public static void main(String[] args) {
		// 1. 각자의 pc(localhost)에 jdbc 계정 연결 후 test테이블에 insert해보자
		// insert -> 처리된 행의 수 반환(int) -> 다음으로 트랜잭션 처리 해줘야함
		
		//필요한 변수 세팅하기
		/*
		int result = 0; //결과(처리된 행 수)를 받아줄 변수
		Connection conn = null; //DB의 연결정보를 보관할 객체
		Statement stmt = null; //sql문을 전달해서 실행 후 결과를 받는 객체
		
		
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("번호 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.println("이름 : ");
		String name = sc.next();
		sc.nextLine();
		
		//실행할 sql문(완전한 상태로 만들어두기)
		String sql = "INSERT INTO TEST VALUES("+num+", '"+ name +"', SYSDATE)";
		
		try {
			//1) JDBC 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); //ojdbc6.jar파일을 추가하지 않으면 오류가 나옴, 또는 오타
			System.out.println("OracleDriver 등록성공");
			
			//2) Connection생성(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			//3) Statement 생성
			stmt = conn.createStatement();
			
			//4,5) sql문 전달하면서 실행 후 결과받기(처리된 행 수)
			result = stmt.executeUpdate(sql);
			//내가 실행할 sql문이 dml(insert, update, delete)문 일 때 사용 : int
			//내가 실행할 sql문이 select일 때 stmt.executeQuery(select문) : ResultSet
//			stmt.executeQuery(sql);
			
			
			//6) 트랜잭션 처리
			if(result > 0) { //요청 sql문 성공
				conn.commit();
			} else { //요청 sql문 실패
				conn.rollback();
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			//7) 다쓴 jdbc용 객체자원 반납, 생성에 역순으로
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(result > 0) { //요청 sql문 성공
			System.out.println("데이터 삽입 성공");
		} else { //요청 sql문 실패
			System.out.println("데이터 삽입 실패패패");
		}
		*/
		
		// 2. 내 pc db상에 jdbc계정에 TEST테이블에 모든 데이터 조회해보기
		// select 문 -> 결과 ResultSet(조회된 데이터가 담겨있다) -> ResultSet으로부터 데이터 추출
		
		//필요한 변수들 세팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		//실행할 sql문 작성
		String sql = "SELECT * FROM TEST";
		
		
		try {
			//1) JDBC 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver 등록성공");
			
			//2) Connection생성(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			//3) Statement 생성
			stmt = conn.createStatement();
			
			//4,5) SQL문 전달해서 실행 후 결과 받기 (ResultSet)
			rset = stmt.executeQuery(sql);
			
			// rset.next() -> rset의 다음행이 들어있는지 없는지 확인 후 결과반환 + 한 행을 내려준다
			
			while(rset.next()) {
				//현재 참조하는 rset으로부터 어떤 컬럼에 해당하는 값을 어떤 타입으로 추출할 건지 제시해줘야한다.
				//db의 컬럼명 제시(대소문자 구분하지 않는다)
				int tno = rset.getInt("TNO");
				String tname = rset.getString("TNAME");
				Date tdate = rset.getDate("TDATE");
				
				System.out.println(tno + ", " + tname + ", " + tdate);
			}
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		

	}

}
