public class Task extends Node implements Observer{
int taskType; //Tipo de tarea (0 = corta, 1 = larga, 2 = rutinaria, ...)
double totalTime;
int ticks;
boolean terminada; //Cuando terminada = True, tarea terminada.





  public Task(int tT, String n) {
    taskType = tT;
    nombre = nom
    ticks = 0;
    totalTime = 0.0;
    terminada = false;
  }


public double calculateTotalTime(){
  double tickConstant  = 0.2; // == tiempo que representa cada tick en segundos.
  return (ticks * tickConstant);
}

private void terminar(){
terminada = true;
}

private canviNom(String nouNom){ //función para poder cambiar el nombre en cualquier momento.
nom = nouNom;
}

  @Override
  public void update(){ // El observado notifica al observable, y se llama a esta función
    ticks++;
  }
}
