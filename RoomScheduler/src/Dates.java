
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;


public class Dates
{
    private static Connection connection;
    private static ArrayList<String> DatesList = new ArrayList<String>();
    private static PreparedStatement addDates;
    private static PreparedStatement getAllDatesPS;
    private static ResultSet resultSet;
    
    public static void addDates(Date date)
    {
        connection = DBConnection.getConnection();
        try
        {
            addDates = connection.prepareStatement(String.format("insert into Dates (date) values (?)"));
            addDates.setDate(1, date);
            addDates.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<Date> getAllDates()
    {
        connection = DBConnection.getConnection();
        ArrayList<Date> DatesList = new ArrayList<Date>();
        try
        {
            getAllDatesPS = connection.prepareStatement("select date from Dates order by date");
            resultSet = getAllDatesPS.executeQuery();
            
            while(resultSet.next())
            {
                DatesList.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return DatesList;
        
    }
    
    public static String AllDatesListString()
    {
        ArrayList<Date> AllDates= Dates.getAllDates();
        
        String Output = "";
        
        for(Date Date:AllDates)
        {
            if (Date!=null)
            {
            Output=Output+Date.toString()+'\n';
            }
        }
        return Output;
    }
    
      public static String[] getDatesStringArray(){
    
        ArrayList<Date> orgList = Dates.getAllDates();
        String[] DateLog= new String[orgList.size()];

        for (Integer i = 0 ; i < orgList.size(); i++ )
            {
                DateLog[i]=orgList.get(i).toString();
            }
        return DateLog;
    }
    
}