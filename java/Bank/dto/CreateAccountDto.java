package Bank.dto;

import java.sql.Date;

public class CreateAccountDto {
	private String Email,First_Name,Last_Name,Gender;
	private int Age;
	private long Phone;
	private String Address,Password;
	private Date DateOfBirth;
	public CreateAccountDto(String email, String first_Name, String last_Name, String gender, int age, long phone,
			String address, String password, Date dateOfBirth) {
		super();
		Email = email;
		First_Name = first_Name;
		Last_Name = last_Name;
		Gender = gender;
		Age = age;
		Phone = phone;
		Address = address;
		Password = password;
		DateOfBirth = dateOfBirth;
	}
	public CreateAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public long getPhone() {
		return Phone;
	}
	public void setPhone(long phone) {
		Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "CreateAccountDto [Email=" + Email + ", First_Name=" + First_Name + ", Last_Name=" + Last_Name
				+ ", Gender=" + Gender + ", Age=" + Age + ", Phone=" + Phone + ", Address=" + Address + ", Password="
				+ Password + ", DateOfBirth=" + DateOfBirth + "]";
	}
	
	
}
