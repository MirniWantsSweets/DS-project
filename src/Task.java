import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task extends Node implements Observer{

  double totalTime;
  int ticks;
  boolean active;
  boolean done; //Cuando done = True, tarea done.
  LocalDateTime start_date;
  List<TimeInterval> timeIntervals;
  boolean started; //True = Activada por primera vez en algún momento




  public Task( String n) { //Inicializador de objeto tarea donde le pasamos un nombre como único parámetro inicial
    name = n;
    ticks = 0;
    totalTime = 0.0;
    done = false;
    started = false;
    timeIntervals = new ArrayList<TimeInterval>();

  }
  @Override
  public Task getInstance(){ // Retorna un objeto de la clase Task
    return this;
  }

  @Override
  public float calculateTotalTime(){
    float tickConstant  = 0.2f; // == tiempo que representa cada tick en segundos.
    return (ticks * tickConstant);
  }
  public void start(SingletonClock clock){ //Inidica inicio de intervalo de trabajo

    active = true;
    timeIntervals.add(new TimeInterval(clock));

  }
  public void stop(SingletonClock clock){ //Inidica finalizacion de intervalo de trabajo

    active = false;
    (timeIntervals.get(timeIntervals.size()-1)).endInterval(clock);
  }
  public void setDone(){ // Indica la finalizacion de la tarea
    done= true;
  }



  private void changename(String newname){ //función para poder cambiar el nombre en cualquier momento.
    name = newname;
  }

  @Override
  public void update(){ // El observado notifica al observable, y se llama a esta función
    if(this.active==true){
      ticks++;
    }

  }

    void displayTask() //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
  {
    System.out.println("**********");
    System.out.println("Task: " + name + " total time = " + calculateTotalTime());

    if(started) System.out.println("Task Started: " + timeIntervals.get(0).getStartTime().getMonth() + "/" + timeIntervals.get(0).getStartTime().getDayOfMonth() + "/" + timeIntervals.get(0).getStartTime().getYear());

    for(int i=0; i < timeIntervals.size(); i++)
    {
      if(i == 0) { System.out.println("TIME INTERVALS: ");}
        System.out.println("Interval " + (i+1) + ": ");
        System.out.println("From: " + timeIntervals.get(i).getStartTime().getMonth() + "/" + timeIntervals.get(i).getStartTime().getDayOfMonth() + "/" + timeIntervals.get(i).getStartTime().getYear() + "   " +  timeIntervals.get(i).getStartTime().getHour() + ":" + timeIntervals.get(i).getStartTime().getMinute() + ":" + timeIntervals.get(i).getStartTime().getSecond() );
      if(timeIntervals.get(i).getEndTime() != null){
        System.out.println("To: " + timeIntervals.get(i).getEndTime().getMonth() + "/" + timeIntervals.get(i).getEndTime().getDayOfMonth() + "/" + timeIntervals.get(i).getEndTime().getYear() + "   " +  timeIntervals.get(i).getEndTime().getHour() + ":" + timeIntervals.get(i).getEndTime().getMinute() + ":" + timeIntervals.get(i).getEndTime().getSecond());
      }
      else {System.out.println("Task in progress");}
      }
    System.out.println("**********");
  }
}

