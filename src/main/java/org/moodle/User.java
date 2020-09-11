package org.moodle;

/**
* The MoodleServices - User class base to start with Moodle API
*
* @author  Jose Wuiler Pozzi
* @version 0.2.5
* @since   2020-08-01
*/
public class User {

    //Informacion obtenida de la documentación de Moodle    
    //Username policy is defined in Moodle security config.
    private String userName;

    //The first name(s) of the user
    private String firstName;

    //The family name of the user
    private String lastName;

    //A valid and unique email address
    private String email;

    //Optional - Plain text password consisting of any characters
    private String password;

    // Optional - True if password should be created and mailed to user.
    private int createPassword;

    //Optional - Email display
    private int mailDisplay;
    
    //Optional Phone 1
    private String phone1;

    //Optional Phone 2
    private String phone2;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCreatePassword() {
        return createPassword;
    }

    public void setCreatePassword(int createPassword) {
        this.createPassword = createPassword;
    }

    public int getMailDisplay() {
        return mailDisplay;
    }

    public void setMailDisplay(int mailDisplay) {
        this.mailDisplay = mailDisplay;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
        
    @Override
    public String toString() {
        return "User [createPassword=" + createPassword + ", email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", mailDisplay=" + mailDisplay + ", password=" + password + ", userName="
                + userName + "]";
    }

}