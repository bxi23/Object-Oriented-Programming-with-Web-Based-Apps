
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;

public class WaitListQueries {
    
    public static Connection connection;
    private static ArrayList<String> waitlist = new ArrayList<String>();
    private static PreparedStatement addWaitlist;
    private static PreparedStatement getWaitlist;
    private static ResultSet resultSet;
    
    public static void addWaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp){
        connection = DBConnection.getConnection();
        try
        {
            addWaitlist = connection.prepareStatement("insert into waitlist (faculty, date, seats, timestamp) values (?,?,?,?)");
            addWaitlist.setString(1, faculty);
            addWaitlist.setDate(2, date);
            addWaitlist.setInt(3, seats);
            addWaitlist.setTimestamp(4, timestamp);
            addWaitlist.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<WaitListEntry> getWaitlist()
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitListEntry> reservations = new ArrayList<WaitListEntry>();
        try
        {
            getWaitlist = connection.prepareStatement("select faculty, date, seats, timestamp from waitlist");
            resultSet = getWaitlist.executeQuery();
            
            while(resultSet.next())
            {
                WaitListEntry entry = new WaitListEntry(resultSet.getString(1),resultSet.getDate(2),resultSet.getInt(3),resultSet.getTimestamp(4));
                reservations.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservations;
    }
     
    
}
