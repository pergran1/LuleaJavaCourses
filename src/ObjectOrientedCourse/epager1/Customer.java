/**
 * Denna klass representerar en kund i banken. Klassen innehåller information såsom namn, efternamn, personnummer
 * och en lista med konton.
 *
 *  Jag har skapat en metod som gör det enklare i BankLogic att hitta ett valt konto eller ta bort ett konto
 * @author Per Granberg, epager-1
 */


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


    public int createAccount(){
        Account newAcc = new SavingsAccount();
        accountList.add(newAcc);
        return newAcc.getAccountNumber();
    }

    public int createCreditAccount(){
        Account newAcc = new CreditAccount();
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


    public ArrayList<String> getStringAccountList(){
        ArrayList<String> strList = new ArrayList<String>();
        for (Account account: accountList){
            strList.add(account.toString());
        }
        return strList;
    }

    public ArrayList<String> getDeletionStringAccountList(){
        ArrayList<String> strList = new ArrayList<String>();
        for (Account account: accountList){
            strList.add(account.closeAccStr());
        }
        return strList;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setSurName(String newSurName){
        surName = newSurName;
    }


    public void changeNames(String newName, String newSurName){
        if(newName.isEmpty() == false) name = newName;
        if (newSurName.isEmpty() == false) surName = newSurName;
    }



    /**
     * Metod för att hitta ett konto hos kunden
     * @param int accountNbr
     * @return Account, null om kontot inte finns
     */
    public Account findAccount(int accountNbr){
        Account foundAccount = null;
        for(Account account: accountList){
            if(account.getAccountNumber() == accountNbr){
                foundAccount = account;
                break;
            }
        }
        return foundAccount;
    }


    /**
     * Ta bort kontot som finns i kundens lista
     * @param int accountNbr
     * @return String, information om kontot som stängdes
     */
    public String removeAccount(int accountNbr){
        Account foundAccount = findAccount(accountNbr);
        if (foundAccount != null){
            String closeAccount = foundAccount.closeAccStr();
            accountList.remove(foundAccount);
            return closeAccount;
        }
        return null;
    }


    /**
     * Tar bort alla konton genom att ersätta kundens ArrayList med konton med en tom lista
     */
    public void removeAllAccounts(){
        ArrayList<Account> accountList = new ArrayList<Account>();
    }


    @Override
    public String toString() {
        return (personalNumber + " " + getFullName());
    }


}
