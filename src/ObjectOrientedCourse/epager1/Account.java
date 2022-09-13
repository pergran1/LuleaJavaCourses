package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private BigDecimal interest;
    private int accountNumber;
    private static int nbrOfAccounts = 1000;
    private String accountType;

    public Account() {
        nbrOfAccounts++;
        this.balance = new BigDecimal("0.00");
        this.interest= new BigDecimal("0.012");
        this.accountNumber = nbrOfAccounts;
        this.accountType = "Sparkonto";
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public String getAccount(){
        return accountType;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public BigDecimal getInterest(){
        return interest;
    }

    public void deposit(int amount){
        balance = balance.add(new BigDecimal(amount));
    }

    public void withdraw(int amount){
        balance = balance.subtract(new BigDecimal(amount));
    }

    @Override
    public String toString() {
        return (getAccountNumber() + " " + getBalance() + " " +  getAccount() + " " + getInterest());
    }

    public String closeAccStr() {
        BigDecimal finalInterest =  getInterest().multiply(getBalance());
        return (getAccountNumber() + " " + getBalance() + " " +  getAccount() + " " + finalInterest);
    }





    public static void main(String[] args) {
        Account testar = new Account();
        Account testar2 = new Account();

        System.out.println(testar.getAccount());
        System.out.println(testar.getAccountNumber());
        System.out.println(testar2.getAccountNumber());




    }

}
