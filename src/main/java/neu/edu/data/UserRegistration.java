package neu.edu.data;

public class UserRegistration {
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Role role;

	public enum Role {

		ADMIN("admin"), USER("user");

		private final String roleName;

		// private enum constructor
		private Role(String roleName) {
			this.roleName = roleName;
		}
		
		public String getRoleName() {
			return roleName;
		}

	}

	public UserRegistration() {
		// TODO Auto-generated constructor stub
	}

	public UserRegistration(String username, String firstname,String lastname, String password, String email, Role role) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public UserRegistration(String username, String email, String role) {

		this.username = username;
		this.email = email;
		if("admin".equals(role)) {
			this.role = Role.ADMIN;
		}else if("user".equals(role)) {
			this.role = Role.USER;
		}
		
	}
	
	public UserRegistration(String username, String firstname, String lastname, String email, String role) {

		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		if("admin".equals(role)) {
			this.role = Role.ADMIN;
		}else if("user".equals(role)) {
			this.role = Role.USER;
		}
		
	}
	

    
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}