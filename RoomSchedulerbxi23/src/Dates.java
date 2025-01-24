import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dates 
{
    
    private static Connection connection;
    private static PreparedStatement getAllDates;
    private static ResultSet resultSet;
    
        public static ArrayList<java.sql.Date> getAllDates()
    {
        connection = DBConnection.getConnection();
        ArrayList<java.sql.Date> date = new ArrayList<java.sql.Date>();
        try
        {
            getAllDates = connection.prepareStatement("Select date from Dates order by date");
            resultSet = getAllDates.executeQuery();
            
            while(resultSet.next())
            {
                date.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return date;
        
    }
    
}
