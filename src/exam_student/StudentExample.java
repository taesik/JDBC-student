package exam_student;

/*�Է¿���
ȫ�浿
hhhh
h001
02
900702
01000000001
ȫȫȫ
h@h.h
 */

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class StudentExample {
	private static Scanner sc = new Scanner(System.in);

	// �޴� ��� �޼ҵ�
	public static void showMenu() {
		System.out.println("=========================================================================="
				+ "=======================================================================");
		System.out.println("�����ϼ���");
		System.out.println("1. ������ ��ȸ");
		System.out.println("2. ������ �Է�");
		System.out.println("3. ������ ����");
		System.out.println("4. ������ �˻�");
		System.out.println("5. ������ ����");
		System.out.println("6. ���α׷� ����");
		System.out.print("����>>");
	}

	// ���� �޼ҵ�. ��ȣ ���ÿ� ���� �޼ҵ� ȣ��, ����
	public static void main(String[] args) {
		StudentDAO dao = StudentDAO.getInstance();
		int menuChoice;

		while (true) {
			showMenu();
			menuChoice = sc.nextInt();
			sc.nextLine();

			switch (menuChoice) {
			case 1:
				read(dao);// ��ȸ
				break;
			case 2:
				create(dao);// �߰�
				break;
			case 3:
				delete(dao);// ����
				break;
			case 4:
				search(dao);// �˻�
				break;
			case 5:
				updateData(dao);
				break;
			case 6:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
				break;
			}
		}
	}

	// �߰� ��ų �����͸� �Է¹޴� �޼ҵ�
	private static StudentVO inputData(String mode, StudentDAO dao) throws Exception {
		// ��ĳ�ʷ� �Է¹��� ����
		String sd_num = null, sd_name = null, sd_id = null, sd_passwd = null, s_num = null, sd_phone = null,
				sd_address = null, sd_email = null;

		// �ӽ÷� String�� ��������� 6�ڸ��� �Է¹޾� java.sql.Date ������ ��ȯ
		String birth = null;

		// Date
		String year, month, date;

		// java.sql.Date
		Date sd_birth = null;

		System.out.print("�л���:");
		sd_name = sc.nextLine().trim();
		System.out.print("�л�id:");
		sd_id = sc.nextLine().trim();
		System.out.print("�л�password:");
		sd_passwd = sc.nextLine().trim();
		System.out.print("�а���ȣ:");
		s_num = sc.nextLine().trim();

		System.out.print("�������(6�ڸ�):");// yymmdd
		birth = sc.nextLine().trim();

		if (birth.substring(0, 1).equals("0"))// 0���� �����ϸ� 2000����
			year = 20 + birth.substring(0, 2);// dateŸ�Կ� ������ ���� yyyy
		else
			year = 19 + birth.substring(0, 2);
		month = birth.substring(2, 4);// mm
		date = birth.substring(4);// dd
		birth = year + "-" + month + "-" + date;// yy-mm-dd
		sd_birth = Date.valueOf(birth);

		System.out.print("��ȭ��ȣ:");
		sd_phone = sc.nextLine().trim();
		System.out.print("�ּ�:");
		sd_address = sc.nextLine().trim();
		System.out.print("e-mail:");
		sd_email = sc.nextLine().trim();

		sd_num = dao.studentNum(s_num);// �л���ȣ ���� �޼ҵ� ȣ��
		System.out.println("�л���ȣ�� [" + sd_num + "]���� ���� �Ǿ����ϴ�.");

		StudentVO svo = new StudentVO(0, sd_num, sd_name, sd_id, sd_passwd, s_num, sd_birth, sd_phone, sd_address,
				sd_email);
		return svo;
	}

	// ��ȸ �޼ҵ�
	private static void read(StudentDAO dao) {
		try {
			// ArrrayList�� ��Ƽ� for-each ������ ���
			ArrayList<StudentVO> svo = dao.getStudentTotal();

			System.out.println("\n=================================================================STUDENT ������ ���"
					+ "=================================================================");
			System.out.println("��ȣ\t�й�\t\t�̸�\tid\t\t��й�ȣ\t�а���ȣ\t�а���\t\t����\t\t��ȭ��ȣ\t\t�ּ�\t\t\t����\t\t\t�������");
			if (svo.size() > 0) {
				for (StudentVO vo : svo) {
					System.out.printf("%-2s\t", vo.getNo());
					System.out.printf("%-8s\t", vo.getSd_num());
					System.out.printf("%-2s\t", vo.getSd_name());
					System.out.printf("%-12s\t", vo.getSd_id());
					System.out.printf("%-7s\t", vo.getSd_passwd());
					System.out.printf("%s\t", vo.getS_num());
					System.out.printf("%-10s\t", vo.getS_name());
					System.out.printf("%-7s\t", vo.getSd_birth());
					System.out.printf("%-10s\t", vo.getSd_phone());
					if (vo.getSd_address().length() <= 4)
						System.out.printf("%-30s\t", vo.getSd_address());
					else
						System.out.printf("%-20s\t", vo.getSd_address());

					System.out.printf("%-20s\t", vo.getSd_email());
					System.out.printf("%s\n", vo.getSd_date());
				}
			} else// ���Ұ� 0�� ���
				System.out.println("�л� ������ �������� �ʽ��ϴ�.");
		} catch (Exception e) {// ���ܽ� catch
			e.printStackTrace();
		}
	}

	// ������ ���� �޼ҵ�
	private static void create(StudentDAO dao) {
		try {

			// ������ �Է¹޴� inputdata ȣ��
			StudentVO svo = inputData("create", dao);

			// ������ ���� �޼ҵ� ȣ��, boolean ����
			boolean result = dao.studentInsert(svo);
			if (result)// true ����
				System.out.println("�л����� �Է� ����.");
			else// false ����
				System.out.println("�л����� �Է� ����");
		} catch (Exception e) {// ���ܽ� catch
			e.printStackTrace();
		}
	}

	// ������ ��ȣ �Է¹޴� �޼ҵ�
	private static int inputDataNo() {
		int no;
		System.out.print("��ȣ �Է�>>");
		no = sc.nextInt();
		return no;
	}

	// ���� �޼ҵ�
	private static void delete(StudentDAO dao) {
		try {
			// svo.no �ʵ忡 ������ no�� ��� ������.
			StudentVO svo = new StudentVO();
			// ������ ��ȣ �Է¹޴� �޼ҵ� ȣ��
			int no = inputDataNo();
			svo.setNo(no);
			// ���� ������ ���� �޼ҵ� ȣ��, ���ڷ� svo ����
			boolean result = dao.studentDelete(svo);
			if (result)// �������� true
				System.out.println("�л� ���� ���� ����.");
			else// ���� ���� false
				System.out.println("�л� ���� ���� ����.");
		} catch (Exception e) {// ���ܽ� catch
			e.printStackTrace();
		}
	}

	private static String searchData() {
		System.out.print("�˻� ���(�л���, ���̵�, �����) �Է�:");
		String search = sc.nextLine();
		String word = null;
		if (search.equalsIgnoreCase("�����")) {
			System.out.print("�˻� �ܾ� �Է�(YYYY-MM-DD):");
			word = sc.nextLine();
		} else {
			System.out.print("�˻� �ܾ� �Է�:");
			word = sc.nextLine();
		}

		String data = search + "," + word;
		return data;
	}

	// �˻� �޼ҵ�
	private static void search(StudentDAO dao) {
		try {
			String[] result = searchData().split(",");
			ArrayList<StudentVO> list = dao.getStudentSearch(result[0], result[1]);

			System.out.println("\n=================================================================STUDENT ������ ���"
					+ "=================================================================");
			System.out.println("��ȣ\t�й�\t\t�̸�\tid\t\t��й�ȣ\t�а���ȣ\t�а���\t\t����\t\t��ȭ��ȣ\t\t�ּ�\t\t\t����\t\t\t�������");
			if (list.size() > 0) {
				for (StudentVO vo : list) {
					System.out.printf("%-2s\t", vo.getNo());
					System.out.printf("%-8s\t", vo.getSd_num());
					System.out.printf("%-2s\t", vo.getSd_name());
					System.out.printf("%-12s\t", vo.getSd_id());
					System.out.printf("%-7s\t", vo.getSd_passwd());
					System.out.printf("%s\t", vo.getS_num());
					System.out.printf("%-10s\t", vo.getS_name());
					System.out.printf("%-7s\t", vo.getSd_birth());
					System.out.printf("%-10s\t", vo.getSd_phone());
					if (vo.getSd_address().length() <= 4)
						System.out.printf("%-30s\t", vo.getSd_address());
					else
						System.out.printf("%-20s\t", vo.getSd_address());

					System.out.printf("%-20s\t", vo.getSd_email());
					System.out.printf("%s\n", vo.getSd_date());
				}
			} else {
				System.out.println("�˻��� �л� �����Ͱ� �������� �ʽ��ϴ�.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//�л����� ���� �޼ҵ�. �������� ���� ���� Enter Ű�� skip �Ѵ�.
	private static void updateData(StudentDAO dao) {
		String sd_num = "";
		
		//HashMap�� <�÷���, ��> �� ��� dao.studentUpdate �޼ҵ�� ������.
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			System.out.print("������ �л�");
			sd_num = inputDataNo() + "";
			
			//�Է����� ������ ���� "" �� �Ǳ� ������
			System.out.println("�������� ���� ������ [Enter]Ű�� �Ѱ� �ּ���.");
			
			//�й�, �̸�, id, �а���ȣ ���� �Ұ�
			System.out.print("password:");
			hm.put("sd_passwd", sc.nextLine()+sc.nextLine());

			System.out.print("�������(yymmdd):");
			hm.put("sd_birth", sc.nextLine());

			System.out.print("��ȭ��ȣ:");
			hm.put("sd_phone", sc.nextLine());
			System.out.print("�ּ�:");
			hm.put("sd_address", sc.nextLine());
			System.out.print("e-mail:");
			hm.put("sd_email", sc.nextLine());
			
			//value = "" �� ���� �����ϱ� ���� �۾�
			//keyset���� set�� key�� �����´�.
			Set <String> keys = hm.keySet();
			
			//while(itr.hasnext()) �ȿ��� ������ �ϸ� ������ ���� ������ for���� ������ ���� key�� ArrayList�� ��� �Ű���
			ArrayList<String> al = new ArrayList<String>();
			Iterator<String> itr = keys.iterator();
			while (itr.hasNext()) 
				al.add(itr.next());//Set -> ArrayList
			
			//key ���� ���� �Ǿ��ִ� ArrayList ������ ��ŭ for�� ����
			for (int i = 0 ; i < al.size() ; i++) {
				if (hm.get(al.get(i)).equals(""))//���� value�� "" �̶��
					hm.remove(al.get(i));//����
			}
			//���� hm ���� value�� �� ���� ����.

			boolean result = dao.studentUpdate(hm, sd_num);
			if (result)
				System.out.println("�л����� ���� ����");
			else
				System.out.println("�л����� ���� ����");
		} catch (Exception e) {
			System.out.println("error = [" + e + "]");
		}

	}
}