public class Task extends Node implements Observer{
int taskType; //Tipo de tarea (0 = corta, 1 = larga, 2 = rutinaria, ...)
double totalTime;
int ticks;

  public Task(int tT) {
    taskType = tT;
    ticks = 0;
    totalTime = 0.0;
  }


public double calculateTotalTime()
{
  double tickConstant  = 0.2; // == tiempo que representa cada tick en segundos.
  return (ticks * tickConstant);
}

  @Override
  public void update(){ // El observado notifica al observable, y se llama a esta función
    ticks++;
  }
}
