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
public class HourlyCompensationModel extends CompensationModel {
      
   private double wage; // wage per hour
   private double hours; // hours worked for week

   // constructor
   public HourlyCompensationModel( double wage, double hours) {

      if (wage < 0.0) { // validate wage
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      }

      if ((hours < 0.0) || (hours > 168.0)) { // validate hours
         throw new IllegalArgumentException(
            "Hours worked must be >= 0.0 and <= 168.0");
      }

      this.wage = wage;
      this.hours = hours;
   } 

   // set wage
   public void setWage(double wage) {
      if (wage < 0.0) { // validate wage
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      }

      this.wage = wage;
   } 

   // return wage
   public double getWage() {return wage;}

   // set hours worked
   public void setHours(double hours) {
      if ((hours < 0.0) || (hours > 168.0)) { // validate hours
         throw new IllegalArgumentException(
            "Hours worked must be >= 0.0 and <= 168.0");
      }

      this.hours = hours;
   } 

   // return hours worked
   public double getHours() {return hours;}

   // calculate earnings; override abstract method earnings in Employee
   @Override
   public double raise(double percent){
        this.wage=wage*(1+percent);
        return earnings();   
   }
        
   @Override                                                           
   public double earnings() {                                          
      if (getHours() <= 40) { // no overtime                           
         return getWage() * getHours();                                
      }                                                                
      else {                                                           
         return 40 * getWage() + (getHours() - 40) * getWage() * 1.5;  
      }                                                                
   }                                                                   

   @Override 
    public String printModel(){
       return "Hourly Compensation Model";
   }
    
   // return String representation of HourlyEmployee object              
   @Override                                                             
   public String toString() {                                            
      return String.format("%s: %.2f%n%s: %.2f%n%s: %.2f",
                "Wage of", getWage(),
                "Hours Worked of", getHours(),
                "Earnings", earnings());                                    
   }                                                                     

}
