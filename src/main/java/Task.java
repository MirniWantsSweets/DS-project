import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends Node implements Observer {
  float totalTime;
  int ticks;
  boolean active;
  boolean done; //Cuando done = True, tarea done.
  List<TimeInterval> timeIntervals;
  boolean started; //True = Activada por primera vez en algún momento

  Logger logger = LoggerFactory.getLogger("TimeTracker.Task");


  //Inicializador de objeto tarea donde le pasamos un nombre como único parámetro inicial
  public Task(String n, List<String> tag) {
    this.name = n;
    this.ticks = 0;
    this.totalTime = 0;
    this.done = false;
    this.started = false;
    this.timeIntervals = new ArrayList<TimeInterval>();
    this.father = null;
    this.tags = new ArrayList();
    this.getID();
    for (int i = 0; i < tag.size(); i++) {
      tags.add(tag.get(i));
    }
  }

  public Task(JSONObject json) {
    loadJSON(json);
  }

  //Inidica inicio de intervalo de trabajo
  public void start(SingletonClock clock) {
    if(timeIntervals.size() == 0) {
      this.startDate = LocalDateTime.now();
    }
    if (!this.active && !this.done) {
      this.active = true;
      timeIntervals.add(new TimeInterval(clock));
    }
    else {
      logger.error("Can't start task: Task is already started.");
    }
  }

  //Inidica finalizacion de intervalo de trabajo
  public void stop(SingletonClock clock) {
    if (this.active) {
      this.active = false;
      (timeIntervals.get(timeIntervals.size() - 1)).endInterval(clock);
      this.endDate = LocalDateTime.now();
    }
    else {
      logger.error("Can't stop task: Task is not active.");
    }
  }

  // Indica la finalizacion de la tarea
  public void setDone() {
    if(!this.active) {
      this.done = true;
    }
    else {
      logger.error("Can't finish task: Task still marked as active.");
    }
  }


  //función para poder cambiar el nombre en cualquier momento
  private void changeName(String newname) {
    this.name = newname;
  }

  //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y
  //fechas de inicio y finalizacion de los intervalos
  @SuppressWarnings("checkstyle:WhitespaceAround")
  @Override
  void display() {
    logger.info("Task: " + name + " total time = " + calculateTotalTime());
    if (started) {
      logger.info("Task Started: " + timeIntervals.get(0).getStartTime().getMonth() + "/"
              + timeIntervals.get(0).getStartTime().getDayOfMonth() + "/"
              + timeIntervals.get(0).getStartTime().getYear());
    }
    for (int i = 0; i < timeIntervals.size(); i++) {
      if (i == 0) {
        logger.info("TIME INTERVALS: ");
      }
      logger.info("Interval " + (i + 1) + ": ");
      logger.info("From: " + timeIntervals.get(i).getStartTime().getMonth() + "/"
              + timeIntervals.get(i).getStartTime().getDayOfMonth() + "/"
              + timeIntervals.get(i).getStartTime().getYear() + "   "
              + timeIntervals.get(i).getStartTime().getHour() + ":"
              + timeIntervals.get(i).getStartTime().getMinute() + ":"
              + timeIntervals.get(i).getStartTime().getSecond());
      if (timeIntervals.get(i).getEndTime() != null) {
        logger.info("To: " + timeIntervals.get(i).getEndTime().getMonth() + "/"
                + timeIntervals.get(i).getEndTime().getDayOfMonth() + "/"
                + timeIntervals.get(i).getEndTime().getYear()
                + "   " +  timeIntervals.get(i).getEndTime().getHour() + ":"
                + timeIntervals.get(i).getEndTime().getMinute()
                + ":" + timeIntervals.get(i).getEndTime().getSecond());
      } else {
        logger.info("Task in progress");
      }
      if (!tags.isEmpty()) {
        logger.info("Tags:" + '\n');
        for (int j = 0; j < tags.size(); j++) {
          logger.info(" " + tags.get(j));
        }
      }
    }
  }
  
  // El observado notifica al observable, y se llama a esta función
  @Override
  public void update() {
    if (this.active == true) {
      this.ticks++;
    }
  }

  //Calcula el tiempo total de la tasca
  @Override
  public float calculateTotalTime() {
    float tickConstant  = 0.2f; // == tiempo que representa cada tick en segundos.
    return this.totalTime = ticks * tickConstant;
  }
  
  // Retorna un objeto de la clase Task
  @Override
  public Task getInstance() {
    return this;
  }

  @Override
  public LocalDateTime calculateFinalDateTime() {
    return this.endDate;
  }

  @Override
  public JSONObject getJSON() {
    JSONObject json = new JSONObject();

    json.put("name", this.name);
    json.put("id", this.id);
    json.put("class", "task");
    json.put("active", this.active);
    if (startDate == null)
      json.put("initialDate", "");
    else
      json.put("initialDate", this.startDate.format(formatter));
    if (endDate == null)
      json.put("finalDate", "");
    else
      json.put("finalDate", this.endDate.format(formatter));
    this.calculateTotalTime();
    json.put("duration", this.totalTime);
    JSONArray array = new JSONArray();

    for (int i = 0; i < this.timeIntervals.size(); i++) {
      array.put(this.timeIntervals.get(i).getJSON());
    }

    json.put("timeIntervals", array);
    json.put("tags", new JSONArray(this.tags));

    //json.put("started", started);
    //json.put("startTime", startTime);
    //json.put("endTime", endTime);
    //json.put("startDate", startDate);
    //json.put("workingTime", workingTime);
    //json.put("father", father);

    return json;
  }

  @Override
  public void loadJSON(JSONObject json) {
    this.name = (String) json.get("name");
    this.id = (int) json.get("id");
    this.active = (Boolean) json.get("active");
    if ((String) json.get("initialDate") == "")
      this.startDate = null;
    else
      this.startDate = LocalDateTime.parse((String) json.get("initialDate"), this.formatter);
    if ((String) json.get("finalDate") == "")
      this.endDate = null;
    else
      this.endDate = LocalDateTime.parse((String) json.get("finalDate"), this.formatter);
    this.totalTime = (int) json.get("duration");
    JSONArray array = (JSONArray) json.get("timeIntervals");

    this.timeIntervals = new ArrayList();
    for (int i = 0; i < array.length(); i++) {
      timeIntervals.add(new TimeInterval((JSONObject) array.get(i)));
    }

    array = (JSONArray) json.get("tags");
    this.tags = new ArrayList();
    for (int i = 0; i < array.length(); i++ ) {
      this.tags.add((String) array.get(i));
    }

  }
}

  