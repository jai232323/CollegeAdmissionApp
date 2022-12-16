package com.example.pecpec.Staffs.Faculty;



public class AdmissionData {
    private String name,fathername,mothername,address,mobileNo,AlterMobileNo,
            email,AlterEmail,aadhar,caste,course,dob,ApplicantImage,gender,maritalStatus;


    private AdmissionData() {

    }

    public AdmissionData(String name, String fathername, String mothername, String address, String mobileNo, String alterMobileNo, String email, String alterEmail, String aadhar, String caste, String course, String dob, String applicantImage, String gender, String maritalStatus) {
        this.name = name;
        this.fathername = fathername;
        this.mothername = mothername;
        this.address = address;
        this.mobileNo = mobileNo;
        AlterMobileNo = alterMobileNo;
        this.email = email;
        AlterEmail = alterEmail;
        this.aadhar = aadhar;
        this.caste = caste;
        this.course = course;
        this.dob = dob;
        ApplicantImage = applicantImage;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAlterMobileNo() {
        return AlterMobileNo;
    }

    public void setAlterMobileNo(String alterMobileNo) {
        AlterMobileNo = alterMobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlterEmail() {
        return AlterEmail;
    }

    public void setAlterEmail(String alterEmail) {
        AlterEmail = alterEmail;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getApplicantImage() {
        return ApplicantImage;
    }

    public void setApplicantImage(String applicantImage) {
        ApplicantImage = applicantImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
