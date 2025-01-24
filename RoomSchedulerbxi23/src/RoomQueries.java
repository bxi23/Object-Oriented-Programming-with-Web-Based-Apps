
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RoomQueries {
    
    private static Connection connection;
    private static ArrayList<String> room = new ArrayList<String>();
    private static PreparedStatement addRoom;
    private static PreparedStatement getListOfRooms;
    private static ResultSet resultSet;
    
    public static void addRoom(String rooms, int seats)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into reservation (rooms, seats) values (?,?)");
            addRoom.setString(1, rooms);
            addRoom.setInt(3, seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
     }
    
    public static ArrayList<String> getAllPossibleRooms(int seats)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getListOfRooms = connection.prepareStatement("select name from rooms where seats >=? order by seats");
            getListOfRooms.setInt(1,seats);
            resultSet = getListOfRooms.executeQuery();
            
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
        
    }
    
}
