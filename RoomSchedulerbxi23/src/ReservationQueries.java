
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;

public class ReservationQueries {
    
    private static Connection connection;
    private static PreparedStatement addReservations;
    private static PreparedStatement getReservations;
    private static PreparedStatement getRoomReservations;
    private static ResultSet resultSet;
    
    public static void addReservationEntry(String faculty, String room,int seats, Date date,Timestamp timestamp)
    {
        connection = DBConnection.getConnection();
        try
        {
            addReservations = connection.prepareStatement("insert into reservations (faculty, room, seats, date, timestamp) values (?,?,?,?,?)");
            addReservations.setString(1, faculty);
            addReservations.setString(2,room);
            addReservations.setInt(3, seats);
            addReservations.setDate(4, date);
            addReservations.setTimestamp(5, timestamp);
            addReservations.executeUpdate();
            } 
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static ArrayList<String> getRoomsReservedByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> reservations = new ArrayList<String>();
        try
        {
            getRoomReservations = connection.prepareStatement("select faculty, room, seats from reservations where date = ?");
            getRoomReservations.setDate(1,date);
            resultSet = getRoomReservations.executeQuery();
            
            while(resultSet.next())
            {
                reservations.add(resultSet.getString(2));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservations; 
    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reserved = new ArrayList<ReservationEntry>();
        try
        {
            getReservations = connection.prepareStatement("select faculty, room, date, seats, timestamp from reservations where date = ? ");
            getReservations.setDate(1, date);
            resultSet = getReservations.executeQuery();
            
            while(resultSet.next())
            {
                ReservationEntry entry = new ReservationEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(4),resultSet.getDate(3),resultSet.getTimestamp(5));
                reserved.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reserved; 
    }
    
}
