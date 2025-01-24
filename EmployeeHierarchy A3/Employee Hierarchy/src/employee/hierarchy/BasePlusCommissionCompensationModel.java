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
public class BasePlusCommissionCompensationModel extends CommissionCompensationModel {

    private double baseSalary; // base salary per week

    // six-argument constructor
    public BasePlusCommissionCompensationModel(double grossSales,
            double commissionRate, double baseSalary) {
        // explicit call to superclass CommissionEmployee constructor
        super(grossSales, commissionRate);

        // if baseSalary is invalid throw exception
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }

        this.baseSalary = baseSalary;
    }

    // set base salary
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }

        this.baseSalary = baseSalary;
    }

    // return base salary
    public double getBaseSalary() {
        return baseSalary;
    }
    @Override
    public double raise(double percent){
        this.commissionRate= commissionRate*(1+percent);
        this.baseSalary=baseSalary*(1+percent);
        return earnings();    
    }
        
        
    // calculate earnings
    @Override
    public double earnings() {
        // not allowed: commissionRate and grossSales private in superclass
        return getBaseSalary() + (getCommissionRate() * getGrossSales());
    }
    
    @Override
    public String printModel() {
        return "Base Plus Commission Compensation";
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f %n%s: %.3f %n%s: %.2f%n%s: %.2f",
                "Gross Sales of", getGrossSales(),
                "Commission Rate of", getCommissionRate(),
                "Base Salary of", getBaseSalary(),
                "Earnings", earnings());
    }
}
