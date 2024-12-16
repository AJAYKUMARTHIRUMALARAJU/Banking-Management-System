package Bank.dto;

public class ForgotPasswordDto {
	private String Email,Password,ConfirmPassword;

	public ForgotPasswordDto(String email, String password, String confirmPassword) {
		super();
		Email = email;
		Password = password;
		ConfirmPassword = confirmPassword;
	}

	public ForgotPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ForgotPasswordDto [Email=" + Email + ", Password=" + Password + ", ConfirmPassword=" + ConfirmPassword
				+ "]";
	}
}
