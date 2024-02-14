package com.example.forteachersapp;

public class dataholder {
    String studentName, usnNumber, complaint, subject, email, section;

    public dataholder(String studentName, String usnNumber, String complaint, String subject, String email, String section) {
        this.studentName = studentName;
        this.usnNumber = usnNumber;
        this.complaint = complaint;
        this.subject = subject;
        this.email=email;
        this.section=section;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUsnNumber() {
        return usnNumber;
    }

    public void setUsnNumber(String usnNumber) {
        this.usnNumber = usnNumber;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
