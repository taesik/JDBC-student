package exam_student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class StudentDAO {
	// �����ͺ��̽� ���� ���� ��� ����
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";// ���� url
	private static final String USER = "javauser";// �����
	private static final String PASSWD = "java1234";// ��й�ȣ

	private static StudentDAO instance = null;// �ڽ��� Ÿ������ ������ ����

	public static StudentDAO getInstance() {
		if (instance == null)// �ϳ��� ����Ǿ�� �ϹǷ� ������� �ٷ� instance ����
			instance = new StudentDAO();
		return instance;
	}

	private StudentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc ����̹�
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWD);// ����Ŭ ���� ����
		return con;
	}

	// ����Ʈ ��ȸ
	public ArrayList<StudentVO> getStudentTotal() {

		// ���� �߰��Ҽ� �ֱ� ������ StringBuffer
		StringBuffer sql = new StringBuffer();

		// innerjoin �̿��� �а������ ���
		sql.append("select st.no, sd_num, sd_name, sd_id, sd_passwd, st.s_num s_num, ");
		sql.append("su.s_name as s_name, sd_birth, sd_phone, sd_address, sd_email, sd_date ");
		sql.append("from student st inner join subject su on st.s_num = su.s_num order by no");

		// try�� �ȿ��� �ϸ� try �������� ���� �ֱ� ������ try�� �ۿ��� ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO svo = null;

		// ArayList �� ��� while (rs.next()) �� ���� ���� ���� ����
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();

		try {

			// try�� �ۿ��� �����ߴ� ��ü�� ����
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			// ���� ���� ������ ���� �ݺ�
			while (rs.next()) {
				svo = new StudentVO();

				// StudentVO�� set�� ResultSet�� get�� ��´ٴ� ��
				svo.setNo(rs.getInt("no"));
				svo.setSd_num(rs.getString("sd_num"));
				svo.setSd_name(rs.getString("sd_name"));
				svo.setSd_id(rs.getString("sd_id"));
				svo.setSd_passwd(rs.getString("sd_passwd"));
				svo.setS_num(rs.getString("s_num"));
				svo.setS_name(rs.getString("s_name"));
				svo.setSd_birth(rs.getDate("sd_birth"));
				svo.setSd_phone(rs.getString("sd_phone"));
				svo.setSd_address(rs.getString("sd_address"));
				svo.setSd_email(rs.getString("sd_email"));
				svo.setSd_date(rs.getDate("sd_date"));

				// �� �޾����� ArrayList ���ҿ� �߰�
				list.add(svo);
			}
		} catch (SQLException se) {// SQLException���� ���ð�� catch
			System.out.println("���� error = [" + se + "]");
			se.printStackTrace();
		} catch (Exception e) {// Exception���� ���ð�� catch
			System.out.println("error = [" + e + "]");
		} finally {// ����� ������� �����ϴ� finally
			try {
				// null�̸� ������ ���� ����, �׸��� null�̸� �̹� ��ü�� �����ٴ� ��
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {// close() ���� ���ܰ� �� �� �ִ�.
				System.out.println("DB ���� ���� error = [" + e + "]");
			}
		}
		return list;
	}

	// �л����� �߰�
	public boolean studentInsert(StudentVO svo) throws Exception {

		// ���� �߰��Ҽ� �ֱ� ������ StringBuffer
		StringBuffer sql = new StringBuffer();
		sql.append("insert into student (no, sd_num, sd_name, sd_id, sd_passwd, "
				+ "s_num, sd_birth, sd_phone, sd_address, sd_email) "
				+ "values (student_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		// try�� �ȿ��� �ϸ� try �������� ���� �ֱ� ������ try�� �ۿ��� ����
		Connection con = null;
		PreparedStatement pstmt = null;

		// boolean ���� �����ϴ� �޼ҵ��. ���� �߰��Ǿ����� true
		boolean success = false;

		try {
			// try�� �ۿ� ������ ��ü ����
			con = getConnection();
			// StringBuffer �� ���� sql ���� pstmt �����ڿ� ������. ���⼭ '?' �� �۵��� ���� �ؿ� ���� �ִ�.
			pstmt = con.prepareStatement(sql.toString());

			// StudentExample.create(dao) �޼ҵ忡�� ���� ���� pstmt�� set���� ����
			// ������ ������ �������� �����Ŵ�.
			pstmt.setString(1, svo.getSd_num());
			pstmt.setString(2, svo.getSd_name());
			pstmt.setString(3, svo.getSd_id());
			pstmt.setString(4, svo.getSd_passwd());
			pstmt.setString(5, svo.getS_num());
			pstmt.setDate(6, svo.getSd_birth());
			pstmt.setString(7, svo.getSd_phone());
			pstmt.setString(8, svo.getSd_address());
			pstmt.setString(9, svo.getSd_email());

			// �������� ������ �޼ҵ� �����ϸ� 1�� �������ش�. �׸��� true
			int i = pstmt.executeUpdate();
			if (i == 1)
				success = true;
		} catch (SQLException se) {// SQLException���� ���ð�� catch, false
			System.out.println("���� error = [" + se + "]");
			success = false;
		} catch (Exception e) {// Exception���� ���ð�� catch, false
			System.out.println("error = [" + e + "]");
			success = false;
		} finally {// ����� ���ο� ������� finally
			try {// close() ���� ���ܰ� �߻��� �� �ִ�.
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("DB ���� ���� error = [" + e + "]");
			}
		}
		return success;
	}

	// �л� ���� ����
	public boolean studentDelete(StudentVO svo) {

		// ���� �߰��Ҽ� �ֱ� ������ StringBuffer
		StringBuffer sql = new StringBuffer();
		sql.append("delete from student where no = ?");

		// try�� �ȿ��� �ϸ� try �������� ���� �ֱ� ������ try�� �ۿ��� ����
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success = false;

		try {

			// try�� �ۿ� ������ ��ü ����
			con = getConnection();
			// StringBuffer �� ���� sql ���� pstmt �����ڿ� ������. ���⼭ '?' �� �۵��� ���� �ؿ� ���� �ִ�.
			pstmt = con.prepareStatement(sql.toString());

			// StudentExample.delete(dao) �޼ҵ忡�� ���� ���� pstmt�� set���� ����
			// ������ ������ �������� �����Ŵ�.
			pstmt.setInt(1, svo.getNo());

			// ������ ������, �����ϸ� 1 ����
			int i = pstmt.executeUpdate();
			if (i == 1)
				success = true;
		} catch (SQLException se) {// SQLException���� ���ð�� catch, false
			System.out.println("���� error = [" + se + "]");
			success = false;
		} catch (Exception e) {// Exception���� ���ð�� catch, false
			System.out.println("error = [" + e + "]");
			success = false;
		} finally {// ����� ���ο� ������� finally
			try {// close() ���� ���ܰ� �߻��� �� �ִ�.
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {// SQLException���� ���ð�� catch
				System.out.println("DB ���� ���� error = [" + e + "]");
			}
		}
		return success;
	}

	// �л���ȣ �ڵ����� �޼ҵ� (��ϳ⵵(sysdate)+�а���ȣ+0001) ���� ������ ��ġ�ϴ� ���� ������ ++ �Ѵ�.
	public String studentNum(String s_num) {
		//ArrayList �� �ӽ÷� �й��� ��´�
		ArrayList<String> al = new ArrayList<String>();

		// ���� �߰��Ҽ� �ֱ� ������ StringBuffer
		StringBuffer sql = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String year = sdf.format(new java.util.Date());
		sql.append("select sd_num from student where substr(sd_num,1,2)=" + year + " and s_num= " + s_num);

		// try�� �ȿ��� �ϸ� try �������� ���� �ֱ� ������ try�� �ۿ��� ����
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		// �⺻��
		String studentNumber = "";
		try {

			// try�� �ۿ� ������ ��ü ����
			con = getConnection();
			stmt = con.createStatement();
			// StringBuffer �� ���� sql ���� stmt ������ ���� �޼ҵ忡 ������.
			rs = stmt.executeQuery(sql.toString());
			studentNumber = year + s_num + "0001";
			int stn = Integer.parseInt(studentNumber);// int �� ����ȯ
			while (rs.next())
				al.add(rs.getString("sd_num"));
			
			Collections.sort(al);
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i).equals(studentNumber)) {
					stn++;
				}
				studentNumber = stn + "";
				if (studentNumber.length() != 8) {
					while (studentNumber.length() != 8)
						studentNumber = "0" + studentNumber;
				}
			}

		} catch (SQLException se) {// SQLException���� ���ð�� catch
			System.out.println("���� error = [" + se + "]");
		} catch (Exception e) {// Exception���� ���ð�� catch
			System.out.println("error = [" + e + "]");
		} finally {// ����� ���ο� ������� finally
			try {// close() ���� ���ܰ� �߻��� �� �ִ�.
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {// SQLException���� ���ð�� catch
				System.out.println("DB ���� ���� error = [" + e + "]");
			}
		}
		return studentNumber;
	}

	public ArrayList<StudentVO> getStudentSearch(String searchObject, String searchWord) throws Exception {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();

		System.out.println("searchObject : " + searchObject);
		System.out.println("/ searchWord : " + searchWord);

		// ���� �߰��Ҽ� �ֱ� ������ StringBuffer
		StringBuffer sql = new StringBuffer();

		// innerjoin �̿��� �а������ ���
		sql.append("select st.no, sd_num, sd_name, sd_id, sd_passwd, st.s_num s_num, ");
		sql.append("su.s_name as s_name, sd_birth, sd_phone, sd_address, sd_email, sd_date ");
		sql.append("from student st inner join subject su on st.s_num = su.s_num ");

		// equalsIgnoreCase �� ������ ��ҹ��ڿ� ������� ��ġ�ϸ� true, ���� ���� �ѱ������� �˾ư����� �����
		if (searchObject.equalsIgnoreCase("�л���"))
			sql.append("where sd_name like ? ");
		else if (searchObject.equalsIgnoreCase("���̵�"))
			sql.append("where sd_id like ? ");
		else if (searchObject.equalsIgnoreCase("�����"))
			sql.append("where to_char(sd_date, 'YYYY-MM-DD') like ? ");

		sql.append("order by no");

		// try�� �ȿ��� �ϸ� try �������� ���� �ֱ� ������ try�� �ۿ��� ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO svo = null;

		try {

			// try�� �ۿ��� �����ߴ� ��ü�� ����
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchWord + "%");

			rs = pstmt.executeQuery();

			// ���� ���� ������ ���� �ݺ�
			while (rs.next()) {
				svo = new StudentVO();

				// StudentVO�� set�� ResultSet�� get�� ��´ٴ� ��
				svo.setNo(rs.getInt("no"));
				svo.setSd_num(rs.getString("sd_num"));
				svo.setSd_name(rs.getString("sd_name"));
				svo.setSd_id(rs.getString("sd_id"));
				svo.setSd_passwd(rs.getString("sd_passwd"));
				svo.setS_num(rs.getString("s_num"));
				svo.setS_name(rs.getString("s_name"));
				svo.setSd_birth(rs.getDate("sd_birth"));
				svo.setSd_phone(rs.getString("sd_phone"));
				svo.setSd_address(rs.getString("sd_address"));
				svo.setSd_email(rs.getString("sd_email"));
				svo.setSd_date(rs.getDate("sd_date"));

				// �� �޾����� ArrayList ���ҿ� �߰�
				list.add(svo);
			}
		} catch (SQLException se) {// SQLException���� ���ð�� catch
			System.out.println("���� error = [" + se + "]");
			se.printStackTrace();
		} catch (Exception e) {// Exception���� ���ð�� catch
			System.out.println("error = [" + e + "]");
		} finally {// ����� ������� �����ϴ� finally
			try {
				// null�̸� ������ ���� ����, �׸��� null�̸� �̹� ��ü�� �����ٴ� ��
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {// close() ���� ���ܰ� �� �� �ִ�.
				System.out.println("DB ���� ���� error = [" + e + "]");
			}
		}
		return list;
	}

	public boolean studentUpdate(HashMap<String, String> hm, String sd_num) {
		StringBuffer sql = new StringBuffer();
		sql.append("update student set ");
		// ���� ����
		boolean success = false;

		// StringBuffer �� �̿��� sql�� �߰�. ������ ���� ��ŭ while���� ����.
		Set<String> keys = hm.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			String key = itr.next();// hm �� key��
			String value = hm.get(key);// hm �� value ��

			if (key.equals("sd_birth")) {// sd_birth �� Date Ÿ�� �̹Ƿ� Ư���� ó�� �ؾ���
				String year, month, date;
				if (value.substring(0, 1).equals("0"))// yymmdd�� yy�� 2000��� ���� 1900���� ������
					year = "20" + value.substring(0, 2);
				else
					year = "19" + value.substring(0, 2);
				month = value.substring(2, 4);// yymmdd -> mm
				date = value.substring(4);// yymmdd -> dd
				value = year + "-" + month + "-" + date;// yyyy-mm-dd
			}

			sql.append(key + " = '" + value + "' ");// <��> sd_id = 'SWAK'

			if (itr.hasNext())// �������� ������ , ���
				sql.append(", ");
		}
		sql.append("where sd_num = " + sd_num);// ������ where ����
//		System.out.println(sql.toString());//������ �� �ۼ� �Ǿ����� Ȯ�ο�
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			int i = stmt.executeUpdate(sql.toString());
			if (i > 0)
				success = true;
		} catch (SQLException se) {// SQLException���� ���ð�� catch, false
			System.out.println("���� error = [" + se + "]");
			success = false;
		} catch (Exception e) {// Exception���� ���ð�� catch, false
			System.out.println("error = [" + e + "]");
			success = false;
		} finally {// ����� ���ο� ������� finally
			try {// close() ���� ���ܰ� �߻��� �� �ִ�.
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("DB ���� ���� error = [" + e + "]");
			}
		}
		return success;

	}

}


