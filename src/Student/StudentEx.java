package Student;

import java.util.ArrayList;
import java.util.Scanner;

import School.SubjectVO;
import School.subjectDAO;

public class StudentEx {
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
		
	}//showmenu end
	public static void main(String[] args) {
		StudentDAO dao = StudentDAO.getInstance();
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
//				create(dao);
				break;
			case 3:
//				update(dao);
				break;
			case 4:
//				delete(dao);
				break;
			case 5:
//				search(dao);
				break;
			case 6: 
				System.out.println("프로그램 종료");
				

				
				System.exit(0);
			}
		}
	}//main end
	private static void read(StudentDAO dao) {
		try {
			ArrayList<StudentVO> svo = dao.getStudentTotal();
			System.out.println("\n**** student 테이블 데이터 출력****");
			System.out.println("번호 \t학과번호 \t학과명");
			if (svo.size()>0) {
				for (StudentVO sub : svo) {
					System.out.print(sub.getSd_num()+"\t");
					System.out.println(sub.getSd_name()+"\t");
					System.out.println(sub.getS_num()+"\t");
					System.out.println(sub.getSd_birth()+"\t");
					System.out.println(sub.getSd_phone()+"\t");
					System.out.println(sub.getSd_address()+"\t");
					System.out.println(sub.getSd_email()+"\t");
				}
			}else 
				System.out.println("학생정보가 존재하지 않습니다.");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//read(StudentDAO)
	
//	private static SubjectVO inputData(String mode, subjectDAO dao) throws Exception{
//		 String sd_num=null;
//		 String sd_name=null;
//		 String sd_id=null;
//		 String sd_passwd=null;
//		 String s_num=null;
//		 String sd_birth=null;
//		 String sd_phone=null;
//		 String sd_address=null;
//		 String sd_email=null;
//		 switch (mode) {
//			case "input":
//				System.out.print("학과코드 입력 : ");
//				System.out.println(s_num);
//				break;
//		 }		
//	}
//	

}
