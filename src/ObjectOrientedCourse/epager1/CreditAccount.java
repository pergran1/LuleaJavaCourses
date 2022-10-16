package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;

public class CreditAccount extends Account{
    private int creditLimit;
    private BigDecimal debtInterest;


    public CreditAccount(){
        this.creditLimit = -5000;
        this.accountType = "Kreditkonto";
        this.interest= new BigDecimal("0.005");
        this.debtInterest= new BigDecimal("0.07");

    }


    public boolean withdraw(int amount){
        int newBalanceWouldBe = balance.subtract(new BigDecimal(amount)).intValue();
        if( amount > 0 && newBalanceWouldBe >= creditLimit) {
            balance = balance.subtract(new BigDecimal(amount));
            return true;
        }
        return false;
    }


    public static void main(String[] args){
        CreditAccount testar = new CreditAccount();
        System.out.println(testar);
        testar.deposit(500);
        System.out.println(testar);
        testar.withdraw(6000);
        System.out.println(testar);

    }




}
