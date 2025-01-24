
import java.util.Date;
import java.sql.Timestamp;

public class ReservationEntry {
    
   private String faculty;
   private String rooms;
   private Integer seats;
   private Date date;
   private Timestamp timestamp;
    
   public ReservationEntry(String faculty, String rooms, Integer seats, Date date, Timestamp timestamp )
   {
        this.faculty = faculty;
        this.rooms = rooms;
        this.seats = seats;
        this.date = date;
        this.timestamp = timestamp;
   }
     
   public String getFaculty()
   {
        return faculty;
   }
   
   public String getRooms()
   {
       return rooms;
   }
    
    public int getSeats()
    {
        return seats;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public Timestamp getTimestamp()
    {
        return timestamp;
    }
    
}
