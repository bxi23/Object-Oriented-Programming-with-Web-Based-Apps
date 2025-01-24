
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ianno
 */
public class ReservationQueries {
    
     private static Connection connection;
    private static PreparedStatement ResEntry;
    private static PreparedStatement getRes;
    private static ResultSet resultSet=null;
    private static ArrayList list;

    public static ResultSet getReservationsByFaculty(String name) {
        ReservationQueries.connection = DBConnection.getConnection();
        try {
            ReservationQueries.getRes = ReservationQueries.connection.prepareStatement("Select * From Reservations where Faculty=?");
            ReservationQueries.getRes.setString(1, name);
            ReservationQueries.resultSet = ReservationQueries.getRes.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ReservationQueries.resultSet;
    }

    
    public static ResultSet getReservationsByDate(String DateEntered) {
        ReservationQueries.connection = DBConnection.getConnection();
        try {
            ReservationQueries.getRes = ReservationQueries.connection.prepareStatement("Select * From Reservations where Date=?");
            ReservationQueries.getRes.setDate(1, Date.valueOf(DateEntered));
            ReservationQueries.resultSet = ReservationQueries.getRes.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ReservationQueries.resultSet;
    }

    
    public static ResultSet getRoomsReservedByDate(String DateEntered) {
        ReservationQueries.connection = DBConnection.getConnection();
        try {
            ReservationQueries.getRes = ReservationQueries.connection.prepareStatement("Select (Date,Room) From Reservations where Date=?");
            ReservationQueries.getRes.setDate(1, Date.valueOf(DateEntered));
            ReservationQueries.resultSet = ReservationQueries.getRes.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ReservationQueries.resultSet;
    }
   
    
}
