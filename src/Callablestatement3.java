
import java.sql.*;
import java.util.*;

import oracle.jdbc.OracleTypes;
public class Callablestatement3 {
	public static void main(String[] args) {
		
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionDB.makeConnection("javauser", "java1234");
			cstmt = con.prepareCall("{call books_select(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.executeQuery();
			
			rs=(ResultSet)cstmt.getObject(1);
			
			System.out.println("\n**** BOOK table data****");
			System.out.println("å����\t\t å����");
			while (rs.next()) {
				System.out.printf("%-28s\t %s\n",rs.getString("title"),
						rs.getInt("price"));
					
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("����Ŭ ���� ���� �� ���� ���࿡ ������ �߻��Ͽ����ϴ�.");
		}
		catch (Exception e) {
			System.out.println("ERROR(" + e.getMessage()+")");
			
		}finally {
			try {
				if(cstmt!=null) cstmt.close();
				if(con !=null) con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

}
