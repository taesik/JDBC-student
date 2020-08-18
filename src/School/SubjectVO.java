package School;

public class SubjectVO {
	
	//VO 클래스는 데이터를 담는 컨테이너 역할을 하는 클래슬ㅊ폴 데이터 전달을 목적으로 만들어진 클래스이다.
	// VO 클래스는 어플리게이켠 구조에서 각 단계의  입출력 정보를 전달하는 역할을 수행하며 속성 성정자 접근자로 구성된다.
	//DAO 클래스에서 데이터베이스로 접근하는 메서드를 만들 것이다.
	
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
