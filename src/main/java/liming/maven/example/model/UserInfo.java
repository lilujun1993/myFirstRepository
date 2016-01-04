package liming.maven.example.model;

public class UserInfo {

	private int id;
	private String age;
	private String userName;
	public UserInfo(){
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserInfo(int id, String age, String userName) {
		super();
		this.id = id;
		this.age = age;
		this.userName = userName;
	}
}
