package Student;

public class StudentVO {
	//no, sd_num, sd_name, sd_id, sd_passwd, s_num, sd_birth, sd_phone, sd_address, sd_email
	private int no;
	private String sd_num;
	private String sd_name;
	private String sd_id;
	private String sd_passwd;
	private String s_num;
	private String sd_birth;
	private String sd_phone;
	private String sd_address;
	private String sd_email;
	
	
	StudentVO() {}
	protected StudentVO(int no, String sd_num, String sd_name, String sd_id, String sd_passwd, String s_num,
			String sd_birth, String sd_phone, String sd_address, String sd_email) {
		super();
		this.no = no;
		this.sd_num = sd_num;
		this.sd_name = sd_name;
		this.sd_id = sd_id;
		this.sd_passwd = sd_passwd;
		this.s_num = s_num;
		this.sd_birth = sd_birth;
		this.sd_phone = sd_phone;
		this.sd_address = sd_address;
		this.sd_email = sd_email;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSd_num() {
		return sd_num;
	}
	public void setSd_num(String sd_num) {
		this.sd_num = sd_num;
	}
	public String getSd_name() {
		return sd_name;
	}
	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}
	public String getSd_id() {
		return sd_id;
	}
	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}
	public String getSd_passwd() {
		return sd_passwd;
	}
	public void setSd_passwd(String sd_passwd) {
		this.sd_passwd = sd_passwd;
	}
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
	public String getSd_birth() {
		return sd_birth;
	}
	public void setSd_birth(String sd_birth) {
		this.sd_birth = sd_birth;
	}
	public String getSd_phone() {
		return sd_phone;
	}
	public void setSd_phone(String sd_phone) {
		this.sd_phone = sd_phone;
	}
	public String getSd_address() {
		return sd_address;
	}
	public void setSd_address(String sd_address) {
		this.sd_address = sd_address;
	}
	public String getSd_email() {
		return sd_email;
	}
	public void setSd_email(String sd_email) {
		this.sd_email = sd_email;
	}
	
	
	
	
	
	
	
		
	
	

}
