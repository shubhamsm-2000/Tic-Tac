package com.example.tictac;

public class PlayerData {

    String Name1, Name2, Number1, Number2;
    int age1, age2;

    public PlayerData(){

    }

    public PlayerData(String name1, String number1, int age1) {
        Name1 = name1;
        Number1 = number1;
        this.age1 = age1;
    }

    public PlayerData(String name1, String name2, String number1, String number2, int age1, int age2) {
        Name1 = name1;
        Name2 = name2;
        Number1 = number1;
        Number2 = number2;
        this.age1 = age1;
        this.age2 = age2;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    public void setNumber1(String number1) {
        Number1 = number1;
    }

    public void setNumber2(String number2) {
        Number2 = number2;
    }

    public void setAge1(int age1) {
        this.age1 = age1;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }
}
