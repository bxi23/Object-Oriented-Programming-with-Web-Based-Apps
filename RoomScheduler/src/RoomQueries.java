
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import java.sql.Date;

public class RoomQueries {
    
    private static Connection connection;
    private static PreparedStatement seatSearch;
    private static ArrayList<String> possibleRooms = new ArrayList<String>();
    private static ResultSet resultSet=null;
    
    public static void addRooms(String Room)
    {
        possibleRooms.add(Room);
    }
    
    public static void dropRoom(String Room)
    {
        possibleRooms.remove(Room);
    }
    
    public static ArrayList<String> getAllPossibleRooms(int seats)
    {
        ArrayList<String> largeEnoughRooms = new ArrayList<String>();

        for (Integer i = 0 ; i < possibleRooms.size(); i++ )
            {
                if (getSeats(possibleRooms.get(i)) >= seats)
                {
                    largeEnoughRooms.add(possibleRooms.get(i));
                }
                
            }
        return largeEnoughRooms;
    }
    
    public static Integer getSeats(String room)
    {
        connection = DBConnection.getConnection();
        try
        {
            seatSearch = connection.prepareStatement("select seats in Rooms where Name=?");
            seatSearch .setString(1, room);
            resultSet= seatSearch.executeQuery();
            return resultSet.getInt(0);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return 0;
    }
    

    
    
    
}
