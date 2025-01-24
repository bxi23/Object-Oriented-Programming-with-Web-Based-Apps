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
public class CommissionCompensationModel extends CompensationModel {

    public double grossSales; // gross weekly sales       
    public double commissionRate; // commission percentage

    // five-argument constructor                                
    public CommissionCompensationModel(double grossSales,
            double commissionRate) {
        // implicit call to Object's default constructor occurs here   

        // if grossSales is invalid throw exception
        if (grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }

        // if commissionRate is invalid throw exception
        if (commissionRate <= 0.0 || commissionRate >= 1.0) {
            throw new IllegalArgumentException(
                    "Commission rate must be > 0.0 and < 1.0");
        }
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    public void setGrossSales(double grossSales) {
        if (grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }

        this.grossSales = grossSales;
    }

    // return gross sales amount
    public double getGrossSales() {
        return grossSales;
    }

    // set commission rate
    public void setCommissionRate(double commissionRate) {
        if (commissionRate <= 0.0 || commissionRate >= 1.0) {
            throw new IllegalArgumentException(
                    "Commission rate must be > 0.0 and < 1.0");
        }

        this.commissionRate = commissionRate;
    }

    // return commission rate
    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public double raise(double percent){
        this.commissionRate= commissionRate*(1+percent);
        return earnings();      
    }
    // calculate earnings 
    @Override
    public double earnings() {
        return getCommissionRate() * getGrossSales();
    }
    @Override
    public String printModel() {
        return "Commission Compensation";
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f%n%s: %.3f%n%s: %.2f",
                "Gross Sales of", getGrossSales(),
                "Commission Rate of", getCommissionRate(),
                "Earnings", earnings());
    }
}
