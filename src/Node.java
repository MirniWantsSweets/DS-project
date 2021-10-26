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
  protected Project padre;

  public String getName() {
    return name; //devuelve el nombre
  }


  abstract Node getInstance(); //devuelva la tasca/proyecto
  abstract float calculateTotalTime(); //convierto los ticks en segundos
}

