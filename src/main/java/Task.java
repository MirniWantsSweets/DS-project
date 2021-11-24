import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends Node implements Observer {
  double totalTime;
  int ticks;
  boolean active;
  boolean done; //Cuando done = True, tarea done.
  LocalDateTime startDate;
  List<TimeInterval> timeIntervals;
  boolean started; //True = Activada por primera vez en algún momento

  Logger logger = LoggerFactory.getLogger("TimeTracker.Task");


  //Inicializador de objeto tarea donde le pasamos un nombre como único parámetro inicial
  public Task(String n, List<String> tag) {
    name = n;
    ticks = 0;
    totalTime = 0.0;
    done = false;
    started = false;
    timeIntervals = new ArrayList<TimeInterval>();
    father = null;
    tags = new ArrayList();
    for (int i = 0; i < tag.size(); i++) {
      tags.add(tag.get(i));
    }
  }

  // Retorna un objeto de la clase Task
  @Override
  public Task getInstance() {
    return this;
  }

  @Override
  public float calculateTotalTime() {
    float tickConstant  = 0.2f; // == tiempo que representa cada tick en segundos.
    return (ticks * tickConstant);
  }

  //Inidica inicio de intervalo de trabajo
  public void start(SingletonClock clock) {
    active = true;
    timeIntervals.add(new TimeInterval(clock));
  }

  //Inidica finalizacion de intervalo de trabajo
  public void stop(SingletonClock clock) {
    active = false;
    (timeIntervals.get(timeIntervals.size() - 1)).endInterval(clock);
  }

  // Indica la finalizacion de la tarea
  public void setDone() {
    done = true;
  }


  //función para poder cambiar el nombre en cualquier momento
  private void changeName(String newname) {
    name = newname;
  }

  // El observado notifica al observable, y se llama a esta función
  @Override
  public void update() {
    if (this.active == true) {
      ticks++;
    }

  }

  //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y
  //fechas de inicio y finalizacion de los intervalos
  @SuppressWarnings("checkstyle:WhitespaceAround")
  void displayTask() {
    logger.info("**********");
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
    logger.info("**********");
  }
}

