import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RoomEntry
{
    private static Connection connection;
    private static PreparedStatement addRoom;
    private static PreparedStatement getRooms;
    private static ResultSet resultSet;
    
    public static void addRoom(String name, Integer seats)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into Rooms (name, seats) values (? ,?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static void dropRoom(String name)
    {
      connection = DBConnection.getConnection();
      try 
      {
          addRoom = connection.prepareStatement("delete from Rooms where name = ?");
          addRoom.setString(1, name);
          addRoom.executeUpdate();  
      }
      catch (SQLException sqlException)   
      {
        sqlException.printStackTrace();
      }
    }
    
        public static ArrayList<String> getAllRooms()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> Rooms = new ArrayList<String>();
        try
        {
            getRooms = connection.prepareStatement("select name from rooms order by name");
            resultSet = getRooms.executeQuery();
            
            while(resultSet.next())
            {
               Rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Rooms;
        
    }
        
     public static String AllRoomsListString(){
         
         ArrayList<String> RoomsList= RoomEntry.getAllRooms();
        
        String Output = "";
        
        for(String names:RoomsList)
        {
            if (names!=null)
            {
            Output=Output+names+'\n';
            }
        }
        return Output;  
     }
     
         public static String[] getRoomStringArray(){
    
        ArrayList<String> orgList = RoomEntry.getAllRooms();
        String[] Rooms= new String[orgList.size()];

        for (Integer i = 0 ; i < orgList.size(); i++ )
            {
                Rooms[i]=orgList.get(i);
            }
        return Rooms;
    }
}
