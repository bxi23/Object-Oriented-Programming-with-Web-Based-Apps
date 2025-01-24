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
public class Employee {

    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private CompensationModel CM;

    public Employee(String firstName, String lastName,
            String socialSecurityNumber, CompensationModel CM) {
        // implicit call to Object's default constructor occurs here   

        // if grossSales is invalid throw exception
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.CM = CM;

    }

    public String getFirstName() {
        return firstName;
    }

    // return last name
    public String getLastName() {
        return lastName;
    }

    public double raise(double percent){
        return CM.raise(percent);
    }
    // return social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setCompensation(CompensationModel NewCM) {
        CM = NewCM;
    }

    public double earnings() {
        return CM.earnings();
    }

    @Override
    public String toString() {
        return String.format("%s %s%n%s: %s%n%s with:%n%s",
                firstName, lastName,
                "Social Security Number", socialSecurityNumber,
                CM.printModel(),
                CM);
    }

}
