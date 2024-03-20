package my.hostelcomply.app.studentPanel;

public class StudentComplaintDetails {
    String Roomnumber, Mobile_No, problemitem, desc, RandomUid, StudentId, First_Name;


    public StudentComplaintDetails(String roomnumber, String phonenumber, String problems, String description, String randomUid, String studentId) {
        Roomnumber = roomnumber;
        Mobile_No = phonenumber;
        problemitem = problems;
        desc = description;
        RandomUid = randomUid;
        StudentId = studentId;

    }

    public String getRoomnumber() {
        return Roomnumber;
    }

    public String getPhonenumber() {
        return Mobile_No;
    }

    public String getProblemitem() {
        return problemitem;
    }

    public String getDesc() {
        return desc;
    }

    public String getRandomUid() {
        return RandomUid;
    }

    public String getStudentId() {
        return StudentId;
    }

    public String getFname() {
        return First_Name;
    }

    public void setRoomnumber(String roomnumber) {
        Roomnumber = roomnumber;
    }

    public void setPhonenumber(String phonenumber) {
        Mobile_No = phonenumber;
    }

    public void setProblemitem(String problemitem) {
        this.problemitem = problemitem;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRandomUid(String randomUid) {
        RandomUid = randomUid;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public void setFname(String fname) {
        First_Name = fname;
    }
}





