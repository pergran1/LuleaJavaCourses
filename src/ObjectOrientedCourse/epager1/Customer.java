package ObjectOrientedCourse.epager1;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private String surName;
    private String personalNumber;
    private ArrayList<Account> accountList = new ArrayList<Account>();

    public Customer(String name, String surName, String personalNumber){

        this.name = name;
        this.surName = surName;
        this.personalNumber = personalNumber;

    }

    // method to create a account to the customer
    public int createAccount(){
        Account newAcc = new Account();
        accountList.add(newAcc);
        return newAcc.getAccountNumber();
    }

    public String getPersonalNumber(){
        return  personalNumber;
    }

    public String getName(){
        return name;
    }

    public String getSurName(){
        return surName;
    }

    public String getFullName(){
        return name + " " + surName;
    }

    public ArrayList<Account> getAccountList(){
        return accountList;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setSurName(String newSurName){
        surName = newSurName;
    }

    // Must override the toString function in order to print the correct format
    @Override
    public String toString() {
        return (getPersonalNumber() + " " + getFullName());
    }

    public static void main(String[] args) {
        Customer test = new Customer("Per", "Granberg", "9409184565");
        System.out.println(test.createAccount());
        Customer test2 = new Customer("Per", "Granberg", "9409184565");
        System.out.println(test2.createAccount());
        System.out.println(test2);



    }





}
