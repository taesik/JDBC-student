package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import School.SubjectVO;

public class StudentDAO {
private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	
	
	private static final String USER = "javauser";
	
	private static final String PASSWD = "java1234";
	
	private static StudentDAO instance = null;
	
	public static StudentDAO getInstance() {
		if (instance == null) {
			instance =new StudentDAO();	
		}//이게 싱글톤 Singleton
		return instance;
	}//getInstance()
	private StudentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}//StudentDAO constructor 
	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(JDBC_URL,USER,PASSWD);
				return con;
	}//getConnection()
	public ArrayList<StudentVO> getStudentTotal(){
		StringBuffer sql = new StringBuffer();
		sql.append("select no, sd_num, sd_name, sd_id, sd_passwd, s_num, sd_birth, ");
		sql.append("sd_phone, sd_address, sd_email from student order by no");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO svo = null;
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			//Result set 의 결과에서 모든 행을 각ㄱ의 SubjectVO 객체에 저장
			while (rs.next()) {
				svo = new StudentVO();
				svo.setNo(rs.getInt("no"));
				svo.setSd_num(rs.getString("sd_num"));
				svo.setSd_name(rs.getString("sd_name"));
				svo.setSd_id(rs.getString("sd_id"));
				svo.setSd_passwd(rs.getString("sd_passwd"));
				svo.setS_num(rs.getString("s_num"));
				svo.setSd_birth(rs.getString("sd_birth"));
				svo.setSd_address(rs.getString("sd_address"));
				svo.setSd_email(rs.getString("sd_email"));
				
				
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
	public boolean studentUpdate (StudentVO svo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update student set sd_passwd = ? ,sd_birth= ? , sd_phone= ? ,");
		sql.append("sd_address= ?, sd_email= ?");
		sql.append("where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success =false;
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, svo.getSd_passwd());
			pstmt.setString(2, svo.getSd_birth());
			pstmt.setString(3, svo.getSd_phone());
			pstmt.setString(4, svo.getSd_address());
			pstmt.setString(5, svo.getSd_email());
			pstmt.setInt(6, svo.getNo());
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
		
		
		
	
	
	
	}
	public boolean studentInsert(StudentVO svo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into student(sd_num, sd_name, sd_id, sd_passwd, s_num, sd_birth, sd_phone, sd_address, sd_email ");
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success =false;
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, svo.getSd_num());
			pstmt.setString(2, svo.getSd_name());

			pstmt.setString(3, svo.getSd_id());
			pstmt.setString(4, svo.getSd_passwd());
			pstmt.setString(5, svo.getS_num());
			pstmt.setString(6, svo.getSd_birth());
			pstmt.setString(7, svo.getSd_phone());
			pstmt.setString(8, svo.getSd_address());
			pstmt.setString(9, svo.getSd_email());
			
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
	}//studentInsert(student svo)
	
	
	public boolean studentDelete(SubjectVO svo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from student ");
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
	}//studentdelete() end
}//StudentDAO end
