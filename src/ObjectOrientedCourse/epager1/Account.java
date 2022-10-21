/**
 * Denna klass är en abstract klass av ett konto som innehåller många av de
 * variabler och metoder som SavingsAccount samt CreditAccount använder.
 * Här finns metoder såsom lägga till eller ta ut pengar samt spara information om varje transaktion
 *
 * @author Per Granberg, epager-1
 */



package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public abstract class Account {
    protected BigDecimal balance;
    protected BigDecimal interest;
    protected int accountNumber;
    private static int nbrOfAccounts = 1000;
    protected String accountType;
    protected ArrayList<String> transactionList = new ArrayList<String>();

    public Account() {
        nbrOfAccounts++;
        this.balance = new BigDecimal("0.00");
        this.interest= new BigDecimal("0.012");
        this.accountNumber = nbrOfAccounts;
        this.accountType = "Sparkonto";
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
        writeTransactions(amount, balance, false);
    }

    /**
     * tar ut pengar från kontot
     * //@param int amount
     * @return Boolean
     */

    public abstract boolean withdraw(int amount);


    /**
     * Skapar en sträng för att visa information om kontot
     */
    @Override
    public abstract String toString();

    /**
     * Skapar en sträng för att skriva ut konto information när man stänger ett konto
     */
    public String closeAccStr() {
        BigDecimal finalInterest =  interest.multiply(balance);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1);
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        String finalStr= NumberFormat.getCurrencyInstance().format(finalInterest);
        return (accountNumber + " " + balanceStr + " " +  accountType + " " + finalStr);
    }


    /**
     * Sparar information från varje transaktion
     * @param int amount, BigDecimal balance, boolean convertToNegative
     */
    public void writeTransactions(int amount, BigDecimal balance, boolean convertToNegative){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        if(convertToNegative == true){
             amount = amount * -1;
        }
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        String amountStr = NumberFormat.getCurrencyInstance().format(amount);
        String transactionStr = strDate + " " + amountStr + " Saldo: " + balanceStr;
        transactionList.add(transactionStr);

    }

    public ArrayList<String> getTransactionList(){
        return transactionList;
    }


}
