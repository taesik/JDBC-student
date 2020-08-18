import java.sql.*;
public class SQLInsertTest {
	
	
	public static void main(String[] args) {
//		addBook("어서와 ","이두두","2020",16000);
		readBookAll();
		
	}
	
	
	
	private static void addBook(String title, String publisher, String year,int price) {
		Connection con = ConnectionDB.makeConnection("javauser", "java1234");
		PreparedStatement stmt=null;
		try { 
//			stmt = con.createStatement();
			
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO books (book_id,title, publisher, ");
			sb.append("year, price ) VALUES (books_seq.nextval, ?, ?, ?,?)");
		
			stmt = con.prepareStatement(sb.toString());
			stmt.setString(1, title);
			stmt.setString(2, publisher);
			stmt.setString(3, year);
			stmt.setInt(4, price);
			
			
			System.out.println("query : "+ sb);
			
			int insertCount = stmt.executeUpdate();
			if (insertCount==1) {
				System.out.println("Added");
			} else {
				System.out.println("fail");
			}
			     
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			try {
				if(stmt != null) {stmt.close();}
				if (con != null) {con.close();}
			}catch (Exception ex) {
				ex.printStackTrace();
					
				}
			}
		}
	private static void readBookAll() {
		Connection con = ConnectionDB.makeConnection("javauser", "java1234");
		Statement stmt = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select book_id,title,publisher, year, price");
			sb.append(" from books");
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());
			System.out.println("\n===== table data=====");
			System.out.println("bname\ttitle\t\tpublisher\treleased date\tprice\n");
			while (rs.next()) {
				System.out.print(rs.getInt("book_id")+"\t");
				System.out.print(rs.getString("title")+"\t\t");
				System.out.print(rs.getString("publisher")+"\t");
				System.out.print(rs.getInt("price")+ "\t\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (stmt != null) {stmt.close();}
				if (con != null) {con.close();}
					
					}
			catch(Exception ex) {
				ex.printStackTrace();
				}
			}
		}
	
		
//	private static void deleteBook(int n) {
//		Connection con = ConnectionDB.makeConnection("javauser", "java1234");
//		Statement stmt = null;
//		
//		try {
//			StringBuffer sb = new StringBuffer();
//			sb.append("delete from books where "+"'"+ )
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}+
		
		
	}

	
	
	



