import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task extends Node implements Observer{

  double totalTime;
  int ticks;
  boolean active;
  boolean done; //Cuando done = True, tarea done.
  LocalDateTime start_date;
  List<LocalDateTime> Start_time;
  List<LocalDateTime> End_time;
  boolean started; //True = Activada por primera vez en algún momento




  public Task( String n) { //Inicializador de objeto tarea donde le pasamos un nombre como único parámetro inicial
    name = n;
    ticks = 0;
    totalTime = 0.0;
    done = false;
    started = false;
    Start_time= new ArrayList();
    End_time = new ArrayList();

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
    Start_time.add(clock.localtime());

  }
  public void stop(SingletonClock clock){ //Inidica finalizacion de intervalo de trabajo

    active = false;
    End_time.add(clock.localtime());
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

    if(started) System.out.println("Task Started: " + Start_time.get(0).getMonth() + "/" + Start_time.get(0).getDayOfMonth() + "/" + Start_time.get(0).getYear());

    for(int i=0; i < End_time.size(); i++)
    {
      if(i == 0) { System.out.println("TIME INTERVALS: ");}
      System.out.println("Interval " + (i+1) + ": ");
      System.out.println("From: " + Start_time.get(i).getMonth() + "/" + Start_time.get(i).getDayOfMonth() + "/" + Start_time.get(i).getYear() + "   " +  Start_time.get(i).getHour() + ":" + Start_time.get(i).getMinute() + ":" + Start_time.get(i).getSecond() );
      System.out.println("To: " + End_time.get(i).getMonth() + "/" + End_time.get(i).getDayOfMonth() + "/" + End_time.get(i).getYear() + "   " +  End_time.get(i).getHour() + ":" + End_time.get(i).getMinute() + ":" + End_time.get(i).getSecond());
    }
    System.out.println("**********");
  }
}

