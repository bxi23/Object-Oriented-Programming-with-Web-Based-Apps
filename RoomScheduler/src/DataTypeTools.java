
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataTypeTools {
    
    public static String ALtoPrintableString(ArrayList<String> list)
    {
    String Output = "";
        
        for(String names:list)
        {
            if (names!=null)
            {
            Output=Output+names+'\n';
            }
        }
        
        return Output;
    }

    public static String PrintResultSet(ResultSet set) {
        String printString = "";
        try {
            ResultSetMetaData MD = set.getMetaData();
            while (set.next()) {
                for (int i = 1; i <= MD.getColumnCount(); i++) {
                    if (i > 1) {
                        printString = printString + ",              ";
                    }
                    String columnValue = set.getString(i);
                    printString = printString + MD.getColumnName(i) + ":   " + columnValue;
                }
                printString = printString + "\n";
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return printString;
    }

    public static Boolean SearchResultSet(ResultSet set, String value) {
        try {
            ResultSetMetaData MD = set.getMetaData();
            while (set.next()) {
                for (int i = 1; i <= MD.getColumnCount(); i++) {
                    if (value == set.getString(i)) {
                        return true;
                    }
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
