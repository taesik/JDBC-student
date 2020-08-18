import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.InputMismatchException;
import java.util.Scanner;
public class CallableStatement2 {
	public static void main(String[] args) {
		String title = null, publisher = null, year = null;
		int price = 0;
		Scanner input = null;
		Connection con = null;
		CallableStatement cstmt =null;
		
		try {
			input = new Scanner(System.in);
			System.out.print("å���� �Է�: ");
			title = input.nextLine();
			System.out.print("���ǻ� �Է�: ");
			publisher =input.nextLine();
			System.out.print("�Ⱓ�⵵ �Է� : ");
			year = input.nextLine();
			System.out.print("�����Է� : ");
			price = input.nextInt();
		} catch (InputMismatchException i) {
			System.out.println("�Է°��� �߸��Ǿ����ϴ�.");
			System.exit(1);
			
		}finally {
			if (input != null) input.close();
		}
		try {
			con = ConnectionDB.makeConnection("javauser", "java1234");
			cstmt = con.prepareCall("{call books_input(?,?,?,?,?)}");
			cstmt.setString(1, title);
			cstmt.setString(2, publisher);
			cstmt.setString(3, year);
			cstmt.setInt(4, price);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			
			cstmt.executeQuery();
			System.out.println(cstmt.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("ERROR("+e.getMessage()+")");
		}finally {
			try {
				if(cstmt != null) cstmt.close();
				if(con!=null) con.close();
				if(input != null) input.close();
			} catch (Exception e2) {
				System.out.println("������������");
				e2.printStackTrace();
			}
		}
		
	}
}
