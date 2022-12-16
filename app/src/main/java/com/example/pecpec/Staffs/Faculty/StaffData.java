package com.example.pecpec.Staffs.Faculty;



public class StaffData {




    private String name,email,password,phoneNo,post,spinner,image,key,dbkey,userdbpushKey;

    private StaffData(){


    }

    public StaffData(String name, String email, String password, String phoneNo, String post, String spinner, String image, String key, String dbkey, String userdbpushKey) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.post = post;
        this.spinner = spinner;
        this.image = image;
        this.key = key;
        this.dbkey = dbkey;
        this.userdbpushKey = userdbpushKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDbkey() {
        return dbkey;
    }

    public void setDbkey(String dbkey) {
        this.dbkey = dbkey;
    }

    public String getUserdbpushKey() {
        return userdbpushKey;
    }

    public void setUserdbpushKey(String userdbpushKey) {
        this.userdbpushKey = userdbpushKey;
    }
}
