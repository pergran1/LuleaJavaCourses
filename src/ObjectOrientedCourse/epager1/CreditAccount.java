package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CreditAccount extends Account{
    private int creditLimit;
    private BigDecimal debtInterest;


    public CreditAccount(){
        this.creditLimit = -5000;
        this.accountType = "Kreditkonto";
        this.interest = new BigDecimal("0.005");
        this.debtInterest = new BigDecimal("0.07");

    }

    @Override
    public boolean withdraw(int amount){
        int newBalanceWouldBe = balance.subtract(new BigDecimal(amount)).intValue();
        if( amount > 0 && newBalanceWouldBe >= creditLimit) {
            balance = balance.subtract(new BigDecimal(amount));
            writeTransactions(amount, balance, true);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format( interest.doubleValue() );
        String percentDebtInterest = percentFormat.format( debtInterest.doubleValue() );
        if(balance.intValue() >= 0){
            return (accountNumber + " " + balanceStr + " " +  accountType + " " + percentStr);
        } else{
            return (accountNumber + " " + balanceStr + " " +  accountType + " " + percentDebtInterest);
        }

    }

    @Override
    public String closeAccStr() {
        // check if the balance is negative
        BigDecimal finalInterest;
        if (balance.compareTo(BigDecimal.ZERO) < 0){
            finalInterest =  debtInterest.multiply(balance);
        } else{
            finalInterest =  interest.multiply(balance);
        }

        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1);
        String balanceStr = NumberFormat.getCurrencyInstance().format(balance);
        String finalStr= NumberFormat.getCurrencyInstance().format(finalInterest);
        return (accountNumber + " " + balanceStr + " " +  accountType + " " + finalStr);
    }


}
