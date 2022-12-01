package com.example.simplearn;

public class userprofile {


    public String username,userUID,age,motherlanguage,learnlanguage;

    public userprofile() {
    }

    public userprofile(String username, String userUID,String age,String motherlanguage,String learnlanguage) {
        this.username = username;
        this.userUID = userUID;
        this.age=age;
        this.motherlanguage=motherlanguage;
        this.learnlanguage=learnlanguage;
    }


    public String getMotherlanguage() {
        return motherlanguage;
    }

    public String getAge() {
        return age;
    }

    public String getLearnlanguage() {
        return learnlanguage;
    }


    public void setAge(String age) {
        this.age = age;
    }
    public void setMotherlanguage(String motherlanguage) {
        this.motherlanguage = motherlanguage;
    }
    public void setLearnlanguage(String learnlanguage) {
        this.learnlanguage = learnlanguage;
    }





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}

