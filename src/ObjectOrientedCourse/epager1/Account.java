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
        this.balance = new BigDecimal("0");
        this.interest= new BigDecimal("0.012");
        this.accountNumber = nbrOfAccounts;
        this.accountType = "Sparkonto";
    }

    public String getAccount(){
        return this.accountType;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public BigDecimal getInterest(){
        return interest;
    }

    @Override
    public String toString() {
        return (getAccountNumber() + " " +  getAccount() + " " + getInterest());
    }


    public static void main(String[] args) {
        Account testar = new Account();
        Account testar2 = new Account();

        System.out.println(testar.getAccount());
        System.out.println(testar.getAccountNumber());
        System.out.println(testar2.getAccountNumber());




    }

}
