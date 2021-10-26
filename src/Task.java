import java.time.LocalDateTime;
public class Task extends Node implements Observer{

  double totalTime;
  int ticks;
  boolean active;
  boolean done; //Cuando done = True, tarea done.
  LocalDateTime start_date;
  boolean started;




  public Task( String n) {
    name = n;
    ticks = 0;
    totalTime = 0.0;
    done = false;
    started = false;
    type= false;
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
  public void start(){

    active = true;

  }
  public void stop(){
    done = true;
    active = false;
  }

  private void changename(String nouNom){ //función para poder cambiar el nombre en cualquier momento.
    name = nouNom;
  }

  @Override
  public void update(SingletonClock clock){ // El observado notifica al observable, y se llama a esta función
    if(this.active==true){
      ticks++;
    }
    if(started == false){
      started=true;
      start_date = clock.localtime();

    }
  }
}
