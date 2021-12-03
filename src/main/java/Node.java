import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.format.DateTimeFormatter;


public abstract class Node {
  protected String name;
  private Time startTime;
  private Time endTime;
  protected LocalDateTime startDate;
  protected LocalDateTime endDate;
  private Double workingTime;
  protected Project father;
  public List<String> tags;
  protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static int idCounter = 1;
  protected int id = 0;


  Logger logger = LoggerFactory.getLogger("TimeTracker.Node");

  public String getName() {
    return name;
  }

  public Project getFather() {
    return father;
  }

  public int getID() {
    return this.id = idCounter++;
  }

  //devuelva la tasca/proyecto
  abstract <T> T getInstance();

  //convierto los ticks en segundos
  abstract float calculateTotalTime();

  //carga la clase con todas sus variables a formato JSON
  abstract JSONObject getJSON();

  abstract LocalDateTime calculateFinalDateTime();

  //carga la clase a partir de un objeto JSON
  abstract void loadJSON(JSONObject json);

  abstract void display();

}



