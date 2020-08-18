package School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class subjectDAO {
	
	
	//DAO �� ���̤ä� ó���� �� �ñ����� �ܰ��̴�. 
	
	//CRUD ���α׷� ���� 
	//�⺻���� ������ ó�� ������ �Է� create insert
	//��ȸ read or retrieve select ���� unpdate 
	//���� delete ����� ������ �����ͺ��̽� ���α׷�
	
	
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	
	
	private static final String USER = "javauser";
	
	private static final String PASSWD = "java1234";
	
	private static subjectDAO instance = null;
	
	public static subjectDAO getInstance() {
		if (instance == null) {
			instance =new subjectDAO();	
		}//�̰� �̱��� Singleton
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
	//�а� ���̺��� ��� ���ڵ带 ��ȯ�ϴ� �޼ҵ�
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
			
			//Result set �� ������� ��� ���� ������ SubjectVO ��ü�� ����
			while (rs.next()) {
				svo = new SubjectVO();
				svo.setNo(rs.getInt("no"));
				svo.setS_num(rs.getString("s_num"));
				svo.setS_name(rs.getString("s_name"));
				
//				ArrayList ��ü�� ���ҷ� �߰�
				

				list.add(svo);
			}
		} catch (SQLException se) {
			System.out.println("���� error = [ " + se +"]");
			se.printStackTrace();
		}catch (Exception e) {
			//������ ���� Exception!
			System.out.println("error = [ " + e+ "]");
		}finally {
			try {
				if (rs != null) {rs.close();
					
				}
				//if �Ȱɸ� ����
				if (pstmt != null) {pstmt.close();
					
				}
				if (con != null) {con.close();
					
				}
				//�Ȱɸ� ������
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
			System.out.println("���� error = [" + e + "]");
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
			System.out.println("DB���� ���� error = ["+e2+"]");
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
			System.out.println("���� error = [" + e + "]");
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
			System.out.println("DB���� ���� error = ["+e2+"]");
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
			System.out.println("���� error = [" + e + "]");
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
			System.out.println("DB���� ���� error = ["+e2+"]");
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
			
			//Result set �� ������� ��� ���� ������ SubjectVO ��ü�� ����
			while (rs.next()) {
				svo = new SubjectVO();
				svo.setNo(rs.getInt("no"));
				svo.setS_num(rs.getString("s_num"));
				svo.setS_name(rs.getString("s_name"));
				
//				ArrayList ��ü�� ���ҷ� �߰�
				

				list.add(svo);
			}
		}catch (SQLException se) {
			System.out.println("���� error = [ " + se +"]");
			se.printStackTrace();
		}catch (Exception e) {
			//������ ���� Exception!
			System.out.println("error = [ " + e+ "]");
		}finally {
			try {
				if (rs != null) {rs.close();
					
				}
				//if �Ȱɸ� ����
				if (pstmt != null) {pstmt.close();
					
				}
				if (con != null) {con.close();
					
				}
				//�Ȱɸ� ������
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
			
			//Result set �� ������� ��� ���� ������ SubjectVO ��ü�� ����
				
//				ArrayList ��ü�� ���ҷ� �߰�
				

		} catch (SQLException se) {
			System.out.println("���� error = [ " + se +"]");
			se.printStackTrace();
		}catch (Exception e) {
			//������ ���� Exception!
			System.out.println("error = [ " + e+ "]");
		}finally {
			try {
				if (rs != null) rs.close();
								//if �Ȱɸ� ����
				if (pstmt != null) pstmt.close();
							
				if (con != null) con.close();
				//�Ȱɸ� ������
				} catch (SQLException e2) {
				System.out.println("DB connecting error = [ "+e2+"]");
			}
		}
		return subjectNumber;
	}
}




