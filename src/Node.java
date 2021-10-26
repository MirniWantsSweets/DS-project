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
  protected String name;
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private Double workingTime;


  public String getName() {
    return name;
  }


  abstract Node getInstance();
  abstract float calculateTotalTime();
}

