package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

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

    public boolean withdraw(int amount){
        int checkAmountLessBalance = balance.compareTo(BigDecimal.valueOf(amount));
        if( amount > 0 && checkAmountLessBalance >= 0) {
            balance = balance.subtract(new BigDecimal(amount));
            return true;
        }
        return false;


    }

    @Override
    public String toString() {
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        BigDecimal finalInterest =  interest.multiply(balance);
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format( interest.doubleValue() );
        return (accountNumber + " " + balanceStr + " " +  accountType + " " + percentStr);
    }

    public String closeAccStr() {
        BigDecimal finalInterest =  interest.multiply(balance);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format( finalInterest.intValue());
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        String finalStr= NumberFormat.getCurrencyInstance().format(finalInterest);
        return (accountNumber+ " " + balanceStr + " " +  accountType + " " + finalStr);
    }





    public static void main(String[] args) {
        Account testar = new Account();
        Account testar2 = new Account();

        System.out.println(testar.getAccount());
        System.out.println(testar.getAccountNumber());
        System.out.println(testar2.getAccountNumber());




    }

}
