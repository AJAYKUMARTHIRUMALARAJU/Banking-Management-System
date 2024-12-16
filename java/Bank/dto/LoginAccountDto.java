package Bank.dto;

public class LoginAccountDto {
	private String Email,Password;

	public LoginAccountDto(String Email, String Password) {
		super();
		this.Email = Email;
		this.Password = Password;
	}

	public LoginAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	@Override
	public String toString() {
		return "LoginAccountDto [Email=" + Email + ", Password=" + Password + "]";
	}
	
}
