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
public abstract class CompensationModel {
    
    public abstract double earnings();
    
    public abstract String printModel();
    
    @Override
    public abstract String toString();
    
    public  abstract double raise(double percent);
    
}
