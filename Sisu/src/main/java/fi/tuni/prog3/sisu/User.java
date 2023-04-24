package fi.tuni.prog3.sisu;

public class User {

    public String firstname;

    public String lastname;
    public String email;
    public String password;
    public int year;

    public String studentNumber;
    public String startingDate;

    public User(String firstname, String lastname, String email, String password, int year, String studentNumber, String startingDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.year = year;
        this.studentNumber = studentNumber;
        this.startingDate = startingDate;
    }

    // getters and setters
    public String getStartingDate() {
        return startingDate;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    //Inherit toString above with startingNumber
    @Override
    public String toString() {
        return "User{"
                + "firstname='" + firstname + '\''
                + ", lastname='" + lastname + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", year=" + year
                + ", studentNumber='" + studentNumber + '\''
                + ", startingDate='" + startingDate + '\''
                + '}';
    }
}