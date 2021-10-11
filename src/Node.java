import java.sql.Date;
import java.sql.Time;
public abstract class Node {
    //-nombre : string
    //-startTime : time
    //-startDate : date
    // -endTime : time
    // -endDate : date
    // -workingTime : double
    // +calculateTotalTime()*
  private String nombre;
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private Double workingTime;

  abstract float calculateTotalTime();
}
