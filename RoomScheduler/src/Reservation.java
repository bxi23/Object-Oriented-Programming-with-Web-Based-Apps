
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import java.sql.Date;


public class Reservation {
    

    private static Connection connection;
    private static PreparedStatement ResEntry;
    private static PreparedStatement getRes;
    private static ResultSet resultSet=null;
    private static ArrayList list;


    
    public static void addReservationEntry(String FacName, String Date, int seats){
        
        connection = DBConnection.getConnection();
        
        ResultSet ResByFac = ReservationQueries.getReservationsByFaculty(FacName);
        ResultSet RoomsByDate = ReservationQueries.getRoomsReservedByDate(Date);
        ResultSet ResbyDate = ReservationQueries.getReservationsByDate(Date);
        
        if (DataTypeTools.SearchResultSet(ResByFac,Date)==false){
        try
        {
            ResEntry= connection.prepareStatement("Insert into Reservations(Faculty,Room, Date, Seats) values(?,?,?,?)");
            ResEntry.setString(1,FacName);
            ResEntry.setString(2,"TestRoom");
            ResEntry.setString(3,Date.valueOf(Date));
            ResEntry.setInt(4,seats);
            ResEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        }
        
    }
    
}
