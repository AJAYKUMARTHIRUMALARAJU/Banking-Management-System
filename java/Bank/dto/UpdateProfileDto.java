package Bank.dto;

import java.sql.Date;

public class UpdateProfileDto {
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private long phone;
    private String address;
    private Date dateOfBirth;

    public UpdateProfileDto(String email, String firstName, String lastName, String gender, int age, long phone,
            String address, Date dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public UpdateProfileDto() {
        // Default constructor
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UpdateProfileDto [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", gender=" + gender + ", age=" + age + ", phone=" + phone + ", address=" + address
                + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
