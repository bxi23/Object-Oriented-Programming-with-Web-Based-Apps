
import java.util.Date;
import java.sql.Timestamp;


public class WaitListEntry {
    private String faculty;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    public WaitListEntry(String faculty, Date date, int seats, Timestamp timestamp){
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
       
    }
    
    public String getFaculty(){
        return faculty;
    }
    
    public int getSeats(){
        return seats;
    }
    
    public Date getDate(){
        return date;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
}


