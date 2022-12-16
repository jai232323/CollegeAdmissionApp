package com.example.pecpec.Students;

public class StudentData {

    String F_Id, S_Name,S_Email,S_Password,S_Image;

    public StudentData() {
    }

    public StudentData(String f_Id, String s_Name, String s_Email, String s_Password, String s_Image) {
        F_Id = f_Id;
        S_Name = s_Name;
        S_Email = s_Email;
        S_Password = s_Password;
        S_Image = s_Image;
    }

    public String getF_Id() {
        return F_Id;
    }

    public void setF_Id(String f_Id) {
        F_Id = f_Id;
    }

    public String getS_Name() {
        return S_Name;
    }

    public void setS_Name(String s_Name) {
        S_Name = s_Name;
    }

    public String getS_Email() {
        return S_Email;
    }

    public void setS_Email(String s_Email) {
        S_Email = s_Email;
    }

    public String getS_Password() {
        return S_Password;
    }

    public void setS_Password(String s_Password) {
        S_Password = s_Password;
    }

    public String getS_Image() {
        return S_Image;
    }

    public void setS_Image(String s_Image) {
        S_Image = s_Image;
    }
}
