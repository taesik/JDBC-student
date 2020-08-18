package Student;

import java.util.ArrayList;
import java.util.Scanner;

import School.SubjectVO;
import School.subjectDAO;

public class StudentEx {
private static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("velge");
		System.out.println("1.������ ��ȸ");
		System.out.println("2.�������Է�");
		System.out.println("3.������ ����");
		System.out.println("4.������ ����");
		System.out.println("5.������ �˻� �а���");
		System.out.println("6.���α׷� ����");
		System.out.print("����");
		
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
				System.out.println("���α׷� ����");
				

				
				System.exit(0);
			}
		}
	}//main end
	private static void read(StudentDAO dao) {
		try {
			ArrayList<StudentVO> svo = dao.getStudentTotal();
			System.out.println("\n**** student ���̺� ������ ���****");
			System.out.println("��ȣ \t�а���ȣ \t�а���");
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
				System.out.println("�л������� �������� �ʽ��ϴ�.");
				
			
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
//				System.out.print("�а��ڵ� �Է� : ");
//				System.out.println(s_num);
//				break;
//		 }		
//	}
//	

}
