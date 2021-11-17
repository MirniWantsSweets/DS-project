import java.sql.Date;
import java.sql.Time;

public abstract class Node {
  protected String name;
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private Double workingTime;
  protected Project padre;

  public String getName() {
    return name;
  }

  //devuelva la tasca/proyecto
  abstract Node getInstance();

  //convierto los ticks en segundos
  abstract float calculateTotalTime();
}


