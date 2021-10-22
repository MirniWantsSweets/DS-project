import java.time.LocalDateTime;
public class Task extends Node implements Observer{
int taskType; //Tipo de tarea (0 = corta, 1 = larga, 2 = rutinaria, ...)
double totalTime;
int ticks;
boolean active;
boolean done; //Cuando done = True, tarea done.
Date start_date;
boolean started;




  public Task(int tT, String n) {
    taskType = tT;
    nombre = nom
    ticks = 0;
    totalTime = 0.0;
    done = false;
    started = false;
  }


public double calculateTotalTime(){
  double tickConstant  = 0.2; // == tiempo que representa cada tick en segundos.
  return (ticks * tickConstant);
}
private void start(){

active = true;

}
private void stop(){
done = true;
active = false;
}

private canviNom(String nouNom){ //función para poder cambiar el nombre en cualquier momento.
nom = nouNom;
}

  @Override
  public void update(SingletonClock clock){ // El observado notifica al observable, y se llama a esta función
    if(this.active==true){
      ticks++;
    }
    if(started == false){
      started=true;
      start_date = clock.LocalDateTime();

    }
  }
}
