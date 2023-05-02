package com.example.simplearn;

import androidx.fragment.app.Fragment;

public class firebasemodel extends Fragment  {

    public String name;
    public String image;
    public String uid;
    public String bio;
    public String status;
    public String age;
    public String motherlanguage;
    public String learnlanguage;




    public firebasemodel(String name, String image, String uid, String status,String age,String motherlanguage,String learnlanguage,String bio) {
        this.name = name;
        this.image = image;
        this.uid = uid;
        this.status = status;
        this.age=age;
        this.learnlanguage=learnlanguage;
        this.motherlanguage=motherlanguage;
        this.bio=bio;
    }

    public firebasemodel() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public  void setMotherlanguage(String motherlanguage){this.motherlanguage=motherlanguage;}

    public  void setAge(String age){this.age=age;}

    public void setLearnlanguage(String learnlanguage) {
        this.learnlanguage=learnlanguage;
    }

    public  String getMotherlanguage(){return motherlanguage;}

    public  String getAge(){return age;}

    public String getLearnlanguage() {
        return learnlanguage;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
