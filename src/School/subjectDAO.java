package School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class subjectDAO {
	
	
	//DAO 는 데이ㅓㅌ 처리으 ㅣ 궁극적인 단계이다. 
	
	//CRUD 프로그램 구현 
	//기본적인 데이터 처릭 가능인 입력 create insert
	//조회 read or retrieve select 수정 unpdate 
	//삭제 delete 기능을 구현한 데이터베이스 프로그램
	
	
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	
	
	private static final String USER = "javauser";
	
	private static final String PASSWD = "java1234";
	
	private static subjectDAO instance = null;
	
	public static subjectDAO getInstance() {
		if (instance == null) {
			instance =new subjectDAO();	
		}//이게 싱글톤 Singleton
		return instance;
	}


	private subjectDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(JDBC_URL,USER,PASSWD);
				return con;
	}
	//학과 테이블에서 모든 레코드를 반환하는 메소드
	public ArrayList<SubjectVO> getSubjectTotal(){
		StringBuffer sql = new StringBuffer();
		sql.append("select no, s_num, s_name from subject order by no");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubjectVO svo = null;
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			//Result set 의 결과에서 모든 행을 각ㄱ의 SubjectVO 객체에 저장
			while (rs.next()) {
				svo = new SubjectVO();
				svo.setNo(rs.getInt("no"));
				svo.setS_num(rs.getString("s_num"));
				svo.setS_name(rs.getString("s_name"));
				
//				ArrayList 객체에 원소로 추가
				

				list.add(svo);
			}
		} catch (SQLException se) {
			System.out.println("쿼리 error = [ " + se +"]");
			se.printStackTrace();
		}catch (Exception e) {
			//예외의 상위 Exception!
			System.out.println("error = [ " + e+ "]");
		}finally {
			try {
				if (rs != null) {rs.close();
					
				}
				//if 안걸면 오류
				if (pstmt != null) {pstmt.close();
					
				}
				if (con != null) {con.close();
					
				}
				//안걸면 오류남
			} catch (SQLException e2) {
				System.out.println("DB connecting error = [ "+e2+"]");
		}
	}
		
		return list;
	}//getSubjectTotal end
	
	public boolean subjectInsert(SubjectVO svo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into subject(no,s_num,s_name) ");
		sql.append("values(subject_seq.nextval, ?,?)");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success =false;
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, svo.getS_num());
			pstmt.setString(2, svo.getS_name());
			
			int i = pstmt.executeUpdate();
			if (i==1) {
				success = true;
			}
		}catch (SQLException e) {
			System.out.println("쿼리 error = [" + e + "]");
			success = false;
		}catch (Exception e) {
			System.out.println("error = [" +e + "]");
			success = false;
		}finally {
			try {
				if (pstmt!= null) {
				pstmt.close();
				if (con != null) {
					con.close();
					
				}
			}
				
		} catch (SQLException e2) {
			System.out.println("DB연동 해제 error = ["+e2+"]");
			}
			
			
		}
		return success;
	}//subjectInsert(subject svo)
	
	public boolean subjectUpdate(SubjectVO svo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update subject set s_num=?,s_name=? ");
		sql.append("where no = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success = false;
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, svo.getS_num());
			pstmt.setString(2, svo.getS_name());
			pstmt.setInt(3, svo.getNo());
			
			int i = pstmt.executeUpdate();
			if (i==1) success = true;
			
		}catch (SQLException e) {
			System.out.println("쿼리 error = [" + e + "]");
			success = false;
		}catch (Exception e) {
			System.out.println("error = [" +e + "]");
			success = false;
		}finally {
			try {
				if (pstmt!= null) {
				pstmt.close();
				if (con != null) {
					con.close();
					
				}
			}
				
		} catch (SQLException e2) {
			System.out.println("DB연동 해제 error = ["+e2+"]");
			}
			
			
		}
		return success;
			
	}//subjectUpdate()SubjectVO
	
	public boolean subjectDelete(SubjectVO svo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from subject ");
		sql.append("where no = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success = false;
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, svo.getNo());
			
			int i = pstmt.executeUpdate();
			if (i==1) success = true;
			
		}catch (SQLException e) {
			System.out.println("쿼리 error = [" + e + "]");
			success = false;
		}catch (Exception e) {
			System.out.println("error = [" +e + "]");
			success = false;
		}finally {
			try {
				if (pstmt!= null) {
				pstmt.close();
				if (con != null) {
					con.close();
					
				}
			}
				
		} catch (SQLException e2) {
			System.out.println("DB연동 해제 error = ["+e2+"]");
			}
			
			
		}
		return success;
	}//subjectdelete() end
	
	public ArrayList<SubjectVO> getSubjectSearch(String s_name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select no, s_num, s_name from subject where s_name like ? order by no");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubjectVO svo = null;
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+s_name+"%");
			rs = pstmt.executeQuery();
			
			//Result set 의 결과에서 모든 행을 각ㄱ의 SubjectVO 객체에 저장
			while (rs.next()) {
				svo = new SubjectVO();
				svo.setNo(rs.getInt("no"));
				svo.setS_num(rs.getString("s_num"));
				svo.setS_name(rs.getString("s_name"));
				
//				ArrayList 객체에 원소로 추가
				

				list.add(svo);
			}
		}catch (SQLException se) {
			System.out.println("쿼리 error = [ " + se +"]");
			se.printStackTrace();
		}catch (Exception e) {
			//예외의 상위 Exception!
			System.out.println("error = [ " + e+ "]");
		}finally {
			try {
				if (rs != null) {rs.close();
					
				}
				//if 안걸면 오류
				if (pstmt != null) {pstmt.close();
					
				}
				if (con != null) {con.close();
					
				}
				//안걸면 오류남
			} catch (SQLException e2) {
				System.out.println("DB connecting error = [ "+e2+"]");
			}
		}
		return list;
	}//getSubjectSearch()
	public String getSubjectNum() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(Lpad(max(to_number(LTRIM(S_NUM,'0')))+1,2,'0'),'01') AS SUBJECTNUM FROM SUBJECT");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String subjectNumber = "";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				subjectNumber = rs.getString("subjectNum");
			}
			
			//Result set 의 결과에서 모든 행을 각ㄱ의 SubjectVO 객체에 저장
				
//				ArrayList 객체에 원소로 추가
				

		} catch (SQLException se) {
			System.out.println("쿼리 error = [ " + se +"]");
			se.printStackTrace();
		}catch (Exception e) {
			//예외의 상위 Exception!
			System.out.println("error = [ " + e+ "]");
		}finally {
			try {
				if (rs != null) rs.close();
								//if 안걸면 오류
				if (pstmt != null) pstmt.close();
							
				if (con != null) con.close();
				//안걸면 오류남
				} catch (SQLException e2) {
				System.out.println("DB connecting error = [ "+e2+"]");
			}
		}
		return subjectNumber;
	}
}




