package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class SavingsAccount extends Account{
    private boolean usedFreeWithdraw;

    public SavingsAccount(){
        this.usedFreeWithdraw = false;

    }

    public boolean withdraw(int amount){
        double totalAmount;
        if(usedFreeWithdraw == true){
            double twoPercent = amount * 0.02;
            totalAmount = amount + twoPercent;
        } else{
            usedFreeWithdraw = true;
            totalAmount = amount;
        }
        int checkAmountLessBalance = balance.compareTo(BigDecimal.valueOf(totalAmount));
        if( amount > 0 && checkAmountLessBalance >= 0) {
            balance = balance.subtract(new BigDecimal(totalAmount));
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

    public static void main(String[] args){
        SavingsAccount testar = new SavingsAccount();
        System.out.println(testar);
        testar.deposit(40);
        System.out.println(testar.withdraw(30));
        System.out.println(testar);
        System.out.println(testar.withdraw(3));
        System.out.println(testar);
    }

}
