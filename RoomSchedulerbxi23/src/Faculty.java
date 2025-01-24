
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Faculty {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addFaculty;
    private static PreparedStatement getFacultyList;
    private static ResultSet resultSet;
    
    public static void addFaculty(String name)
    {
        connection = DBConnection.getConnection();
        try
        {
            addFaculty = connection.prepareStatement("insert into faculty (name) values (?)");
            addFaculty.setString(1, name);
            addFaculty.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
   
    public static ArrayList<String> getFacultyList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> fac = new ArrayList<String>();
        try
        {
            getFacultyList = connection.prepareStatement("Select Name from Faculty order by Name");
            resultSet = getFacultyList.executeQuery();
            
            while(resultSet.next())
            {
                fac.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return fac;
        
    }
}
