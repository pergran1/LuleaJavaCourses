package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class SavingsAccount extends Account{
    private boolean usedFirstTime;
    private double savingInterest;

    public SavingsAccount(){
        this.usedFirstTime = false;
        this.savingInterest = 0.02;

    }

    @Override
    public boolean withdraw(int amount){
        double totalAmount;
        if(usedFirstTime == true){
            double twoPercent = amount * savingInterest;
            totalAmount = amount + twoPercent;
        } else{
            usedFirstTime = true;
            totalAmount = amount;
        }
        int checkAmountLessBalance = balance.compareTo(BigDecimal.valueOf(totalAmount));
        if( amount > 0 && checkAmountLessBalance >= 0) {
            balance = balance.subtract(new BigDecimal(totalAmount));
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
        return (accountNumber + " " + balanceStr + " " +  accountType + " " + percentStr);
    }

}
