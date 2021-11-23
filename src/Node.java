import java.sql.Date;
import java.sql.Time;

public abstract class Node {
  protected String name;
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private Double workingTime;
  protected Project father;

  public String getName() {
    return name;
  }

  public Project getFather() {
    return father;
  }

  //devuelva la tasca/proyecto
  abstract Node getInstance();

  //convierto los ticks en segundos
  abstract float calculateTotalTime();

  // TODO : abstact Iterator
}



