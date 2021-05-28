package project;
/**
 * 유저의 정보를 반환하는 클래스
 * 
 * @author Gyeonghun Jo
 *
 */

public class User {
	/**
	 * 유저의 정보 - 아이디, 이름, 비밀번호를 반환한다.
	 * 
	 */
	private String id;
	private String name;
	private String password;
	
	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	/**
	 * 필드 getId의 accessor
	 * @return id의 값
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 필드 setId의 mutator
	 * @param id의 값 (문자열)
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 필드 getName의 accessor
	 * @return name의 값
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 필드 setName의 mutator
	 * @param name의 값 (문자열)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 필드 getPassword의 accessor
	 * @return password의 값
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 필드 setPassword의 mutator
	 * @param password의 값 (문자열)
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * toString user의 정보를 반환한다.
	 * @return User의 정보 (문자열)
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
}
