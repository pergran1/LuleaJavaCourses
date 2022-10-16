/**
 * Denna klass representerar ett sparkonto
 * Den innehåller variabler såsom pengar, ränta, kontonummer och typ av konto
 * Men ska kunna hämta och ändra några variabler, bland annat ta ut eller sätta in pengar
 *
 * Metoder finns även för att få en bra utskrift av kontot
 *
 * @author Per Granberg, epager-1
 */



package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    protected BigDecimal balance;
    protected BigDecimal interest;
    protected int accountNumber;
    protected static int nbrOfAccounts = 1000;
    protected String accountType;

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

    /**
     * Sätter in pengar på kontot
     */
    public void deposit(int amount){
        balance = balance.add(new BigDecimal(amount));
    }

    /**
     * tar ut pengar från kontot
     * @param int amount
     * @return Boolean
     */
    public boolean withdraw(int amount){
        int checkAmountLessBalance = balance.compareTo(BigDecimal.valueOf(amount));
        if( amount > 0 && checkAmountLessBalance >= 0) {
            balance = balance.subtract(new BigDecimal(amount));
            return true;
        }
        return false;
    }


    /**
     * Skapar en sträng för att visa information om kontot
     */
    @Override
    public String toString() {
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format( interest.doubleValue() );
        return (accountNumber + " " + balanceStr + " " +  accountType + " " + percentStr);
    }

    /**
     * Skapar en sträng för att skriva ut konto information när man stänger ett konto
     */
    public String closeAccStr() {
        BigDecimal finalInterest =  interest.multiply(balance);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1);
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        String finalStr= NumberFormat.getCurrencyInstance().format(finalInterest);
        return (accountNumber+ " " + balanceStr + " " +  accountType + " " + finalStr);
    }


}
