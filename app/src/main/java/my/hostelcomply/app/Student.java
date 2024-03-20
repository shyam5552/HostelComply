package my.hostelcomply.app;

public class Student {
    private String Fname,Lname,Roomnumber,Email,Pass,cpass,mobileno;

    public Student(String Fname, String Lname, String Roomnumber, String Email, String Pass, String mobileno, String cpass) {

        Fname = Fname;
        Lname = Lname;
        Roomnumber = Roomnumber;
        Email = Email;
        Pass = Pass;
        mobileno = mobileno;
        cpass = cpass;

    }
    public Student() {
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getRoomnumber() {
        return Roomnumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getPass() {
        return Pass;
    }

    public String getCpass() {
        return cpass;
    }

    public String getMobileno() {
        return mobileno;
    }
}
