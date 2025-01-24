/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.hierarchy;

/**
 *
 * @author ianno
 */
public class SalariedCompensationModel extends CompensationModel {
    private double weeklySalary;

   // constructor
   public SalariedCompensationModel ( double weeklySalary) {

      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }

      this.weeklySalary = weeklySalary;
   } 

   // set salary
   public void setWeeklySalary(double weeklySalary) {
      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }

      this.weeklySalary = weeklySalary;
   } 

   // return salary
   public double getWeeklySalary() {return weeklySalary;}

   // calculate earnings; override abstract method earnings in Employee
   @Override
   public double raise(double percent){
        this.weeklySalary=weeklySalary*(1+percent);
        return earnings();    
   }
  
        
   @Override                                                           
   public double earnings() {return getWeeklySalary();}                

   @Override
   public String printModel(){
       return "Salaried Compensation";
   }
   // return String representation of SalariedEmployee object  
   @Override                                                             
   public String toString() {                                            
      return String.format("%s: %.2f%n%s: %.2f",
                "Weekly Salary of", getWeeklySalary(),
                "Earnings", earnings());
   }          
}
