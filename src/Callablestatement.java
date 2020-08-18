

import java.sql.*;
import java.util.*;

public class Callablestatement {
	public static void main(String[] args) {
		int book_id;
		Scanner input = null;
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			input = new Scanner(System.in);
			System.out.print("책번호 입력: ");
			book_id = input.nextInt();
			
			con = ConnectionDB.makeConnection("javauser","java1234");
			cstmt = con.prepareCall("{call books_proc(?,?)}");
			cstmt.setInt(1,book_id);
			cstmt.registerOutParameter(2,Types.VARCHAR);
			cstmt.executeQuery();
			
			System.out.println("\n****BOOKS 테이블 검색 데이터 ****");
			System.out.printf("책제목 : %s", cstmt.getString(2));
			
		}
		catch (InputMismatchException i) {
			System.out.println("입력값이 잘못되었습니다.");
			i.printStackTrace();
			
		}
		
		
		catch (SQLException s) {
			System.out.println("오라클 서버 연동후 쿼리 실행에 문제가 발생하였습니다.");
			
		}
		catch (Exception e) {
			System.out.println("ERROR("+e.getMessage()+")");
		}
		finally {
			try {
				if (input != null) 	input.close();
				if (cstmt != null)  cstmt.close();
				if (con != null)	con.close();
			} catch (Exception e2) {
				System.out.println("연결해제 오류");
				e2.printStackTrace();
			}
		}
		
	}
}
