/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savingsaccountclass;


public class SavingsAccount{

    private double savingsBalance;
    private static double annualIntrestRate;

    //class intialize Function
    public SavingsAccount(double Bal){
        savingsBalance=Bal;
    }

    public static void main(String[] args) {

        SavingsAccount saver1 = new SavingsAccount(2000);
        SavingsAccount saver2 = new SavingsAccount(3000);
        setInterestRate(0.04);
        System.out.println("Savings Account Balances");
        
        System.out.printf("%5s%15s%10s\n", "Month", "Saver1", "Saver2");
        
        for (int m=0; m<12;m++){
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            System.out.printf("%-10s%10s%10s\n",m,saver1.printformat(),saver2.printformat());

        }
        
        SavingsAccount.setInterestRate(.05);
        
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.printf("%-10s%10s%10s\n","13",saver1.printformat(),saver2.printformat());
         

    }
    
    //use Functions
    public String printformat (){
        return String.format("$%.2f",savingsBalance);
    }

    public void calculateMonthlyInterest() {
        savingsBalance+=  (savingsBalance * annualIntrestRate) / 12;
    }

    public static void setInterestRate( double nv) {
        if (nv>=0){
        annualIntrestRate=nv; 
        }
    }

    public void setSavingsBalance(double nv) {
        savingsBalance=nv;
    }


}
