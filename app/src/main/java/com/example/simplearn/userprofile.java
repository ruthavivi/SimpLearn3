package com.example.simplearn;

public class userprofile {

    public boolean soundOn;
    public String username,userUID,age,motherlanguage,learnlanguage,bio;
    public String image;
    public userprofile() {
    }

    public userprofile(String username, String userUID,String age,String motherlanguage,String learnlanguage,String bio) {
        this.username = username;
        this.userUID = userUID;
        this.age=age;
        this.motherlanguage=motherlanguage;
        this.learnlanguage=learnlanguage;
        this.bio=bio;
    }
    public boolean isSoundOn() {return soundOn;}
    public void setSoundOn(boolean soundOn) {this.soundOn = soundOn;}
    public String getImage() {
        return image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImage(String image) {
        this.image = image;
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

