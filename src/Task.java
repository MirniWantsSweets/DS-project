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
  boolean started;




  public Task( String n) {
    name = n;
    ticks = 0;
    totalTime = 0.0;
    done = false;
    started = false;
    Start_time= new ArrayList();
    End_time = new ArrayList();

  }
  @Override
  public Task getInstance(){
    return this;
  }

  @Override
  public float calculateTotalTime(){
    float tickConstant  = 0.2f; // == tiempo que representa cada tick en segundos.
    return (ticks * tickConstant);
  }
  public void start(SingletonClock clock){

    active = true;
    Start_time.add(clock.localtime());

  }
  public void stop(SingletonClock clock){

    active = false;
    End_time.add(clock.localtime());
  }
  public void setDone(){
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
}

