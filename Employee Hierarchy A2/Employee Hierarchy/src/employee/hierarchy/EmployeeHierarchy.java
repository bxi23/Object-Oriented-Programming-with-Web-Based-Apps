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
public class EmployeeHierarchy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CommissionCompensationModel commissionModel = new CommissionCompensationModel(2000.00, 0.04);
        BasePlusCommissionCompensationModel basePlusCommissionModel = new BasePlusCommissionCompensationModel(2000.00, 0.05, 600.00);

        Employee employee1 = new Employee("John", "Smith", "111-11-1111", commissionModel);
        Employee employee2 = new Employee("Sue", "Jones", "222-22-2222", basePlusCommissionModel);

        System.out.printf("%s%n%s%n", employee1, employee2);
        System.out.printf("%s%s%s%s%s%8.2f%n%n", "Earnings for ", employee1.getFirstName(), " ", employee1.getLastName(), ": ", employee1.earnings());

        // Change the compensation model for the two employees.
        CommissionCompensationModel commissionModelNew = new CommissionCompensationModel(5000.00, 0.04);
        BasePlusCommissionCompensationModel basePlusCommissionModelNew = new BasePlusCommissionCompensationModel(4000.00, 0.05, 800.00);

        // Set the new compensation models for the employees.
        employee1.setCompensation(basePlusCommissionModelNew);
        employee2.setCompensation(commissionModelNew);

        // Print out the new information for the two employees.
        System.out.printf("%s%n%s%n", employee1, employee2);
    }
}
