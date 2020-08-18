package School;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SubjectExample {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("velge");
		System.out.println("1.데이터 조회");
		System.out.println("2.데이터입력");
		System.out.println("3.데이터 수정");
		System.out.println("4.데이터 삭제");
		System.out.println("5.데이터 검색 학과명");
		System.out.println("6.프로그램 종료");
		System.out.print("선택");
		
	}
	
	
	
	
	public static void main(String[] args) {
		subjectDAO dao = subjectDAO.getInstance();
		int menuChoice;
		
		while (true) {
			showMenu();
			menuChoice = sc.nextInt();
			sc.nextLine();
			
			switch (menuChoice) {
			case 1:
				read(dao);
				break;
			case 2:
				create(dao);
				break;
			case 3:
				update(dao);
				break;
			case 4:
				delete(dao);
				break;
			case 5:
				search(dao);
				break;
			case 6: 
				System.out.println("프로그램 종료");
				

				
				System.exit(0);
			}
		}
	}//main end




	private static void read(subjectDAO dao) {
		try {
			ArrayList<SubjectVO> svo = dao.getSubjectTotal();
			System.out.println("\n**** subject 테이블 데이터 출력****");
			System.out.println("번호 \t학과번호 \t학과명");
			if (svo.size()>0) {
				for (SubjectVO sub : svo) {
					System.out.print(sub.getNo()+"\t");
					System.out.print(sub.getS_num()+"\t");
					System.out.println(sub.getS_name()+"\t");
					
				}
			}else 
				System.out.println("학과정보가 존재하지 않습니다.");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//read(subjectDAO)
	private static SubjectVO inputData(String mode, subjectDAO dao) throws Exception{
//	private static SubjectVO inputData(String mode) {
		String s_num=null; 
		String s_name = null;
//		if (!mode.equals("search")) {
//			System.out.print("학과코드 입력 : ");
//			s_num = sc.nextLine();
//			
//		}
//		System.out.print("학과명입력 : ");
//		s_name = sc.nextLine();
//		SubjectVO sub = new SubjectVO(0,s_num,s_name);
//		return sub;
		switch (mode) {
		case "input":
			System.out.print("학과코드 입력 : ");
			s_num = dao.getSubjectNum();
			System.out.println(s_num);
			break;

		case "update":
			System.out.print("학과코드입력: ");
			s_num = sc.nextLine();
			break;

			
		}
		System.out.print("학과명입력 : ");
		s_name = sc.nextLine();
		SubjectVO sub = new SubjectVO(0,s_num,s_name);
		return sub;
		
		
	}//inputDAta (String)
	
	
	
	
	
	private static void create(subjectDAO dao) {
		try {
			SubjectVO svo = inputData("input",dao);
			boolean result = dao.subjectInsert(svo);
			if (result) 
				System.out.println("학과데이터 입력 성공.");
			else 
				System.out.println("학과데이터 입력 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//creaete(subjectDAO)
	private static int inputDataNo() {
		int no;
		System.out.print("번호입력 : ");
		no = sc.nextInt();
		return no;
	}//inputDataNo()
	
	
	private static void update(subjectDAO dao) {
		try {
			SubjectVO svo = inputData("update",dao);
			int no = inputDataNo();
			svo.setNo(no);
			boolean result = dao.subjectUpdate(svo);
			if (result) 
				System.out.println("학과 데이터 수정 성공");
			else 
				System.out.println("학과 데이터 수정 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}//update(subjectDAO)
	private static void delete(subjectDAO dao) {
		try {
			int no = inputDataNo();
			SubjectVO svo = new SubjectVO();
			svo.setNo(no);
			boolean result = dao.subjectDelete(svo);
			if (result) 
				System.out.println("학과 데이터 삭제 성공");
			else 
				System.out.println("학과 데이터 삭제 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//delete(subjectDAO)
	private static void search(subjectDAO dao) {
		try {
			SubjectVO svo = inputData("search",dao);
			System.out.println("검색단어 : " + svo.getS_name());
			
			ArrayList<SubjectVO> list =dao.getSubjectSearch(svo.getS_name());
			System.out.println("번호\t학과번호\t학과명");
			if (list.size()>0) {
				for (SubjectVO sub : list) {
					System.out.print(sub.getNo()+ "\t");
					System.out.print(sub.getS_num()+"\t");
					System.out.println(sub.getS_name()+"\t");
				}
			}else System.out.println("학과정보가 존재하지 않습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//search(subjectDAO)
	
	
}
