package School;

public class SubjectVO {
	
	//VO Ŭ������ �����͸� ��� �����̳� ������ �ϴ� Ŭ�������� ������ ������ �������� ������� Ŭ�����̴�.
	// VO Ŭ������ ���ø������� �������� �� �ܰ���  ����� ������ �����ϴ� ������ �����ϸ� �Ӽ� ������ �����ڷ� �����ȴ�.
	//DAO Ŭ�������� �����ͺ��̽��� �����ϴ� �޼��带 ���� ���̴�.
	
	private int no;
	private String s_num;
	private String s_name;
	
	SubjectVO(){}
	
	@Override
	public String toString() {
		return "SubjectVO [no=" + no + ", s_num=" + s_num + ", s_name=" + s_name + "]";
	}//toString(0

	protected SubjectVO(int no, String s_num, String s_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.s_name = s_name;
	}//SubjectVO (no,snum.,snme)
	public int getNo() {
		return no;
	}//getno
	public void setNo(int no) {
		this.no = no;
	}//setno
	public String getS_num() {
		return s_num;
	}//getSnum
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}//setSnum
	public String getS_name() {
		return s_name;
	}//getSname
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}//setSaneme
	
	

}
