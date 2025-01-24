/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdatabase;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author ianno
 */
public class BookDataBase {

    public static void main(String[] args) {
      final String DATABASE_URL = "C:/Programs Projects/Derby Databases/Book";        
      final String SELECT_QUERY =                            
         "SELECT authorID, firstName, lastName FROM authors";

      // use try-with-resources to connect to and query the database
      try (                                                        
         Connection connection = DriverManager.getConnection(      
            DATABASE_URL, "java", "java");                     
         Statement statement = connection.createStatement();       
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {

         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Authors Table of Books Database:%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++) {
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         }
         System.out.println();
         
         // display query results
         while (resultSet.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {
               System.out.printf("%-8s\t", resultSet.getObject(i));
            }
            System.out.println();
         } 
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
      }                                                   
   } 
} 