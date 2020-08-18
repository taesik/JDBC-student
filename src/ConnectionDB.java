import java.sql.*;

public class ConnectionDB {
	public static Connection makeConnection(String id, String password) {
		//11g : jdbc:oracle:thin:@127.0.0.1:1521:orcl 서버위치:포트번호
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Stacked completely");
			
			con = DriverManager.getConnection(url,id,password);
			System.out.println("Database Connected ");
			
			
			
			
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("cannot find driver");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			con = makeConnection("scott","tiger");

			
			
		Statement s = con.createStatement();
		StringBuffer sb = new StringBuffer();
		sb.append("select empno, ename, sal, hiredate from emp order by empno");

		ResultSet rs = s.executeQuery(sb.toString());
		
		while (rs.next()) {
			int empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			double sal =rs.getDouble("sal");
			String hiredate = rs.getString("hiredate");
			System.out.print("empno :" + empno + "ename : " + ename);
			System.out.println(" salary : " + sal + " Hiredate :" + hiredate);
			
			
			}
		
		}catch (SQLException e) {
			System.err.println("Query error \n " + e.getMessage());
		}finally {
		if (con !=null) 
			 con.close();
			
		}
		
		
		
		
	}
	
	private static void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException {
		
		
	}
	
	
}
